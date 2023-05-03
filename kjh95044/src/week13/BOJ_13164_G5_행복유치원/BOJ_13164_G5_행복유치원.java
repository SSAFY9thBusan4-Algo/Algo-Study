import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] map = new int[N];
		int result = 0;
		
		st= new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1. N명의 원생을 k조로 나눠야한다.
		// 2. 각 조별로 가장 키가 큰 원생과 가장 키가 작은 원생의 차이가 비용이 된다.
		// 3. 최소 비용은 얼마인가?
		
		List<Integer> list = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            list.add(map[i] - map[i - 1]);
        }
 
        Collections.sort(list);
 
        for (int i = 0; i < N - K; i++) {
            result+=list.get(i);
        }
        
        System.out.println(result);
		
	}
	

}
