package src.week4.PRG_118666_LV1_성격유형검사하기;

public class Main {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		//TCMA
		System.out.println(s.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"},new int[] {5, 3, 2, 7, 5}));
		//RCJA
		System.out.println(s.solution(new String[] {"TR", "RT", "TR"}, new int[] {7, 1, 3}));
	}

	static class Solution {
	    public String solution(String[] survey, int[] choices) {
	    	// info
	    	final String typeIndex ="RTCFJMAN"; 
	    	int[] typeScore = new int[8];
	    	
	    	// 문항 수 만큼
	    	for(int idx = 0 ; idx < choices.length; idx++) {
	    		
	    		// 문항에 대한 답
	    		int choice = choices[idx];
	    		if(choice < 4) {
	    			// 1 - 3점, 2 - 2점, 3 - 1점
	    			if(choice == 1) choice += 2;
	    			else if(choice == 3) choice -= 2;
	    			typeScore[typeIndex.indexOf(survey[idx].charAt(0))] += choice;
	    		}
	    		else if(choice > 4) {
	    			// 5 - 1점, 6 - 2점, 7 - 3점
	    			typeScore[typeIndex.indexOf(survey[idx].charAt(1))] += choice-4;
	    		}
	    	}
	    	
	    	// 유형 구하기
	    	StringBuilder ans = new StringBuilder();
	    	for(int i = 0 ; i < 8; i += 2) {
	    		ans.append(typeIndex.charAt(typeScore[i] >= typeScore[i+1] ? i : i+1));
	    	}
	    	
	        return ans.toString();
	    }
	}
}
