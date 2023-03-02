package week4.PRG_118666_LV1_성격유형검사하기;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static List<MBTI> list; // 성격 유형 지표 4가지를 담을 변수
	public static String[] mbti = { "RT", "CF", "JM", "AN" }; // 순서대로 1번 지표, 2번 지표 ...(알파벳 순서는 오름차순)

	public static StringBuilder sb;

	public static class MBTI {
		int no; // 지표
		char category; // 유형
		int score; // 점수

		public MBTI(int no) {
			this.no = no;
		}

		public void updateScore(int score) {
			this.score += score;
		}
	}

	public String solution(String[] survey, int[] choices) {
		String answer = "";

		sb = new StringBuilder();
		list = new ArrayList<MBTI>();
		list.add(new MBTI(1));
		list.add(new MBTI(2));
		list.add(new MBTI(3));
		list.add(new MBTI(4));

		for (int i = 0; i < survey.length; i++) {
			String category = survey[i];
			if (category.equals("RT") || category.equals("TR")) {
				calculateScore(0, category, choices[i]);
			} else if (category.equals("CF") || category.equals("FC")) {
				calculateScore(1, category, choices[i]);
			} else if (category.equals("JM") || category.equals("MJ")) {
				calculateScore(2, category, choices[i]);
			} else if (category.equals("AN") || category.equals("NA")) {
				calculateScore(3, category, choices[i]);
			}
		}

		// 유형 확정(2개의 유형 중 어떤 것을 선택할 지)
		// score가 음수이거나 같으면 첫번째 유형 선택, 양수이면 두번째 유형 선택
		// => 점수가 음수이거나 같으면 알파벳 순서가 더 빠른 유형(ex) R), 양수이면 알파벳 순서가 더 늦은 유형(ex) T)
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).score <= 0) {
				list.get(i).category = mbti[i].charAt(0);
			} else if (list.get(i).score > 0) {
				list.get(i).category = mbti[i].charAt(1);
			}
		}

		for (MBTI m : list) {
			sb.append(m.category);
		}

		answer = sb.toString();
		return answer;
	}

	// categoryIndex : 지표 번호, inputCategory : 입력으로 들어온 성격 유형, choice : 입력으로 들어온 선택지
	// 점수가 음수이거나 같으면 알파벳 순서가 더 빠른 유형(ex) R), 양수이면 알파벳 순서가 더 늦은 유형(ex) T)
	private void calculateScore(int categoryIndex, String inputCategory, int choice) {
		int score=0;
		// 입력으로 주어진 성격 유형이 오름차순 일 때
		if (inputCategory.charAt(0) == mbti[categoryIndex].charAt(0)) {
			score = choice - 4;
		}
		// 입력으로 주어진 성격 유형이 내림차순 일 때
		else {
			score = -(choice - 4);
		}
		
		// 점수 업데이트
		list.get(categoryIndex).updateScore(score);

	}
}
