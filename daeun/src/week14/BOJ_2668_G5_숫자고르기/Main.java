import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N+1];
		for(int i=1;i<N+1;i++) {
			num[i] = Integer.parseInt(br.readLine());			
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] visit;
		
		for(int i=1;i<N+1;i++) {
			visit = new boolean[N+1];
			
			if(i==num[i]) { //같은 숫자일때
				visit[i] = true;
				list.add(i);
				continue;
			}
			if(!visit[i]) { //앞에서 이미 진행된 싸이클은 안하고
				visit[i] = true;
				int next = num[i];
				
				while(!visit[next]) { //next를 다음 것으로 계속 바꾸면서 방문 위치에 도착할때까지 반복
					visit[next] = true;
					next = num[next];
				}
				if(next == i) { //끝났을 때 처음으로 돌아오면
					list.add(i);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
	}
}
