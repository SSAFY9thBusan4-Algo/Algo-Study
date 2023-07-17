import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> posMap = new ArrayList<>();
        List<Integer> negMap = new ArrayList<>();

        // 양수 까리 곱한다.
        // 음수는 음수끼리 곱한다.
        // 0은? 0은 음수쪽에 곱해주면 이득이다.
        // 1은? 1은 곱하는 것보다 그냥 더하는게 더 이득이다.

        // 양수와 음수 + 0 이 들어있는 리스트를 분리한다.
        for(int i=0; i<N; i++){
            int input = Integer.parseInt(br.readLine());
            if(input>0){
                posMap.add(input);
            }
            else{
                negMap.add(input);
            }
        }

        Collections.sort(posMap, Collections.reverseOrder()); // ex) 5, 3, 1 순서

        // 음수끼리 곱하기 때문에 절대값이 큰 순으로 정렬
        Collections.sort(negMap); // ex) -5, -3, -1 순서

        int sum = 0;
        int i = 0;
        sum += getSum(posMap);
        sum += getSum(negMap);

        System.out.println(sum);
    }

    private static int getSum(List<Integer> map) {
        int i = 0;
        int sum = 0;
        while (i < map.size()) {
            if (i + 1 < map.size() && map.get(i) != 1 && map.get(i + 1) != 1) // 둘다 1이 아니고, 홀수가 아닐때 곱해서 더한다.
                sum += map.get(i++) * map.get(i++);
            else
                sum += map.get(i++);
        }
        return sum;
    }
}
