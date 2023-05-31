import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호(무료)
		
		int[] arr = new int[n];
		int[] sushi = new int[d+1];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
	
		int cnt = 0;
		// 처음 윈도우 초기화
		for(int i=0; i<k; i++) {
			if(sushi[arr[i]] == 0) cnt++;
			
			sushi[arr[i]]++;
		}
		
		int ans = cnt;
		// 슬라이딩 윈도우 
		for(int i=1; i<n; i++) {
			// 윈도우 이동
			if(sushi[arr[(i+k-1) % n]] == 0) cnt++;
			sushi[arr[(i+k-1) % n]]++;
			
			sushi[arr[i-1]]--;
			if(sushi[arr[i-1]] == 0) cnt--;
			
			// 최대 가짓수 갱신
			if(ans <= cnt) {
				if(sushi[c] == 0) ans = cnt+1;
				else ans = cnt;
			}
		}
		
		System.out.println(ans);
	}
}
