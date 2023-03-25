import java.io.*;
import java.util.*;

public class Main {
	
	static int[] gates;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		gates = new int[G+1];
		for(int i=1; i<=G; i++) {
			gates[i] = i;
		}
		
		int cnt = 0;
		while(P-- > 0) {
			int i = Integer.parseInt(br.readLine());
			int dockingGate = find(i); // i번째 비행기가 도킹할 수 있는 게이트 번호(부모를 가리킴)
			
			if(dockingGate == 0) break; // 0번째 게이트를 가르키고 있으면 도킹 불가

			union(dockingGate, dockingGate-1); // i번쨰 게이트에 도킹을 하고 i-1번쨰 게이트로 다음번 도킹 연결(유니온 파인드)
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static int find(int a) {
		if(gates[a] == a) return a; // 첫 도킹(본인 자리)
		
		return gates[a] = find(gates[a]); // union에 의해 하나씩 당겨진 게이트 번호
	}
	
	static void union(int a, int b) {
		if(find(a) != find(b)) gates[a] = b;
	}
}
