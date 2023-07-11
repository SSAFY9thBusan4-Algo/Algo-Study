import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 가수의 출연 순서를 정한다.
        // 각각 순서가 있기 때문에 위상 정렬로 푼다.

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N + 1];

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            int left = Integer.parseInt(st.nextToken());

            for (int j = 1; j < num; j++) {

                int right = Integer.parseInt(st.nextToken());

                list.get(left).add(right);
                inDegree[right] += 1;
                left = right;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

// test용 출력
//        if (queue.isEmpty()) {
//            System.out.println(0);
//        }
//
//        for (int i = 1; i < N; i++) {
//            System.out.println(list.get(i));
//        }

//        System.out.println(Arrays.toString(inDegree));

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append("\n");

            ArrayList<Integer> items = list.get(cur);

            for (int i = 0; i < items.size(); i++) {
                inDegree[items.get(i)]--;
                if (inDegree[items.get(i)] == 0) {
                    queue.offer(items.get(i));
                }
            }
        }


        boolean isCycle = false;
        for(int i=0; i< inDegree.length; i++){
            if(inDegree[i] != 0){
                isCycle = true;
                break;
            }
        }

        if(isCycle){
            System.out.println(0);
        }else {
            System.out.println(sb);
        }
    }

}
