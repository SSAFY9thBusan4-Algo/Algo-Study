import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        boolean[] visited = new boolean[N+1];
        int result = 0;

        for (int i = 1; i <= N; i++) { // 모든 노드의 인접 리스트를 탐색한다.
            if(!visited[i]){ // 방문 하지 않았다면 방문 처리를 해준다.
                visited[i] = true;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);

                result ++; // 아래에서 인접한 노드를 모두 방문 처리 하기 때문에 여기는 새로운 그룹이다.

                while(!queue.isEmpty()){ // 노드의 인접한 노드들을 모두 방문 처리 해준다.
                    int now = queue.poll();
                    for (int j = 0; j < adjList.get(now).size(); j++) {
                        int adjNow = adjList.get(now).get(j);
                        if(!visited[adjNow]){
                            visited[adjNow] = true;
                            queue.offer(adjNow);
                        }

                    }
                }
            }
        }

        System.out.println(result);
    }
}
