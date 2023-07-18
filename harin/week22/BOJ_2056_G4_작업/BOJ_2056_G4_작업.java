import java.io.*;
import java.util.*;
public class BOJ_2056_G4_작업 {

    static int N;
    static int[] inDegree; // 진입차수
    static ArrayList<ArrayList<Integer>> list;
    static int[] time;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(in.readLine());
        list = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        inDegree = new int[N+1];
        time = new int[N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(in.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++) {
                int work = Integer.parseInt(st.nextToken());
                list.get(work).add(i);

                inDegree[i]++;
            }
        }

        ans = topology();
        System.out.println(ans);
    }

    private static int topology() {
        Queue<Integer> queue = new ArrayDeque<>();

        int[] result = new int[N+1];
        for(int i=1; i<=N; i++) {
            result[i] = time[i];

            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int work : list.get(cur)) {
                inDegree[work]--;

                result[work] = Math.max(result[work], result[cur] + time[work]);

                if(inDegree[work] == 0) {
                    queue.offer(work);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            ans = Math.max(ans, result[i]);
        }

        return ans;
    }
}
