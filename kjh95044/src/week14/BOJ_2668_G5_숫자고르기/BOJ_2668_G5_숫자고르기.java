import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N, map[], result;
    static boolean[] check; // 뽑힌 정수 출력하기 위한 check

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N+1];
        check = new boolean[N+1];
        for(int i=1; i<=N; i++){
            map[i] = Integer.parseInt(in.readLine());
        }

        for (int i=1; i<=N; i++){
            cycle(map[i], i);
        }

        sb.append(result).append("\n");
        for(int i=1; i<=N; i++){
            if(check[i]){
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void cycle(int node, int start) {
        for (int i = 1; i <= N; i++) {
            if (node == start) { // 사이클이 생기면 result를 하나 증가시켜준다.
                result++;
                check[node] = true; // 뽑힌 정수 출력하기 위해 check 해준다.
                return;
            }
            node = map[node]; // map을 돌면서 node가 start와 같아지면 사이클이 있다고 본다.
        }
    }
}
