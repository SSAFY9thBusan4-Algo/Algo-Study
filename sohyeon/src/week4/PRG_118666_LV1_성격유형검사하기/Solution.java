package week4.PRG_118666_LV1_성격유형검사하기;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static void main(String[] args) throws IOException {
		
		String[] servey = {"AN", "CF", "MJ", "RT", "NA"};
		int[] choices = {5, 3, 2, 7, 5};
		
		String result = solution(servey, choices);
		System.out.println(result);
		
	}

	private static Map<Character, Integer> map;
	
	private static String solution(String[] servey, int[] choices) {
		
		map = new HashMap<Character, Integer>();
		
		char[] types = {'A','N','C','F','M','J','R','T','N','A'};
		
		for (char t : types) {
			map.put(t, 0);
		}
		
		for (int i = 0; i < servey.length; i++) {
			String serv = servey[i];
			int cho = choices[i];
			
			if (cho-4 < 0) { // 매우 비동의, 비동의, 약간 비동의
				int v = map.get(serv.charAt(0));
				v += Math.abs(cho-4);
				map.replace(serv.charAt(0), v);
			}
			else if (cho-4 > 0) { // 약간 동의, 동의, 매우 동의
				int v = map.get(serv.charAt(1));
				v += Math.abs(cho-4);
				map.replace(serv.charAt(1), v);
			}
		}
		
//		for (char k : map.keySet()) {
//			System.out.println(k+" : "+map.get(k));
//		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(compareType('R', 'T'));
		sb.append(compareType('C', 'F'));
		sb.append(compareType('J', 'M'));
		sb.append(compareType('A', 'N'));
		
		return sb.toString();
		
	}
	
	private static String compareType(char t1, char t2) {
		char result = ' ';
		
		if (map.get(t1) > map.get(t2)) result = t1;
		else if (map.get(t1) < map.get(t2)) result = t2;
		else result = (t1-t2 < 0) ? t1 : t2;
		
		return Character.toString(result);		
	}
	
}

/** 제출코드
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public String solution(String[] survey, int[] choices) {
        
        String answer = sol(survey, choices);
        return answer;
        
    }
    
    private static Map<Character, Integer> map;
	
	private static String sol(String[] servey, int[] choices) {
		
		map = new HashMap<Character, Integer>();
		
		char[] types = {'A','N','C','F','M','J','R','T','N','A'};
		
		for (char t : types) {
			map.put(t, 0);
		}
		
		for (int i = 0; i < servey.length; i++) {
			String serv = servey[i];
			int cho = choices[i];
			
			if (cho-4 < 0) { // 매우 비동의, 비동의, 약간 비동의
				int v = map.get(serv.charAt(0));
				v += Math.abs(cho-4);
				map.replace(serv.charAt(0), v);
			}
			else if (cho-4 > 0) { // 약간 동의, 동의, 매우 동의
				int v = map.get(serv.charAt(1));
				v += Math.abs(cho-4);
				map.replace(serv.charAt(1), v);
			}
		}
		
//		for (char k : map.keySet()) {
//			System.out.println(k+" : "+map.get(k));
//		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(compareType('R', 'T'));
		sb.append(compareType('C', 'F'));
		sb.append(compareType('J', 'M'));
		sb.append(compareType('A', 'N'));
		
		return sb.toString();
		
	}
	
	private static String compareType(char t1, char t2) {
		char result = ' ';
		
		if (map.get(t1) > map.get(t2)) result = t1;
		else if (map.get(t1) < map.get(t2)) result = t2;
		else result = (t1-t2 < 0) ? t1 : t2;
		
		return Character.toString(result);		
	}
    
} **/

