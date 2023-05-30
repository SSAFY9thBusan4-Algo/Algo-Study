import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]); //접시 수
		int d = Integer.parseInt(split[1]); //초밥 가짓수
		int k = Integer.parseInt(split[2]); //연속 접시 수
		int c = Integer.parseInt(split[3]); //쿠폰 번호
		
		int[] num = new int[N];
		for(int i=0;i<N;i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		int cnt = 0;
		int[] eat = new int[d+1];
		for(int i=0;i<k;i++) { //첫 k개 담기
			if(eat[num[i]]==0) {
				cnt++;
			}
			eat[num[i]]++;
		}
		
		int result = cnt;
		for(int i=0;i<N;i++) {
			if(result <= cnt) {
				if(eat[c] == 0) {
					result = cnt+1; //쿠폰 사용
				}
				else {
					result = cnt;
				}
			}
			
			//앞은 빼고
			eat[num[i]]--;
			if(eat[num[i]]==0) {
				cnt--;
			}
      
			//뒤는 더하고
			if(eat[num[(i+k)%N]]==0) {
				cnt++;
			}
			eat[num[(i+k)%N]]++;
		}
		System.out.println(result);
	}
}
