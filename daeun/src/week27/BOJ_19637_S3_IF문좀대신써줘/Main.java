import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        String[][] name = new String[N][2];
        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            name[i][0] = split[0];
            name[i][1] = split[1];
        }

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            int start = 0, end = N - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int temp = Integer.parseInt(name[mid][1]);
                if (temp < num) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            sb.append(name[start][0] + "\n");
        }
        System.out.println(sb);
    }
}
