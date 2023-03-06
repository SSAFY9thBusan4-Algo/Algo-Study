package week5.SWEA_4014_활주로건설;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	public static List<Node> startList;
	public static int N;
	public static int K;
	public static int[][] map;
	public static int result;

	// 결과를 한번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();
	
	public static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("res/1949_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		/*
		 * 2. 입력파일 객체화
		 */
		int T;
		T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int max=0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>max) {
						max=map[i][j];
					}
				}
			}
			
			startList = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max) {
						startList.add(new Node(i, j));
					}
				}
			}

			/*
			 * 3. 알고리즘 풀기
			 */
			for(Node start : startList) {
				dfs(start.x,start.y,1,false);
			}

		}

		/*
		 * 4. 정답 출력
		 */
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int i, boolean b) {
		
	}
}
