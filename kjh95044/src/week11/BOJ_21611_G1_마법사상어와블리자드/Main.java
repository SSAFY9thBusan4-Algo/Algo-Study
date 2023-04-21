import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, map[][], mid, sum;
    static List<Integer> list;
    static int[][] delta = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // 문제에서 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        mid = N /2;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(in.readLine());
            for (int m = 0; m < N; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for(int m =0; m < M; m++){
            st = new StringTokenizer(in.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            go(d,s);
        }
        // 5. 출력은 1 * 1번 구슬 폭발개수 + 2 * 2번 구슬 폭발개수 + 3 * 3번 구슬 폭발 개수
        System.out.println(sum);
    }

    private static void go(int d, int s) {
        // 1. 블리자드 스킬 사용 (방향, 거리)
        useSkill(d, s);

        // 2. 빈 공간 구슬 당기기 -> list로 대체
        move();

        // 3. 연속한 구슬 폭발 및 구슬 당기기
        bomb();

        // 4. 구슬 변화 단계
        // 4-1 그룹별로 나누는데, 하나의 그룹은 두 개의 구슬 A와 B로 변한다.
        // 4-2 A는 그룹에 들어있는 구슬의 개수, B는 그룹을 이루고 있는 구슬의 번호(1,2,3)
        // 4-3 단 칸에 들어가지 못하는 경우는 그냥 사라진다.

        map = new int[N][N];
        divide();


    }

    private static void divide() {
        ArrayList<Integer> newList = new ArrayList<Integer>();

        int size = list.size();
        int connectCnt = 1;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                newList.add(connectCnt);
                newList.add(list.get(i));
                connectCnt = 1;
            }
        }

        if(size >=1) {
            newList.add(connectCnt);
            newList.add(list.get(size - 1));
        }


        // add
        int cnt = 0;
        size = 1;
        int corner = 0;
        int dir = 3; // 좌시작
        int nx = mid;
        int ny = mid;
        int time = newList.size();
        int total=0;
        while (time-- > 0) {
            cnt++;
            nx += delta[dir][0];
            ny += delta[dir][1];

            if (nx == 0 && ny == -1)
                break;

            map[nx][ny] = newList.get(total++);

            if (cnt == size) {
                dir = getDir(dir);
                corner++;
                cnt = 0;
            }

            if (corner == 2) {
                corner = 0;
                size++;
            }
        }
    }

    private static int getDir(int dir) {
        switch (dir){ // 순서 좌하우상 1234
            case 3:
                dir = 2;
                break;
            case 2:
                dir = 4;
                break;
            case 4:
                dir = 1;
                break;
            case 1:
                dir = 3;
        }
        return dir;
    }

    private static void bomb() {
        int[][] removeList = new int[1000][2];

        int size = list.size();
        int connectCnt = 1;
        int removeCnt = 0;
        boolean isRemove = false;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                if (connectCnt >= 4) {
                    removeList[removeCnt][0] = i - connectCnt + 1;
                    removeList[removeCnt][1] = connectCnt;
                    removeCnt++;
                    isRemove = true;
                }
                connectCnt = 1;
            }
        }
        // 마지막 처리
        if (connectCnt >= 4) {
            removeList[removeCnt][0] = size - connectCnt;
            removeList[removeCnt][1] = connectCnt;
            removeCnt++;
            isRemove = true;
        }

        if (isRemove) {
            for (int k = removeCnt - 1; k >= 0; k--) {
                int start = removeList[k][0];
                int len = removeList[k][1];

                for (int i = 0; i < len; i++) {
                    sum += list.get(start);
                    list.remove(start);
                }
            }
        }
        if (isRemove) {
            bomb();
        }
    }

    private static void move() {
        // 중앙에서 시작해서 좌하우상으로 도는데,
        // size만큼 가다가 코너를 두번 만나면 size를 증가 시켜준다.

        int cnt = 0;
        int size = 1;
        int corner = 0;
        int dir = 3; // 좌
        int nx = mid;
        int ny = mid;
        while (true){
            cnt++;
            nx += delta[dir][0];
            ny += delta[dir][1];

            if (nx == 0 && ny == -1)
                break;

            // 0이 아니면 list에 넣기
            if (map[nx][ny] !=0){
                list.add(map[nx][ny]);
            }

            if(cnt == size){
                // 현재 delta가 1부터 상하좌우
                dir = getDir(dir);
                corner++;
                cnt = 0;
            }

            if(corner == 2){ // 코너 두번 돌면
                corner = 0;
                size++;
            }

        }
    }

    private static void useSkill(int d, int s) {
        for(int i=1; i<=s; i++){
            int nx = mid + delta[d][0] * i;
            int ny = mid + delta[d][1] * i;

            if(!(nx>=0&& ny>=0 &&nx<N &&ny<N)) continue;

            map[nx][ny] = 0; // 스킬 닿은 부분 0으로 변경
        }
    }
}
