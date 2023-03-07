package week5.BOJ_21608_G5_상어초등학교;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int N, map[][], many[][], empty[][], result; 
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[] score = {0, 1, 10, 100, 1000};
	static ArrayList<Integer> favorite[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; //기존 교실 배치도
		
		favorite = new ArrayList[N*N+1]; //자리 배치 끝나고 만족도 조사해서 저장해둬야 함
		for(int s=0;s<N*N+1;s++) {
			favorite[s] = new ArrayList<>();
		}
		
		//입력 받으면서 자리 찾기
		String[] split;
		int student = 0;
		for(int s=0;s<N*N;s++) {
			split = br.readLine().split(" ");
			student = Integer.parseInt(split[0]);
			for(int i=0;i<4;i++) {
				favorite[student].add(Integer.parseInt(split[i+1]));
			}
			where(student);
		}
		//모든 학생의 자리 배치 끝
		
		satis(); //만족도 조사
				
		System.out.println(result);
	}
	
	private static void where(int student) {
		many = new int[N][N]; //인접 칸 수 세는  배열
		empty = new int[N][N]; //빈 칸 수 세는 배열
		for(int i=0; i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 0) { //아직 아무도 안 앉은 자리
					for(int d=0;d<4;d++) { //인접한 구역 => 사방 탐색
						int X = i +dx[d];
						int Y = j +dy[d];
						if(X>=0 && X<N && Y>=0 && Y<N) { //일단 범위 안에서
							if(favorite[student].contains(map[X][Y])) { //좋아하는 학생이 있으면
								
								many[i][j]++;
							}
							else if(map[X][Y] == 0) { //빈칸인 경우
								empty[i][j]++; 
							}
						}
					}
				}
			}	
		} //모든 자리를 돌면서 사방탐색

		int max = 0;
		ArrayList<int[]> list = new ArrayList<>();
		for(int i=0; i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 0) {
					if(max < many[i][j]) {
						max = many[i][j];
						list.clear(); //새로운 최댓값이 나왔으니까 전부 지우고 넣기
						list.add(new int[] {i, j});
					}
					else if(max == many[i][j]) {
						list.add(new int[] {i, j});
					}
				}
			}		
		} //모든 자리를 돌면서 1번 조건을 만족하는 칸 찾기
		
		ArrayList<int[]> list2 = new ArrayList<>();
		if(list.size()==1) { //1번 조건 만족하는 자리가 하나인 경우
			map[list.get(0)[0]][list.get(0)[1]] = student;
		}
		else {
			max = 0; //초기화
			for(int i=0;i<list.size();i++) { //list에 들어있는 개수만큼
				int[] now = list.get(i);
				
				if(max < empty[now[0]][now[1]]) { //빈 칸 수가 제일 많은 위치 찾기
					max = empty[now[0]][now[1]];
					list2.clear(); //새로운 최댓값이 나왔으니까 전부 지우고 넣기
					list2.add(new int[] {now[0], now[1]});
				}
				else if (max == empty[now[0]][now[1]]){
					list2.add(new int[] {now[0], now[1]});
				}
			}
			map[list2.get(0)[0]][list2.get(0)[1]] = student;
		}
		//2번 조건 확인 끝, 3번 조건은 탐색 순서때문에 list2에 자연스럽게 순서대로 들어감
	}
	
	private static void satis() {
		int count;
		for(int i=0; i<N;i++) {
			for(int j=0;j<N;j++) {
				count = 0;
				for(int d=0;d<4;d++) { //인접한 구역 => 사방 탐색
					int X = i +dx[d];
					int Y = j +dy[d];
					if(X>=0 && X<N && Y>=0 && Y<N) {
						if(favorite[map[i][j]].contains(map[X][Y])) { 
							count++;
						}
					}
				}
				result += score[count];
			}
		}
	}

}