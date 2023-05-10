import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = {31,28,31,30,31,30,31,31,30,31,30,31};
		int result = 0;
		int day = 13;
		
		for(int i = 2019; i<N+1; i++) {
			for(int j=0; j<12;j++) {
				if(day % 7 == 4) { //금요일인지 //2019년 1월 첫 금요일이 4일
					result++;
				}
				day+=num[j];
				
				if(j==1 && (i%400==0 || (i%4==0 && i%100!=0))) { //윤년
					day++;
				}
			}
		}

		System.out.println(result);
	}
}
