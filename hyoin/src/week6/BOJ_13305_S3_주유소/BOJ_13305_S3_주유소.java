package week6.BOJ_13305_S3_주유소;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305_S3_주유소 {

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] distance = new int[N-1]; //  인접한 두 도시를 연결하는 도로의 길이
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N - 1; i++) {
			distance[i]= Integer.parseInt(st.nextToken());
		}

		// 리터당 가격이 제일 작은 곳에서 최대한 주유해야함
		// 첫번째 도시부터 리터당 가격을 비교하면서 최소값을 사용해서 해당 거리만큼 주유
		st = new StringTokenizer(in.readLine());
		long money=0; // 주유소의 리터당 가격
		long min = Long.MAX_VALUE; // 리터당 최소 가격
		long result = 0; // 최종비용
		for (int i = 0; i < N; i++) {
			money= Long.parseLong(st.nextToken());
			if(min>money) { // 현재 도시의 리터당 가격이 min보다 작으면 현재 도시에서 주유
				min=money;
			}
			if(i!=N-1) // 마지막 도시는 주유하지 않으므로 제외하고 주유
				result+=distance[i]*min;
		}
		
		sb.append(result);

		System.out.println(sb);
	}
}
