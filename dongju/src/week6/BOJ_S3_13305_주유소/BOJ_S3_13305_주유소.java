import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int n = Integer.parseInt(br.readLine());
	    
	    int[] road = new int[n-1];
	    int[] gasStation = new int[n];

	    st = new StringTokenizer(br.readLine());
	    for(int i=0; i<n-1; i++) {
	    	road[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0; i<n; i++) {
	    	gasStation[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    
	    long cnt = 0; // 해당 주유소
	    long ans = 0;
	  loop: for(int i=0; i<n-1; i++) { // 해당 주유소 부터
	    	if(cnt > 0) {
	    		cnt--;
	    		continue;
	    	}

	    	long restDistance = 0; // 남은 거리 계산
	    	for(int j=i+1; j<n; j++) { // 다음 주유소 부터 마지막 주유소 까지 탐색
	    		restDistance += road[j-1]; // 거리 체크
	    		
	    		if(gasStation[i] > gasStation[j]) { // 더 싸거나 같은 주유소가 있다면 그 거리까지 우선 구매
	    			cnt = j-i;
	    			ans += (gasStation[i] * restDistance);
                    cnt--;
	    			continue loop;
	    			}
	    		}
	    	
	    	if(cnt == 0) {
	    		ans += (gasStation[i] * restDistance);
	    		break;
	    	}
	    }
	   
	    System.out.println(ans);
	}
}