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
        int M = Integer.parseInt(st.nextToken());

        int[] powers = new int[N]; // 전투력
        String[] names = new String[N]; // 칭호

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());

            powers[i] = power;
            names[i] = name;
        }

        for(int i=0; i<M; i++){
            int num = Integer.parseInt(br.readLine());
            int index = binarySearch(powers, num); // 이분탐색
            sb.append(names[index]).append("\n");
        }

        System.out.println(sb);
    }

    public static int binarySearch(int[] arr, int target){

        int left = 0;
        int right = arr.length - 1;
        int mid;

        while(left <= right){
            mid = (left + right) / 2;
            if(arr[mid] < target){
                left = mid + 1;
            }else if(arr[mid] >= target){
                right = mid -1; // power가 같은 경우 왼쪽거 우선
            }
        }

        return left;
    }

}
