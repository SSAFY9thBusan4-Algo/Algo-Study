import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        HashMap<Integer,Integer> map = new HashMap<>();

        int limit = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // 이 부분을 반대로 했었다.
            int k = Integer.parseInt(st.nextToken());

            limit = Math.max(limit, k);
            map.put(k,v);

        }

        int sum = 0;

        for(int i=0; i<=2*K; i++){
            sum += map.getOrDefault(i,0);
        }

        int max = sum;

        // 슬라이딩 윈도우
        for(int i=1; i <= limit; i++){
            sum -= map.getOrDefault(i-1, 0); // 좌
            sum += map.getOrDefault(2*K+i, 0); // 우
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }


}
