import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, K, result, kit[], day[];
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        kit = new int[N];
        day = new int[N];
        visit = new boolean[N];

        split = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            kit[i] = Integer.parseInt(split[i]);
        }

        dfs(0);

        System.out.println(result);
    }

    //dfs로 모든 순서를 탐색하면서 찾음
    private static void dfs(int cnt) {
        if(cnt == N){
            int now = 500;
            boolean flag = true;
            for(int i=0;i<N;i++){
                //그날 운동량 더하고 K 빼고
                now += kit[day[i]] - K;
                if(now < 500){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result++;
            }
            return;
        }

        for(int i=0;i<N;i++){
            if(visit[i]){
                continue;
            }
            visit[i] = true;
            day[cnt] = i;

            dfs(cnt+1);

            visit[i] = false;
        }
    }
}
