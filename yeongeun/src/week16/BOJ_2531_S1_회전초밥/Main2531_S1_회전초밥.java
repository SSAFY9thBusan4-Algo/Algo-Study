package src.week16.BOJ_2531_S1_회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main2531_S1_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);	// 접시 수
		int d = Integer.parseInt(input[1]);	// 초밥 수
		int k = Integer.parseInt(input[2]);	// 연속해서 먹는 접시 수
		int c = Integer.parseInt(input[3]);	// 쿠폰 번호
		
		int[] numbers = new int[N];
		for(int i = 0 ; i < N ; i++) numbers[i] = Integer.parseInt(br.readLine());
		
		// 최대 가지수를 구해야 한다.
		// 1. k개에서 만들 수 있는 최대 가지수. (슬라이딩 윈도우로 구하기.)
		
		int maxcnt = 0;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int cnt = 0;
		boolean coupon = false;
		// 0. 전처리.k개 선택.
		for(int i = 0 ; i < k ; i++) {
			if(!map.containsKey(numbers[i])) {
				if(numbers[i] == c) coupon = true;
				cnt++;
				map.put(numbers[i], 1);
			}
			else map.put(numbers[i], map.get(numbers[i])+1);
		}
		// 처음 0부터 k까지.
		if(coupon) maxcnt = cnt > maxcnt ? cnt : maxcnt;
		else maxcnt = cnt+1 > maxcnt ? cnt+1 : maxcnt;

		// 1. 0~n까지. 정방향
		for(int i = k ; i < N ; i++) {
			// 한개 추가
			if(!map.containsKey(numbers[i])) {
				map.put(numbers[i], 1);
				cnt++;
				if(numbers[i] == c) coupon = true;
			}
			else map.put(numbers[i], map.get(numbers[i])+1);

			// 한개 제거
			if(map.get(numbers[i-k]) == 1) {
				map.remove(numbers[i-k]);
				cnt--;
				if(numbers[i-k] == c) coupon = false;
			}
			else map.put(numbers[i-k], map.get(numbers[i-k])-1);
			
			// 선택 다 되었을 때.
			if(coupon) maxcnt = cnt > maxcnt ? cnt : maxcnt;
			else maxcnt = cnt+1 > maxcnt ? cnt+1 : maxcnt;
			
		}
		
		// 2. 한바퀴 하고나서
		for(int i = 0 ; i < k ; i++) {
			// 한개 추가
			if(!map.containsKey(numbers[i])) {
				map.put(numbers[i], 1);
				cnt++;
				if(numbers[i] == c) coupon = true;
			}
			else map.put(numbers[i], map.get(numbers[i])+1);

			// 한개 제거
			if(map.get(numbers[N-k+i]) == 1) {
				map.remove(numbers[N-k+i]);
				cnt--;
				if(numbers[N-k+i] == c) coupon = false;
			}
			else map.put(numbers[N-k+i], map.get(numbers[N-k+i])-1);
			
			// 선택 다 되었을 때.
			if(coupon) maxcnt = cnt > maxcnt ? cnt : maxcnt;
			else maxcnt = cnt+1 > maxcnt ? cnt+1 : maxcnt;
		}
		
		System.out.println(maxcnt);
	}
}
