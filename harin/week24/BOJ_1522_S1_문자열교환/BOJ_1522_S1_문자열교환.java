import java.io.*;

public class BOJ_1522_S1_문자열교환 {
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		
		int aCnt = 0;
		int length = str.length();
		int ans = Integer.MAX_VALUE; //최소값을 찾기 위해 맥스값으로 초기화해준다.
		
		//a의 개수 찾아주기
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == 'a') aCnt++;
		}
		
		str = str+str;
		
		//a의 개수만큼 윈도우를 슬라이딩하며 b의 최소 개수 세어주기. (교환해야하는 횟수)
		for(int i=0; i<length; i++) {
			int bCnt = 0;
			for(int j=0; j<aCnt; j++) {
				if(str.charAt(i+j)=='b') bCnt++;
			}
			
			ans = Math.min(bCnt, ans);
		}
		
		System.out.println(ans);
		
	}

}
