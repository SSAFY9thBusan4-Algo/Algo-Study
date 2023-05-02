import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int H = Integer.parseInt(split[1]);

		int[] up = new int[H+1];
		int[] down = new int[H+1];
		for(int i=0;i<N;i++) {
			int temp = Integer.parseInt(br.readLine());
			if(i%2==0) { //아래
				down[temp]++;
			}
			else { //위
				up[H-temp]++;
			}
		} 
		
		//앞에서 높이별로 더했으니까 연결되어 있는 부분들에 더해주기
		for(int i=1;i<H+1;i++) {
			down[i] += down[i-1];
		}
		for(int i=H;i>0;i--) {
			up[i-1] += up[i];
		}
		
		//개수 세기
		int min = N;
		int result = 0;
		for(int i=1;i<H+1;i++) {
			int now = down[H] - down[i-1] + up[1] - up[i];
			if(now<min) {
				min = now;
				result = 1;
			}
			else if(now==min) {
				result++;
			}
		}
		
		System.out.println(min+" "+result);
	}
}
