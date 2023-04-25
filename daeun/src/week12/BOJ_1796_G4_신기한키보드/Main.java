import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
	static int result=Integer.MAX_VALUE, len;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		len = str.length();
		
		list = new ArrayList[26]; //문자마다 위치를 추가할 list 배열
		for(int i=0;i<26;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0;i<len;i++) {
			char now = str.charAt(i);
			list[now-'a'].add(i); //위치를 추가
		}
		
		dfs(0, 0, 0);

		System.out.println(result+len);

	}

	private static void dfs(int alpha, int idx, int move) {
		if(alpha == 26) {
			result = Math.min(result, move);
			return;
		}
		
		if(list[alpha].size()==0) { //문자가 없으면 다음으로
			dfs(alpha+1, idx, move);
		}
		else {
			int first = list[alpha].get(0);
			int last = list[alpha].get(list[alpha].size()-1);
			
			if(first == last) { //하나 들어 있는 경우
				move += Math.abs(idx - first);
				idx = first; //현 위치 옮기기
				dfs(alpha+1, idx, move);
			}
			else {
				int num1 = Math.abs(idx-first);
				int num2 = Math.abs(idx-last);
				
				if(idx>first && idx<last) { //idx가 둘 사이에 있을 때
					int temp = move+num1*2+num2;
					dfs(alpha+1, last, temp);
					
					temp = move+num1+num2*2;
					dfs(alpha+1, first, temp);
				}
				else {
					if(num1<num2) { //차이가 더 많이 나는 만큼 이동
						move+=num2; 
						idx = last;
					}
					else {
						move+=num1;
						idx = first;
					}
					dfs(alpha+1, idx, move);
				}
			}
		}
	}
}
