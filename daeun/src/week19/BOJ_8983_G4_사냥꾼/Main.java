import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int M, L, gun[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String[] split = br.readLine().split(" ");
		M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);
		L = Integer.parseInt(split[2]);
		
		split = br.readLine().split(" ");
		gun = new int[M];
		for(int i=0;i<M;i++) { //사대 위치
			gun[i] = Integer.parseInt(split[i]);
		}
		Arrays.sort(gun); //이분 탐색을 위해

		int result = 0, x = 0, y = 0;
		for(int i=0;i<N;i++) { //동물 위치
			split = br.readLine().split(" ");
			x = Integer.parseInt(split[0]);
			y = Integer.parseInt(split[1]);
			if(possible(x, y)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	private static boolean possible(int x, int y) {
		int start = 0;
		int end = M - 1;
		int mid = 0;
		int distance = 0;
		
		while(start <= end) {
			mid = (start+end)/2;
			distance = Math.abs(x-gun[mid])+y;
			
			if(distance <= L) { //사정거리 내
				return true;
			}
			
			//동물이랑 x좌표가 같은 경우
			if(x == gun[mid]) {
				return false;
			}
			
			//동물이 앞/뒤에 있는 경우
			if(x < gun[mid]) {
				end = mid - 1;
			}
			else if(x > gun[mid]) {
				start = mid + 1;
			}
		}
		
		return false;
	}
}
