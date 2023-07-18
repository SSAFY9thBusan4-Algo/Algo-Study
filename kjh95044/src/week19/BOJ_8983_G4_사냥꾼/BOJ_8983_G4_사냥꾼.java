import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983_G4_사냥꾼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 사대의 수 M
        int N = Integer.parseInt(st.nextToken()); // 동물의 수 N
        int L = Integer.parseInt(st.nextToken()); // 사정거리 L

        int[] saDae = new int[M];


        st = new StringTokenizer(br.readLine());
        for(int i =0; i<M; i++){
            saDae[i] = Integer.parseInt(st.nextToken());
        }

        // 이분탐색 하기위해 정렬
        Arrays.sort(saDae);

        int result = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if( y>L || x<saDae[0] - L || x>saDae[saDae.length-1] + L) continue;
            int idx = Arrays.binarySearch(saDae, x);
            if(idx>=0){
                result ++;
            }else{
                // x에 해당하는 값을 찾지 못했을때 이분탐색의 리턴값은 해당 값보다 큰 가장 가까운 인덱스에 -1을 곱하고 1을 뺀 값이다.
                // 이에 따라 그 반대로 idx값을 찾았다.
                idx = (idx+1) * -1;

                // 왼쪽과 오른쪽에서 사정거리내의 사대를 찾을수 있는지 확인한다.
                if(idx-1>=0 && (Math.abs(x-saDae[idx-1]) + y) <=L){
                    result++;
                } else if (idx< saDae.length && (Math.abs(x-saDae[idx]) + y) <=L) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
