package week14.BOJ_16463_S3_13일의금요일;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16463_S3_13일의금요일 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());

		int result=0;
		int day = 13;
		int year = 2019;
		while (year <= N) {
			int month = 1;
			while (month <= 12) {
				if(day%7==4) { // 13일의 금요일인지 확인
					result++;
				}
				
				// 해당 월의 일 추가
				switch (month) {
				case 1:
					day+=31;
					break;
				case 2:
					if (isLeapYear(year)) {
						day+=29;
					} else {
						day+=28;
					}
					break;
				case 3:
					day+=31;
					break;
				case 4:
					day+=30;
					break;
				case 5:
					day+=31;
					break;
				case 6:
					day+=30;
					break;
				case 7:
					day+=31;
					break;
				case 8:
					day+=31;
					break;
				case 9:
					day+=30;
					break;
				case 10:
					day+=31;
					break;
				case 11:
					day+=30;
					break;
				case 12:
					day+=31;
					break;
				}
				
				month++;
			}
			year++;
		}
		
		System.out.println(result);
	}

	private static boolean isLeapYear(int year) {
		boolean result = false;
		if (year % 400 == 0) {
			result = true;
		} else if (year % 100 == 0) {
			result = false;
		} else if (year % 4 == 0) {
			result = true;
		}

		return result;
	}
}
