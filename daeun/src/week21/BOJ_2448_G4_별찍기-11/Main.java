import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
      
        map = new char[N][N*2-1];
        for(int i=0;i<N;i++){
            Arrays.fill(map[i], ' ');
        }

        make(0, N-1, N);

        for(int i=0;i<N;i++){
            for(int j=0;j<N*2-1;j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void make(int i, int j, int n) {
        if(n == 3){
            map[i][j] = '*';
            map[i+1][j-1] = '*';
            map[i+1][j+1] = '*';
            map[i+2][j-2] = '*';
            map[i+2][j-1] = '*';
            map[i+2][j] = '*';
            map[i+2][j+1] = '*';
            map[i+2][j+2] = '*';
            return;
        }
        else{
            int half = n/2;
            make(i, j, half);
            make(i+half, j-half, half);
            make(i+half, j+half, half);
        }
    }
}
