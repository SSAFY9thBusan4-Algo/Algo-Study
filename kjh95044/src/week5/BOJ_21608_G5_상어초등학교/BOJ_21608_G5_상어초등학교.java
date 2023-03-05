package week5.BOJ_21608_G5_상어초등학교;
import java.io.*;
import java.util.*;

public class BOJ_21608_G5_상어초등학교 {

	static StringBuilder sb = new StringBuilder();
    static int[][] map;

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        // 빈칸 모두 탐색 (빈칸 없으면 종료) 하여 이 학생이 들어갈 위치를 찾는다.
        // 빈 칸중 상하좌우에 좋아하는 학생 수 카운트, 이전 칸보다 학생수 많으면 위치 갱신
        // 이전에 저장한 칸의 학생수랑 같다면 주변 빈칸수 카운트, 이전에 저장한 칸의 빈칸수보다 많다면 위치 갱신
        // 왼쪽위에서 오른쪽 아래로 탐색하며 갱신 할 것이기 때문에 3번 조건은 신경 쓰지 않아도 된다.

        map = new int[N + 1][N + 1];
        int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
        HashMap<Integer,List<Integer>> likesMap = new HashMap<>();

        // 입력과 동시에 풀이 시작
        for (int t = 1; t <= N * N; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int id = Integer.parseInt(st.nextToken());
            List<Integer> likes = new ArrayList<>();
            likes.add(Integer.parseInt(st.nextToken()));
            likes.add(Integer.parseInt(st.nextToken()));
            likes.add(Integer.parseInt(st.nextToken()));
            likes.add(Integer.parseInt(st.nextToken()));
            likesMap.put(id, likes);

            // 0으로 초기화해서 문제가 생겼었다.
            int adjCnt = -1;
            int blankCnt = -1;
            int x = 0;
            int y = 0;

            // 빈칸 모두 탐색
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(map[i][j] != 0){
                        continue;
                    }

                    int tempBlankCnt = 0; // 빈칸 카운트
                    int tempAdjCnt = 0; // 인접 like 학생 카운트

                    // 인접한 네방향 탐색후 빈칸과 like 학생이 있는지 확인후 카운트
                    for(int d=0; d<4; d++){
                        int nx = i + delta[d][0];
                        int ny = j + delta[d][1];

                        if(!(nx>=1 && ny>=1 && nx<=N && ny<=N)){
                            continue;
                        }

                        if(map[nx][ny] == 0){
                            tempBlankCnt ++;
                        } else if (likes.contains(map[nx][ny])) {
                            tempAdjCnt ++;
                        }
                    }

                    // 원래 저장된 값보다 더 적절한 자리가 생기면 갱신
                    // 인접 like 학생이 더 많거나 혹은 like 학생수가 같고 빈칸수가 더 많으면 갱신
                    if(adjCnt < tempAdjCnt){
                        adjCnt = tempAdjCnt;
                        blankCnt = tempBlankCnt;
                        x = i;
                        y = j;
                    } else if (adjCnt==tempAdjCnt && blankCnt<tempBlankCnt) {
                        blankCnt = tempBlankCnt;
                        x = i;
                        y = j;
                    }

                }
            }

            // 빈칸 모두 탐색후 갱신된 위치에 학생을 넣어준다.
            map[x][y] = id;

        }

        int result = 0;
        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= N ; j++) {
                int tempAdjCnt = 0; // 인접 like 학생 카운트

                // 인접한 네방향 탐색후 빈칸과 like 학생이 있는지 확인후 카운트
                for(int d=0; d<4; d++){
                    int nx = i + delta[d][0];
                    int ny = j + delta[d][1];

                    if(!(nx>=1 && ny>=1 && nx<=N && ny<=N)){
                        continue;
                    }

                    List<Integer> list = likesMap.getOrDefault(map[i][j], null);

                    if (list!=null && list.contains(map[nx][ny])) {
                        tempAdjCnt ++;
                    }
                }

                // 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000
                switch (tempAdjCnt){
                    case 1:
                        result += 1;
                        break;
                    case 2:
                        result += 10;
                        break;
                    case 3:
                        result += 100;
                        break;
                    case 4:
                        result +=1000;
                        break;
                }
            }
        }

        sb.append(result);
        System.out.println(sb);
    }
}
