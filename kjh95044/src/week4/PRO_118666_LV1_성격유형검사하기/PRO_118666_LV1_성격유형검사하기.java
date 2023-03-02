package week4.PRO_118666_LV1_성격유형검사하기;

import java.util.HashMap;

public class PRO_118666_LV1_성격유형검사하기 {

	static HashMap<Character, Integer> map = new HashMap<>();
	static char[] types = {'R','T','C','F','J','M','A','N'};
	
    static public String solution(String[] survey, int[] choices) {
        String answer = "";
        for(int i=0; i<types.length; i++) {
        	map.put(types[i], 0);
        }
        
        for(int i=0; i<survey.length; i++) {
        	char left = survey[i].charAt(0);
        	char right = survey[i].charAt(1);
        	
        	int choice = choices[i];
        	
        	if(choice > 4) {
        		map.replace(right, map.get(right) + choice-4);
        	}
        	else if(choice <4) {
        		map.replace(left, map.get(left) + Math.abs(choice-4));
        	}
        }
        
        for(int i=0; i<types.length; i+=2) {
            if(map.get(types[i])>= map.get(types[i+1])) {
            	answer += types[i];
            }
            else {
            	answer += types[i+1];
            }
        }
        return answer;
    }
	
	public static void main(String[] args) {
		String[] surv = {"AN", "CF", "MJ", "RT", "NA"};
		int[] choices = {5, 3, 2, 7, 5};
		System.out.println(solution(surv, choices));
	}
}
