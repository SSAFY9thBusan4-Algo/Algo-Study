import java.io.*;
import java.util.*;

public class BOJ_2831_G4_댄스파티 {

    // 키가 같은 남자와 여자가 춤을 추는 일은 일어나지 않는다.
    // 커플의 유형은 1. 키가 작은 남자 (하트) 키가 큰 여자
    // 2. 키가 큰 남자 (하트) 키가 작은 여자

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int ans = 0;

        // 남자 리스트
        ArrayList<Integer> boyLikeShort = new ArrayList<>();
        ArrayList<Integer> boyLikeTall = new ArrayList<>();

        // 여자 리스트
        ArrayList<Integer> girlLikeShort = new ArrayList<>();
        ArrayList<Integer> girlLikeTall = new ArrayList<>();

        // 남자 입력
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int b=0; b<N; b++){
            int boyHeight = Integer.parseInt(st.nextToken());
            if(boyHeight > 0) boyLikeTall.add(boyHeight);
            else boyLikeShort.add(boyHeight * -1);
        }

        // 여자 입력
        st = new StringTokenizer(in.readLine());
        for(int g=0; g<N; g++) {
            int girlHeight = Integer.parseInt(st.nextToken());
            if(girlHeight > 0) girlLikeTall.add(girlHeight);
            else girlLikeShort.add(girlHeight * -1);
        }

        // 정렬
        Collections.sort(boyLikeShort);
        Collections.sort(boyLikeTall);
        Collections.sort(girlLikeShort);
        Collections.sort(girlLikeTall);

        // 키가 큰 남자 (하트) 키가 작은 여자
        for(int b=0, g=0; b<boyLikeShort.size() && g<girlLikeTall.size();) {
            int boyHeight = boyLikeShort.get(b);
            int girlHeight = girlLikeTall.get(g);

            if(boyHeight <= girlHeight) {
                b++;
                continue;
            }

            ans++;
            b++;
            g++;
        }

        // 키가 작은 남자 (하트) 키가 큰 여자
        for(int b=0, g=0; b<boyLikeTall.size() && g < girlLikeShort.size();) {
            int boyHeight = boyLikeTall.get(b);
            int girlHeight = girlLikeShort.get(g);

            if(girlHeight <= boyHeight) {
                g++;
                continue;
            }

            ans++;
            b++;
            g++;
        }

        System.out.println(ans);
    }

}
