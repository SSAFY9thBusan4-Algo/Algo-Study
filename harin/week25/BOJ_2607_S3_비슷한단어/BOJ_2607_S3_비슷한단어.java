import java.io.*;
import java.util.*;


public class BOJ_2607_S3_비슷한단어 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = Integer.parseInt(in.readLine());
		String orgStr = "";
		
		int ans = 0;
		
		Map<Character, Integer> map = new HashMap();
		
		for(int i=0; i<cnt; i++) {
			String str = in.readLine();
			if(i==0) { // 입력으로 주어진 첫 번째 단어
				orgStr = str;
				for(int j=0; j<orgStr.length(); j++) {
					map.put(orgStr.charAt(j), map.getOrDefault(orgStr.charAt(j), 0) + 1);
				}
				continue;
			}
			
			if(Math.abs(str.length() - orgStr.length()) > 1) continue;
			
			// str의 요소를 확인
			Map<Character, Integer> strMap = new HashMap();
			for(int j=0; j<str.length(); j++) {
				strMap.put(str.charAt(j), strMap.getOrDefault(str.charAt(j), 0) + 1);
			}
			
			int check = 0; //같은 문자 개수
			// orgStr과 str을 비교하여 확인
			for(Character ch : map.keySet()) {
				if(strMap.get(ch) != null) {
					if(strMap.get(ch) > map.get(ch)) {
						check += map.get(ch);
					}
					else check += strMap.get(ch);
				}
			}
			
			// 1. str에서 문자를 제거해야할 경우
			if(orgStr.length() == str.length() - 1) {
				if(check == str.length() - 1) {
					ans++;
				}
			}
			// 2. str에서 문자를 추가해야할 경우
			else if(orgStr.length() == str.length() + 1) {
				if(check == str.length()) {
					ans++;
				}
			}
			// 3. 문자를 바꾸는 경우와 구성이 같은 경
			else if(orgStr.length() == str.length()) {
				if(check + 1 == orgStr.length() || check == orgStr.length()) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	

}
