import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 2차원 배열로 만들어서 하면 되지 않을까?
		// 했지만... 메모리 초과가 떴다.
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // N은 동굴의 길이
		int H = Integer.parseInt(st.nextToken()); // H는 높이
		
		
		int[] bot = new int[H+2];
		int[] top = new int[H+2];

		// 같은 높이인 친구들을 각 구간에 합해준다.
		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(in.readLine())]++;
			top[H - Integer.parseInt(in.readLine()) + 1]++;
		}
		
//		System.out.println(Arrays.toString(bot));
//		System.out.println(Arrays.toString(top));

		// 누적합
		
		for (int i = 1; i <= H; i++) {
			bot[i] += bot[i - 1];
		}

		for (int i = H; i >= 1; i--) {
			top[i] += top[i + 1];
		}
		
//		System.out.println(Arrays.toString(bot));
//		System.out.println(Arrays.toString(top));

		int min = 200000;
		int minCnt = 0;

		// i의 석순의 개수는 bot[H] - bot[i-1]
		// i의 종유석의 개수는 top[H] - top[i+1]
		// 이 둘을 더하면 장애물 총 갯수
		
		for (int i = 1; i <= H; i++) {
			int obs = (bot[H] - bot[i - 1]) + (top[1] - top[i + 1]);

			if (obs < min) {
				min = obs;
				minCnt = 1;
			} else if (obs == min)
				minCnt++;
		}
		sb.append(min).append(" ").append(minCnt);
		System.out.println(sb);
		
//		HashMap<Integer, Integer> section = new HashMap<>();
//		boolean isStalagmite = true; // 석순인가?
//		
//		for(int i=0; i<N; i++) {
//			int height = Integer.parseInt(in.readLine());
//			
//			if(isStalagmite) {
//				for(int j =0; j<height; j++) {
//					section.put(j, section.getOrDefault(j, 0) + 1);
//				}
//			}
//			else {
//				for(int j =0; j<height; j++) {
//					section.put(H-j-1, section.getOrDefault(H-j-1, 0) + 1);
//				}
//			}
//			
//			isStalagmite = !isStalagmite;
//		}
//		
//		
//		int min = 10000000;
//		
//		for(int key : section.keySet()) {
//			min = Math.min(min, section.get(key));
//		}
//		
//		int minCnt = 0;
//		
//		for(int key : section.keySet()) {
//			if(min == section.get(key)) {
//				minCnt ++;
//			}
//		}
//		
//		sb.append(min).append(" ").append(minCnt);
//		System.out.print(sb);
	}
}
