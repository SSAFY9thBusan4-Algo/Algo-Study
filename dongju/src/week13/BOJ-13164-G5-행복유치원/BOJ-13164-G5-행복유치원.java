import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 원생의 수
		int k = Integer.parseInt(st.nextToken()); // 조의 개수
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] diff = new int[n-1]; // 인접해 있는 원생의 키 차이
		for(int i=0; i<n-1; i++) {
			diff[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(diff);

		/* 
		 * n개의 원생에서 k개의 조를 만들어야 하므로
		 * 10명의 원생으로 4개의 조를 만든다 하면 한 조에 최소 한명씩 넣어야하므로
		 * 조에 들어가지 못한 6(n-k)명이 남는다
		 * 티셔츠 비용은 각 조의 키차이 이므로 저장해놓은 키차이 배열을 이용해서 값을 더해준다
		 */
		
		int ans = 0;
		for(int i=0; i<n-k; i++) { 
			ans += diff[i];
		}
		
		System.out.println(ans);
	}
}
