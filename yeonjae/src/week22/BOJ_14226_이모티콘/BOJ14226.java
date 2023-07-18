import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ14226 {

    static int S, ans;
    static int[][] visited;

    static class Node {
        int dis, clip;

        public Node(int dis, int clip) {
            this.dis = dis;
            this.clip = clip;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(in.readLine());
        visited = new int[S * 2 + 1][S];

        visited[1][0] = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0));
        while(!q.isEmpty()) {
            Node node = q.poll();
            int nowDis = node.dis;
            int nowClip = node.clip;
            int nowCnt = visited[nowDis][nowClip];

            if(nowDis == S) {
                ans = nowCnt;
                break;
            }

            // 1번 연산
            // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if(nowDis < S && visited[nowDis][nowDis] == 0) {
                visited[nowDis][nowDis] = nowCnt + 1;
                q.add(new Node(nowDis, nowDis));
            }

            // 2번 연산
            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            if(nowDis + nowClip <= 2 * S && visited[nowDis + nowClip][nowClip] == 0) {
                visited[nowDis + nowClip][nowClip] = nowCnt + 1;
                q.add(new Node(nowDis + nowClip, nowClip));
            }

            // 3번 연산
            // 화면에 있는 이모티콘 중 하나를 삭제한다.
            if(nowDis - 1 > 0 && visited[nowDis - 1][nowClip] == 0) {
                visited[nowDis - 1][nowClip] = nowCnt + 1;
                q.add(new Node(nowDis - 1, nowClip));
            }
        }

        System.out.println(ans - 1);
    }
}
