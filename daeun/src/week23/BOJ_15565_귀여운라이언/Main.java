import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);
        
        int[] list = new int[N];
        split = br.readLine().split(" ");
        for(int i=0;i<N;i++) {
        	list[i] = Integer.parseInt(split[i]);
        }
        
        int cnt = 0;
        int result = Integer.MAX_VALUE;
        for(int left=1, right=1 ; left<N ; left++) {
        	while(right<N && cnt<K) {
        		if(list[right]==1) {
        			cnt++;
        		}
        		right++;
        	}

        	if(cnt==K) {
        		result = Math.min(result, right-left);
        	}
        	
        	if(list[left] == 1) {
        		cnt--;
        	}
        }
        
        if(result == Integer.MAX_VALUE) {
        	System.out.println(-1);
        }
        else {
        	System.out.println(result);
        }
    }
}
