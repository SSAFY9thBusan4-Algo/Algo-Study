import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	static int N, time[], in[], dp[];
	static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N+1];
        in = new int[N+1];
        dp = new int[N+1];
        list = new ArrayList[N+1];
        
        for(int i=1;i<N+1;i++) {
        	list[i] = new ArrayList<>();
        	String[] split = br.readLine().split(" ");
        	time[i] = Integer.parseInt(split[0]);
        	in[i] = Integer.parseInt(split[1]);
        	
        	for(int j=0;j<in[i];j++) {
        		list[Integer.parseInt(split[2+j])].add(i);
        	}
        }
        
        topology();
        
        int result = 0; //이걸 밑에서 하니까 모든 작업에 선행 관계가 없을 때 틀린다!
        for(int i=1;i<N+1;i++) {
			result = Math.max(dp[i], result);
        }

        System.out.println(result);
    }
    
	private static void topology() {
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1;i<N+1;i++) {
			if(in[i] == 0) {
				queue.offer(i);
				dp[i] = time[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int i=0;i<list[now].size();i++) {
				int next = list[now].get(i);
				dp[next] = Math.max(dp[now]+time[next], dp[next]);
				in[next]--;
				
				if(in[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}

}
