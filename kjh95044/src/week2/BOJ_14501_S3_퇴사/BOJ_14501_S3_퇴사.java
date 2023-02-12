package week2.BOJ_14501_S3_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14501_S3_퇴사 {
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int[][] map;
    static boolean[] isSelected;
    static int maxMoney;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        map = new int[T+1][2];
        isSelected = new boolean[T+1];

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] split = br.readLine().split(" ");
            map[test_case][0] = Integer.parseInt(split[0]);
            map[test_case][1] = Integer.parseInt(split[1]);
        }

        // 알고리즘 풀이 시작
        nextConsulting(1);
        sb.append(maxMoney + "\n");
        System.out.println(sb);
    }

    //
    private static void nextConsulting(int num) {
        // 기저부분
        if(num == T+1){
            int result = 0;
            for(int i=1; i<=T; i++){
                if(isSelected[i]){
                    result += map[i][1];
                }
            }

            maxMoney = Math.max(result, maxMoney);

            return;
        }

        // 유도 부분
        // 여기서 T + 1로 해줘야 하는데, T로 해서 한참 해맸다.
        if(map[num][0] + num <= T+1) {
            isSelected[num] = true;
            nextConsulting(map[num][0] + num);
        }
        else {
            nextConsulting(num+1);
        }
        isSelected[num] = false;
        nextConsulting(num+1);
    }
}