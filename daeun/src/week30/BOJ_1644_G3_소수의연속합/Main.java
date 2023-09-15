import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static boolean[] primeNumber;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        prime();

        int sum = 0;
        int cnt = 0;
        int start = 0;
        int end = 0;
        while(start<=end && end<list.size()){
            if(sum < N) {
                sum += list.get(end++);
            } else {
                //목표가 나왔으면 더하고
                if(sum == N) {
                    cnt++;
                }
                sum -= list.get(start++);
            }
        }

        System.out.println(cnt);
    }

    //소수 구하기
    private static void prime() {
        primeNumber = new boolean[N+1];
        Arrays.fill(primeNumber , true);

        primeNumber[0] = primeNumber[1] = false;
        //2부터 소수 구하기
        for(int i=2; i*i<N+1; i++){
            if(primeNumber[i]){
                for(int j=i*i; j<=N; j+=i) {
                    primeNumber[j] = false;
                }
            }
        }

        for(int i=1; i<N+1; i++) {
            if(primeNumber[i]) {
                list.add(i);
            }
        }
        list.add(0);
    }
}
