import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int daysOfMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totalDay = 13;
        int result = 0;

        // 2019년 1월 1일이 화요일
        for (int i = 2019; i <= N; i++) {
            for (int j = 1; j <= 12; j++) {
                if (totalDay % 7 == 4) { // 7로 나눴을때 나머지가 1이면 화요일, 따라서 4여야 금요일
                    result ++;
                }

                totalDay += daysOfMonth[j]; // 다음달 13일을 구하기 위해 해당 달의 날짜만큼 더해준다.

                /*
                    문제 힌트
                    400의 배수 연도는 윤년이다.
                    400의 배수가 아니면서 100의 배수인 연도는 윤년이 아니다.
                    100의 배수가 아니면서 4의 배수인 연도는 윤년이다.
                 */

                // 윤년 조건에 따라 2월 달이 윤년이면 29일까지 있기 때문에 + 1 해줌
                if(j == 2 && (i % 400 == 0 || (i % 100 != 0 && i % 4 == 0) )){
                    totalDay++;
                }

            }
        }

        System.out.println(result);
    }
}
