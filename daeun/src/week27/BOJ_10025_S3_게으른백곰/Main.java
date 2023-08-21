import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //2K+1 의 최댓값을 찾아라
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1])*2+1; //이 위치부터 가져간 셈
        int[] ice = new int[1000001];
        for(int i=0;i<N;i++){
            split = br.readLine().split(" ");
            int one =  Integer.parseInt(split[0]);
            int two = Integer.parseInt(split[1]);
            ice[two] = one;
        }

        int sum = 0, max = 0;
        for(int i = 0; i <= 1000000; i++) {
            if(i-K>=0){
                sum -= ice[i-K];
            }
            sum += ice[i];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
