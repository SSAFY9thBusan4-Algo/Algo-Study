import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int[][] map = new int[N+1][M+1];
        for(int i=1;i<N+1;i++){
            split = br.readLine().split(" ");
            for(int j=1;j<M+1;j++){
                map[i][j] = Integer.parseInt(split[j-1]);
            }
        }
        
        int[][] sum = new int[N+1][M+1]; //구간 누적 합
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                sum[i][j] = map[i][j]+sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1];
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=1;i<N+1;i++){
            for(int j=1;j<M+1;j++){
                for(int k=i;k<N+1;k++){
                    for(int l=j;l<M+1;l++){
                        result = Math.max(result, sum[k][l]-sum[i-1][l]-sum[k][j-1] + sum[i-1][j-1]);
                    }
                }
            }
        }

        System.out.println(result);
    }
}
