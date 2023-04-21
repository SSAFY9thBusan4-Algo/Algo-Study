import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>(n);
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            list.add(new int[] {start, end});
        }
        
        // 시작을 기준으로 정렬
        Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
        
        // 끝을 기준으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
 			@Override
 			public int compare(int[] o1, int[] o2) {
 				return Integer.compare(o1[1], o2[1]);
 			}
 		});
        
        int ans = 0;
        
        // 강의실의 시작시간 순서대로 시작
        for(int[] i: list) {
        	// 끝나는 시간기준으로 정렬되있으므로 끝나는 시간이 현재 수업의 시작시간보다 짧다면 제거
        	if(!pq.isEmpty()) while(pq.peek()[1] <= i[0]) pq.poll();
        	
        	pq.offer(i);
        	
        	ans = Math.max(ans, pq.size());
        }
        
        System.out.println(ans);
    }
}
