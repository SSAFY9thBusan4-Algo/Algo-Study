import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_1744_G4_수묶기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr); // 오름차순 정렬

        int result = 0;
        int index = N;

        // 수열이 1개일 때
        if (N == 1) {
            result = arr[0];
        }

        // 음수관련(오름차순으로 탐색)
        for (int i = 0; i < N - 1; i += 2) {
            int num1 = arr[i];
            int num2 = arr[i + 1];

            if (num1 < 0 && num2 < 0) { // 음수 & 음수일 때 묶기
                result += num1 * num2;
            } else if (num1 < 0 && num2 == 0) { // 음수 & 0일 때 묶기 + 다음수부터 양수이므로 index 설정
                index = i + 2;
                break;
            } else if (num1 < 0 && num2 > 0) { // 음수 & 양수일 때 음수만 단독으로 더함
                result += num1;
                index = i + 1;
                break;
            } else if (num1 == 0 && num2 > 0) { // 0 & 양수일 때 break
                index = i + 1;
                break;
            } else if (num1 > 0 && num2 > 0) { // 양수 & 양수일 때 break
                index = i;
                break;
            }
        }

        // 양수관련(내림차순으로 탐색)
        if (index < N) { // 양수가 존재할 때
            if ((N - index) % 2 != 0) { // 홀수일 때 제일 작은수는 묶기X
                result += arr[index];
                index++;
            }

            for (int i = N - 1; i >= index; i -= 2) {
                int num1 = arr[i];
                int num2 = arr[i - 1];
                if (num1 + num2 > num1 * num2) { // 묶는것보다 묶지 않을때가 더 점수가 높을 때
                    result += num1 + num2;
                } else {
                    result += num1 * num2;
                }
            }
        }

        System.out.println(result);
    }
}
