package week19.BOJ_8983_G4_사냥꾼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int M,N,L;
	static int[] hunters;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		hunters = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		Arrays.sort(hunters);
		
		int result = 0;
		int x,y;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			if (y>L || x<hunters[0]-L || x>hunters[M-1]+L) continue;
			else {
				
				int idx = Arrays.binarySearch(hunters, x);
				if (idx>=0) result++;
				else {
					idx = (idx+1)*-1;
					if (idx<M && canShoot(x,y,hunters[idx])) {
						result++;
					}else if (idx-1>=0 && canShoot(x,y,hunters[idx-1])) {
						result++;
					}
				}				
			}			
		}
		
		System.out.println(result);
		
	}

	private static boolean canShoot(int x, int y, int hx) {
		if (Math.abs(x-hx)+y <= L) return true;
		else return false;
	}
	
}
