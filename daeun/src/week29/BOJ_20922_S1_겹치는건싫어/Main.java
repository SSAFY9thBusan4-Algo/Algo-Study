import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);
        split = br.readLine().split(" ");
        int[] numbers = new int[N];
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(split[i]);
        }

        //투 포인터로 해야겠다!
        int [] count = new int[100001];
        int start = 0, end = 0;
        int result = Integer.MIN_VALUE;

        while (end < N) {
            //뒤로 더 갈 수 있으면
            while (end < N && count[numbers[end]] + 1 <= K) {
                count[numbers[end]]++;
                end++;
            }

            //더 못 간다.
            int len = end - start;
            result = Math.max(result, len);

            //앞 처리하고 이동
            count[numbers[start]]--;
            start++;
        }
        System.out.println(result);
    }
}
