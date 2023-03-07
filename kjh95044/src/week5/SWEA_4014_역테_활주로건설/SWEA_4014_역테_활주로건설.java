package week5.SWEA_4014_역테_활주로건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_역테_활주로건설 {

    static StringBuilder sb = new StringBuilder();
    static int result;
    static int N, X, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            result = 0;
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 풀이 시작
            // 가로 방향, 세로 방향 2가지 반복문을 돌린다.
            // 한 방향으로 갈때 조건에 걸리지 않고 한 방향 끝까지 이동하면 카운트 증가
            // 조건은 다음 인접한 칸의 높이가 다를때 경사로를 설치 할수 없다면 continue
            // 경사로는 높이가 1차이 나면서 낮은곳에 x칸이 있을때 설치 할수 있다.

            countResult(true); // 가로
            countResult(false); // 세로


            sb.append(result);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void countResult(boolean isRow) {
        for (int i = 0; i < N; i++) {
            boolean canInstall = true; // 활주로 설치 가능한지 여부
            boolean isInstalling = false; // 경사로를 뒤에 설치 할때는 앞으로 나올 높이를 확인 해야하기 때문에 설치중인지 여부를 표현
            int prevCnt = 1;
            for (int j = 1; j < N; j++) {

                int prev = isRow ? map[i][j - 1] : map[j - 1][i];
                int post = isRow ? map[i][j] : map[j][i];

                int diff = Math.abs(post - prev);

                if (diff > 1) {
                    canInstall = false;
                    break;
                } else if (diff == 1) {
                    if (isInstalling) { // 뒤에 경사로 설치 하려는데 다른 높이를 만나면 종료
                        canInstall = false;
                        break;
                    }

                    if (prev < post) { // 뒤에꺼가 더 크면 앞에 경사로 설치 가능
                        if (prevCnt < X) { // 설치 불가능
                            canInstall = false;
                            break;
                        }
                    } else { // 앞에꺼가 더 클떄는 뒤에 경사로 설치 가능
                        isInstalling = true;
                        if(N-j <X){
                            canInstall = false;
                            break;
                        }
                    }
                    prevCnt = 1;

                } else {
                    prevCnt++;
                    if (isInstalling && prevCnt == X) { // 설치중이고, 경사로 길이만큼 공간이 있을때 설치 종료
                        isInstalling = false;
                        prevCnt = 0;
                    }
                }

            }
            if (canInstall)
                result++;
        }
    }
}