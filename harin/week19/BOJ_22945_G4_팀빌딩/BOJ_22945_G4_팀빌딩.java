import java.io.*;
import java.util.*;

public class BOJ_22945_G4_팀빌딩 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //개발자 수 입력
		int N = Integer.parseInt(in.readLine());
		
		//능력치 배열 입력
    StringTokenizer st = new StringTokenizer(in.readLine());
		int[] stat = new int[N];
		for(int i=0; i<N; i++) {
			stat[i] = Integer.parseInt(st.nextToken());
		}
		
		//투포인터를 사용해서 최대 능력치 계산
		int ans = Integer.MIN_VALUE;

		int startIdx=0; int endIdx=N-1;
		while(startIdx < endIdx) {
			int minStat = Math.min(stat[startIdx], stat[endIdx]); //A개발자와 B개발자의 최소 능력치 구함
			ans = Math.max((endIdx - startIdx - 1) * minStat, ans); //ans와 팀의 능력치 중 더 큰 값을 ans 변수에 대입

      // 능력치가 더 작은 것을 가리키는 포인터 값을 옮긴다!
			// 왜냐하면 최대값을 구하는 문제이기 때문에 ...
			if(stat[startIdx] < stat[endIdx]) startIdx++;
			else endIdx--;
		}
		
		System.out.println(ans);
	}

}
