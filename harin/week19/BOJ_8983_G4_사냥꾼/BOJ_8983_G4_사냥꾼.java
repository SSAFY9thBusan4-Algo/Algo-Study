import java.io.*;
import java.util.*;

public class BOJ_8983_G4_사냥꾼 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken()); //사대의 수
		int N = Integer.parseInt(st.nextToken()); //동물의 수
		int L = Integer.parseInt(st.nextToken()); //총 사정거리

		int sadaes[] = new int[M]; // 사대 저장 배열
		int animals[][] = new int[N][2]; // 동물 위치 저장 배열

		//사대 입력 받기
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<M; i++) {
			sadaes[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sadaes); //오름차순 정렬

		//동물 정보 입력 받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			animals[i][0] = Integer.parseInt(st.nextToken());
			animals[i][1] = Integer.parseInt(st.nextToken());
		}

		//2차원 배열 정렬 
		Arrays.sort(animals, (o1, o2) -> {
			if(o1[0]-o2[0] == 0) return o1[1] - o2[1];
			return o1[0]-o2[0];
		});

		int ans = 0;
		int minX = sadaes[0] - L;// 제일 작을 수 있는 x축 범위
		int maxX = sadaes[M-1] + L; // 제일 클 수 있는 x축 범위
		int maxY=L;	// 제일 클 수 있는 y축 범위
		
		int cur = 0;
		for(int i=0; i<N; i++) { //동물 수 만큼 반복
			int x = animals[i][0]; //동물 x축 위치
			int y = animals[i][1]; //동물 y축 위치
			
			/*
			 * 동물의 위치가 제일 작을 수 있는 x축 범위보다 작거나 
			 * 제일 클 수 있는 y축 범위보다 클 때 continue를 한다.
			 * 만약 제일 클 수 있는 x축 범위보다 동물이 멀리있다면 이 동물은 절대 죽일 수 없기 때문에 break 한다.
			 */
			if(x < minX || y > maxY) continue;
			else if(x > maxX) break;
			
			/*
			 * 사대를 반복한다.
			 * i번째 동물이 j번째 사대의 사정거리 안에 있다면,
			 * i+1번째 동물은 j 이상의 사정거리에 있고 j-1번째의 사대에 있을 확률은 없기 때문에 cur 변수를 사용하여 반복한다. 
			 */
			for(int j=cur; j<M; j++) { 
				int distance = Math.abs(sadaes[j] - x) + y;
				if(distance <= L) {
					ans++;
					cur = j;
					break;
				}
			}
		}

		System.out.println(ans);	
	}

}
