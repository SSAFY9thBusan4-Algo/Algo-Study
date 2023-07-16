import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_2448_G4_별찍기11 {

    public static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        stars = new char[N][N*2-1];

        for(int i=0; i<N; i++){
            Arrays.fill(stars[i],' ');
        }
        
        // 삼각형을 3개로 쪼개서 제일 작은 삼각형이 나올때까지 반복
        star(0, N-1, N);;

        for(int i=0; i<N; i++){
            for(int j=0; j<2*N-1; j++){
                sb.append(stars[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void star(int r, int c, int N) {
        if(N==3){ // 제일 작은 삼각형
            stars[r][c] = '*';
            stars[r + 1][c - 1] = stars[r + 1][c + 1] = '*';
            stars[r + 2][c - 2] = stars[r + 2][c - 1] = stars[r + 2][c] = stars[r + 2][c + 1] = stars[r + 2][c + 2] = '*';
            return;
        } else { // 삼각형 세개로 분리
            int cut = N / 2;
            star(r, c, cut); // 제일 위 삼각형
            star(r + cut, c - cut, cut); // 아래 왼쪽 삼각형
            star(r + cut, c + cut, cut); // 아래 오른쪽 삼각형
        }
    }
}
