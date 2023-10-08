package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1138 {

    static int N;
    static String[] ss;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        ss = in.readLine().split(" ");

        numbers = new int[N];
        for(int i = 0; i < N; i++) {
            int left = Integer.parseInt(ss[i]);
            int cnt = 0;

            for(int j = 0; j < N; j++) {
                if(cnt == left) {
                    if(numbers[j] == 0) {
                        numbers[j] = i + 1;
                        break;
                    }
                }
                else {
                    if(numbers[j] == 0)
                        cnt++;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            sb.append(numbers[i]).append(' ');
        }

        System.out.println(sb);
    }
}
