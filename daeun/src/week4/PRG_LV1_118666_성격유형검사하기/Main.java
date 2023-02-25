package week4.PRG_LV1_118666_성격유형검사하기;

public class Main {
	public static void main(String[] args) {
		
	}
	static class Solution{
		String solution(String[] survey, int[] choices){
			String type = "RTCFJMAN";
			int[] num = new int[8];
			for(int i=0; i<choices.length;i++) {
				int choice = choices[i];
				//비동의 vs 동의
				if(choice < 4) {
					if(choice == 1) choice = 3;
					else if(choice == 3) choice = 1;
					//입력받은 타입의 앞
					num[type.indexOf(survey[i].charAt(0))] += choice;
				}
				else if(choice > 4) {
					if(choice == 7) choice = 3;
					else if(choice == 6) choice = 2;
					else if(choice == 5) choice = 1;
					//입력받은 타입의 뒤
					num[type.indexOf(survey[i].charAt(1))] += choice;
				}
			
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<8;i+=2) {
				System.out.println(num[i]+" "+num[i+1]);
				if(num[i]>=num[i+1]) { //type이 사전순이라 크거나 같다로
					sb.append(type.charAt(i));
				}
				else {
					sb.append(type.charAt(i+1));
				}
			}
			return sb.toString();
		}
			
	}
}
