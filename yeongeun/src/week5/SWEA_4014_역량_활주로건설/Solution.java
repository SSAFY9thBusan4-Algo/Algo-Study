package src.week5.SWEA_4014_역량_활주로건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // input
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int X = Integer.parseInt(input[1]);

            int[][] map = new int[N][N];
            for(int i = 0 ; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            // =======================================
            // solve

            int result = 0;

            // 1. 가로방향
            for(int i = 0 ; i < N ; i++) {
                boolean flag = true;    // 이번 행에서 활주로 설치가 가능한가.

                int prenum = map[i][0];
                int precnt = 1;

                for(int j = 1 ; j < N ; j++) {
                    // 1. 이전 값과 값이 같은가
                    if(map[i][j] == prenum) precnt++;
                    else {  // 2. 다르다
                        // 오르막
                        if(map[i][j] - prenum == 1) {
                            // 이전에 땅이 연속해서 X개만큼 있어야 한다.
                            if(precnt < X) {
                                flag = false;
                                break;
                            }

                            prenum = map[i][j];
                            precnt = 1;
                        }

                        // 내리막
                        else if(prenum - map[i][j] == 1) {
                            //X개만큼 연속해서 같은 땅이 있어야 한다.
                            int cnt = 0;
                            while(++cnt < X) {
                                if(j+cnt == N || map[i][j] != map[i][j + cnt]) {
                                    flag = false;
                                    break;
                                }
                            }
                            if(!flag) break;

                            // 설치 가능하면
                            j += cnt-1;
                            prenum = map[i][j];
                            precnt = 0; // 갯수는 다음거부터 카운트 해야한다.
                        }

                        // 높이가 1 을 넘는 경우. 활주로 설치가 불가능하다.
                        else {
                            flag = false;
                            break;
                        }
                    }
                }
                //System.out.println(i+" : "+flag);
                if(flag) result++;
            }



            // 1. 세로방향
            for(int i = 0 ; i < N ; i++) {
                boolean flag = true;    // 이번 열에서 활주로 설치가 가능한가.

                int prenum = map[0][i];
                int precnt = 1;

                for(int j = 1 ; j < N ; j++) {
                    // 1. 이전 값과 값이 같은가
                    if(map[j][i] == prenum) precnt++;
                    else {  // 2. 다르다
                        // 오르막
                        if(map[j][i] - prenum == 1) {
                            // 이전에 땅이 연속해서 X개만큼 있어야 한다.
                            if(precnt < X) {
                                flag = false;
                                break;
                            }

                            prenum = map[j][i];
                            precnt = 1;
                        }

                        // 내리막
                        else if(prenum - map[j][i] == 1) {
                            //X개만큼 연속해서 같은 땅이 있어야 한다.
                            int cnt = 0;
                            while(++cnt < X) {
                                if(j+cnt == N || map[j][i] != map[j + cnt][i]) {
                                    flag = false;
                                    break;
                                }
                            }

                            if(!flag) break;

                            // 설치 가능하면
                            j += cnt - 1;
                            prenum = map[j][i];
                            precnt = 0; // 갯수는 다음거부터 카운트 해야한다.
                        }

                        // 높이가 1 을 넘는 경우. 활주로 설치가 불가능하다.
                        else {
                            flag = false;
                            break;
                        }
                    }
                }

                //System.out.println(i+" : "+flag);
                if(flag) result++;
            }



            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }

        System.out.println(sb);
    }
}
