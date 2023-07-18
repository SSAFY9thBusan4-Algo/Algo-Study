package src.week20.BOJ_G4_점수따먹기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1749 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        // 입력과 동시에 0,0 에서부터 누적합을 저장.
        int[][] sum = new int[N+1][M+1];
        for(int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = 0;
            for(int j = 1 ; j <= M ; j++) {
                s += Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i-1][j] + s;
            }
        }

        // solve
        int result = Integer.MIN_VALUE;
        // (i,j)에서 (a,b)까지 영역의 합
        for(int i = 1 ; i <= N ; i++) {
            for(int j = 1 ; j <= M ; j++) {
                
            	for(int a = i ; a <= N ; a++) {
            		for(int b = j ; b <= M ; b++) {
            			
            			int s = sum[a][b] - sum[i-1][b] - sum[a][j-1] + sum[i-1][j-1];
            			if(s > result) result = s;
            		}
            	}
            }
        }

        System.out.println(result);
    }
}
