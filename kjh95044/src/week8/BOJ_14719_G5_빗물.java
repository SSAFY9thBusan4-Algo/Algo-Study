import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] map;
    static int result, H, W;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[W];

        st=new StringTokenizer(br.readLine());
        for(int i=0; i< W; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<W-1; i++){
            int left =0;
            int right = 0;
            for(int j=0; j<i; j++){
                left = Math.max(left, map[j]);
            }
            for(int j=i; j<W; j++){
                right = Math.max(right, map[j]);
            }

            if(left>map[i] && right>map[i]) {
                int amount = Math.min(left, right);
                result += amount - map[i];
            }

        }
        System.out.println(result);
    }
}
