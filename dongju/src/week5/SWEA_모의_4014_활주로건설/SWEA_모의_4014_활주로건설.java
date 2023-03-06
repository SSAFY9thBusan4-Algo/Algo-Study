package week5.SWEA_모의_4014_활주로건설;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의_4014_활주로건설 {
	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			// 가로
			for(int i=0; i<n; i++) {
				boolean check = true;
				boolean[] visited = new boolean[n];
				for(int j=0; j<n-1; j++) {
					if(Math.abs(arr[i][j] - arr[i][j+1]) >= 2) {
						check = false;
						break;
					}
					if(Math.abs(arr[i][j] - arr[i][j+1]) != 1) continue; // 높이차이 1 X
					else {
						if(arr[i][j] < arr[i][j+1]) { // 오르막
							for(int k=0; k<x; k++) {
								if(j-k >= 0 && !visited[j-k]) { // 범위
									if(arr[i][j] != arr[i][j-k]) {
										check = false;
									}
								} else check = false; // 범위 밖
							}
							if(check) { // 활주로 건설
								for(int k=0; k<x; k++) {
									visited[j-k] = true;
								}
							}
						} else { // 내리막
							for(int k=0; k<x; k++) {
								if(j+k+1 < n && !visited[j+k+1]) { // 범위
									if(arr[i][j+1] != arr[i][j+k+1]) {
										check = false;
									} 
								} else check = false;
							}
							if(check) { // 활주로 건설
								for(int k=0; k<x; k++) {
									visited[j+k+1] = true;
								}
							}
						}
					}
				}
				if(check) cnt++;
			}
			
			// 세로
			for(int j=0; j<n; j++) {
				boolean check = true;
				boolean[] visited = new boolean[n];
				for(int i=0; i<n-1; i++) {
					if(Math.abs(arr[i][j] - arr[i+1][j]) >= 2) {
						check = false;
						break;
					}
					if(Math.abs(arr[i][j] - arr[i+1][j]) != 1) continue; // 높이차이 1 X
					else {
						if(arr[i][j] < arr[i+1][j]) { // 오르막
							for(int k=1; k<x; k++) {
								if(i-k >= 0 && !visited[i-k]) { // 범위
									if(arr[i][j] != arr[i-k][j]) {
										check = false;
									}
								} else check = false;
							}
							if(check) { // 활주로 건설
								for(int k=0; k<x; k++) {
									visited[i-k] = true;
								}
							}
						} else { // 내리막
							for(int k=1; k<x; k++) {
								if(i+k+1 < n && !visited[i+k+1]) { // 범위
									if(arr[i+1][j] != arr[i+k+1][j]) {
										check = false;
									}
								} else check = false;
							}
							if(check) { // 활주로 건설
								for(int k=0; k<x; k++) {
									visited[i+k+1] = true;
								}
							}
						}
					}
				}
				if(check) cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}