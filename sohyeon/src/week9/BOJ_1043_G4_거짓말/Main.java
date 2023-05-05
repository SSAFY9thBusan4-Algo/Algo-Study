package week9.BOJ_1043_G4_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	private static Map<Integer, Boolean> map = new HashMap();
	private static Set<Integer> knowT = new HashSet<>();
	private static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		parent = new int[N+1];
		for (int i=1; i<=N; i++) {
			parent[i] = i;
			map.put(i, false);
		}
		
		// 진실을 아는 사람을 키로 하는 값을 true로 바꾸기
		int[] pt = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
		for (int i=1; i<=pt[0]; i++) {
			map.replace(pt[i], true);
			knowT.add(pt[i]);
		}
		
		// 파티에 온 사람들 리스트에 담기
		List<Integer>[] parties = new List[M];
		for (int i=0; i<M; i++) {
			parties[i] = new ArrayList<>();
			int[] party = Arrays.stream(in.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
			for (int p=1; p<=party[0]; p++) {
				parties[i].add(party[p]);
			}
//			System.out.println(parties[i].toString());
		}
		
		// 같은 파티에 참여하는 사람끼리 union
		for (List party : parties) {
			for (int i=0; i<party.size(); i++) {
				for (int j=i+1; j<party.size(); j++) {
					int p1 = (int) party.get(i);
					int p2 = (int) party.get(j);
					
					union(p1, p2);
				}
			}
		}
		
		// 파티에 참여하는 사람이 진실을 아는 사람이면 pass,
		// 파티에 참여하는 사람이 진실을 아는 사람과 같은 부모를 가지면 pass
		// 위에 해당하지 않으면 result+1
		int result = 0;
		for (List party : parties) {
			boolean flag = true;
			for1:
			for (int i=0; i<party.size(); i++) { 
				int p = (int) party.get(i);
				if (map.get(p)) {
					flag = false;
					break;
				}
				else {
					for (int t : knowT) {
						if (findset(p) == findset(t)) {
							flag = false;
							break for1; 
						}
					}
				}
			}
			if (flag) result += 1;
		}
		
		System.out.println(result);
		
	}

	private static void union(int p1, int p2) {
		int r1 = findset(p1);
		int r2 = findset(p2);
		
		if (r1 != r2) {
			parent[r2] = r1;
		}
	}

	private static int findset(int p) {
		if (parent[p] == p) {
			return p;
		}
		
		return parent[p] = findset(parent[p]);
	}
	
}
