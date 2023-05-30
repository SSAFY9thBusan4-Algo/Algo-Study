import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N, d, k, c, sushi[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰번호
        sushi = new int[N];
        HashMap<Integer,Integer> map = new HashMap<>();
        int max;

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(in.readLine());
        }

        for(int i=0; i<k; i++) {
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }
        map.put(c, map.getOrDefault(c, 0) + 1);
        max = map.size();

        for(int i=k; i<N+k; i++) {
            int past = sushi[(i-k)%N];
            int pastCnt = map.get(past);
            map.replace(past, pastCnt-1);
            if(map.get(past) == 0) {
                map.remove(past);
            }

            map.put(sushi[i%N], map.getOrDefault(sushi[i%N], 0) + 1);
            max = Math.max(max, map.size());
        }

        System.out.println(max);
    }
}
