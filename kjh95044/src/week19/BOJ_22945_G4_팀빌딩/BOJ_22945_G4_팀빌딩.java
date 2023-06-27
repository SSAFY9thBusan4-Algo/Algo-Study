import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22945_G4_팀빌딩 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int [n+1];

        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = n;
        int result=0;

        while (left<=right){
            int min = Math.min(arr[left],arr[right]); // 둘중 더 작은 값을 저장
            // 조건 : (개발자 A와 개발자 B 사이에 존재하는 다른 개발자 수) × min(개발자 A의 능력치, 개발자 B의 능력치)
            result = Math.max((right-left-1) * min, result); // 조건에서 나올수 있는 최대값을 저장

            // 더 둘중 더 큰 값을 움직이게 되면 이전값보다 항상 작아지기 때문에
            // 더 작은 값을 움직인다.
            if(min == arr[left]){
                left++;
            }
            else{
                right--;
            }
        }

        System.out.println(result);

    }


}
