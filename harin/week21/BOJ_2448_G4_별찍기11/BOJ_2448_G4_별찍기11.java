import java.io.*;
import java.util.*;

public class BOJ_2448_G4_별찍기11 {

    static char [][] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        arr = new char[N][2*N - 1];
        for(int i=0; i<N; i++) {
            for(int j=0; j<2*N-1; j++) {
                arr[i][j] = ' ';
            }
        }

        //별찍기 시작
        makeStar(0, N-1, N);

        //찍은 별 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<2*N - 1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
        
    }

    //재귀로 별찍기
    private static void makeStar(int r, int c, int n) {
        if(n==3) {
            arr[r][c] = '*';
            arr[r+1][c-1] = arr[r+1][c+1] = '*';
            arr[r+2][c-2] = arr[r+2][c-1] = arr[r+2][c] = arr[r+2][c+1] = arr[r+2][c+2] = '*';

            return;
        }
      
        int size = n/2;
        makeStar(r, c, size);
        makeStar(r+size, c-size, size);
        makeStar(r+size, c+size, size);
    }
}
