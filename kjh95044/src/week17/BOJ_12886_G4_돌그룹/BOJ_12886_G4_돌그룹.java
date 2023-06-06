import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static boolean [][] visited = new boolean[1501][1501];
    static int sum;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        sum = a + b + c;

        dfs(a,b);
        System.out.println(answer);
    }

    private static void dfs(int a, int b) {
        int c = sum - a - b;
        if(a==b && b==c){
            answer = 1;
            return;
        }

        nextStep(a,b);
        nextStep(b,c);
        nextStep(a,c);

    }


    private static void nextStep(int a, int b) {
        int max = Math.max(a,b);
        int min = Math.min(a,b);

        // 하나라도 방문 안 한게 있으면 출동
        if(!(visited[min*2][max-min] && visited[max-min][min*2])) {
            visited[min*2][max-min] = visited[max-min][min*2] = true;
            dfs(min*2,max-min);
        }
    }

}
