package week5.SWEA_4014_활주로건설;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	 public static int N;
	    public static int X;
	    public static int[][] map;
	 
	    // 결과를 한번에 출력하기 위한 StringBuilder
	    private static StringBuilder sb = new StringBuilder();
	 
	    public static void main(String[] args) throws Exception {
	 
	        /*
	         * 1. 입력파일 읽어들이기
	         */
	        //System.setIn(new FileInputStream("res/4014_input.txt"));
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
	            X = Integer.parseInt(st.nextToken());
	 
	            map = new int[N][N];
	            for (int i = 0; i < N; i++) {
	                st = new StringTokenizer(in.readLine());
	                for (int j = 0; j < N; j++) {
	                    map[i][j] = Integer.parseInt(st.nextToken());
	                }
	            }
	 
	            /*
	             * 3. 알고리즘 풀기
	             */
	 
	            // 행 : 좌에서 우로 탐색하며 활주로 설치 가능한지 탐색 & 우에서 좌로도 탐색
	            // 열 : 상에서 하로 탐색하며 활주로 설치 가능한지 탐색 & 하에서 상으로도 탐색
	            int result = 0;
	            for (int i = 0; i < N; i++) {
	                boolean left = true; // 좌->우 or 상->하 방향으로 활주로 설치 가능한지 확인하는 변수
	                boolean right = true; // 우->좌 or 하->상 방향으로 활주로 설치 가능한지 확인하는 변수
	 
	                boolean[] airtripCheck = new boolean[N]; // 해당 셀에 경사로가 설치되어있는지 확인하는 변수
	 
	                // ----------------- 행 검사 ---------------------
	                // 좌 -> 우 방향 탐색
	                for (int j = 0; j < N - 1; j++) {
	                    if (map[i][j + 1] == map[i][j] - 1) { // 경사로 검사 시작
	                        boolean airstrip = checkAirstrip(i, j + 1, airtripCheck);
	 
	                        if (!airstrip) { // 경사로 설치가 불가능할 때
	                            left = false;
	                            break;
	                        } else { // 경사로 설치가 가능할 때
	                            for (int k = j + 1; k < j + 1 + X; k++) { // 경사로를 설치해줬다고 알려줌
	                                airtripCheck[k] = true;
	                            }
	                            j = j + X - 1; // 다음 경사로 범위 설정 (범위 조심 -> 마지막에 for문으로 +1 추가됨)
	                        }
	                    } 
	                    else if (map[i][j + 1] < map[i][j] - 1) { // 다음 지형의 높이 차이가 2이상이면 경사로 설치 불가능
	                        left = false;
	                        break;
	                    }
	                }
	 
	                // 우 -> 좌 방향 탐색
	                // 경사로 설치 가능할 때 airtripCheck에 체크 안해도 됨 -> 해당 행에서 검사가 끝나서 초기화 될거라서
	                for (int j = N - 1; j >= 1; j--) {
	                    if (map[i][j - 1] == map[i][j] - 1) { // 경사로 검사 시작
	                        boolean airstrip = checkAirstrip2(i, j - 1, airtripCheck);
	                        if (!airstrip) {
	                            right = false;
	                            break;
	                        } else {
	                            j = j - X + 1;
	                        }
	                    } 
	                    else if (map[i][j - 1] < map[i][j] - 1) {
	                        left = false;
	                        break;
	                    }
	                }
	 
	                // 양방향에서 모두 활주로가 설치 가능해야 활주로 설치 가능
	                if (left && right) {
	                    result++;
	                }
	 
	                // ----------------- 열 검사 ---------------------
	                airtripCheck = new boolean[N];
	                left = true;
	                right = true;
	                // 상 -> 하 방향 탐색
	                for (int j = 0; j < N - 1; j++) {
	                    if (map[j + 1][i] == map[j][i] - 1) { // 경사로 검사 시작
	                        boolean airstrip = checkAirstrip3(i, j + 1, airtripCheck);
	                        if (!airstrip) {
	                            left = false;
	                            break;
	                        } else {
	                            for (int k = j + 1; k < j + 1 + X; k++) {
	                                airtripCheck[k] = true;
	                            }
	                            j = j + X - 1; // 범위 조심 -> 마지막에 for문으로 +1 추가됨
	                        }
	                    } 
	                    else if (map[j + 1][i] < map[j][i] - 1) {
	                        left = false;
	                        break;
	                    }
	                }
	 
	                // 하 -> 상 방향 탐색
	                for (int j = N - 1; j >= 1; j--) {
	                    if (map[j - 1][i] == map[j][i] - 1) { // 경사로 검사 시작
	                        boolean airstrip = checkAirstrip4(i, j - 1, airtripCheck);
	                        if (!airstrip) {
	                            right = false;
	                            break;
	                        } else {
	                            j = j - X + 1;
	                        }
	                    } 
	                    else if (map[j - 1][i] < map[j][i] - 1) {
	                        right = false;
	                        break;
	                    }
	                }
	 
	                if (left && right) {
	                    result++;
	                }
	            }
	            sb.append(result).append("\n");
	 
	        }
	 
	        /*
	         * 4. 정답 출력
	         */
	 
	        System.out.println(sb);
	    }
	 
	    // 주어진 길이만큼 경사로를 설치할 수 있는지 확인하는 메소드
	    // 행에서 좌->우 검사
	    private static boolean checkAirstrip(int i, int start, boolean[] airtripCheck) {
	        // 경사로 길이만큼 설치할 수 있는지 확인
	        for (int j = start; j < start + X; j++) {
	            if (j >= N) {// 배열 범위 벗어날 때 -> 경사로를 다 설치하기 전에 범위를 벗어났으므로 실패
	                return false;
	            }
	            if (map[i][j] != map[i][start]) { // 낮은 지형의 높이가 다를 때 -> 경사로를 다 설치하기 전에 높이가 달라졌으므로 실패
	                return false;
	            }
	        }
	        // 무사히 경사로를 설치 가능할 때
	        return true;
	    }
	 
	    // 행에서 우->좌 검사
	    private static boolean checkAirstrip2(int i, int start, boolean[] airtripCheck) {
	        for (int j = start; j > start - X; j--) {
	            if (j < 0) {
	                return false;
	            }
	            if (map[i][j] != map[i][start]) {
	                return false;
	            }
	            // 동일한 셀에 이미 경사로가 설치되어있을 때
	            if (airtripCheck[j]) {
	                return false;
	            }
	        }
	        return true;
	    }
	 
	    // 열에서 상->하 검사
	    private static boolean checkAirstrip3(int i, int start, boolean[] airtripCheck) {
	        for (int j = start; j < start + X; j++) {
	            if (j >= N) {
	                return false;
	            }
	            if (map[j][i] != map[start][i]) {
	                return false;
	            }
	 
	        }
	        return true;
	    }
	 
	    // 열에서 하->상 검사
	    private static boolean checkAirstrip4(int i, int start, boolean[] airtripCheck) {
	        for (int j = start; j > start - X; j--) {
	            if (j < 0) {
	                return false;
	            }
	            if (map[j][i] != map[start][i]) {
	                return false;
	            }
	            if (airtripCheck[j]) {
	                return false;
	            }
	        }
	        return true;
	    }
}
