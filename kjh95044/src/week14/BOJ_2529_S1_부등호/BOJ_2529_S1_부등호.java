import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 부등호 {
    static char[] arr;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        arr = new char[N];
        int cnt = 0;
        while (st.hasMoreTokens()) {
            arr[cnt++] = st.nextToken().charAt(0);
        }

        visited = new boolean[10];
        permutation(0, "");
        Collections.sort(resultList); // 리스트 정렬

        System.out.println(resultList.get(resultList.size()-1));  // 정렬했으니 마지막 값이 가장 큰 값
        System.out.println(resultList.get(0)); // 정렬했으니 첫번째 값이 가장 작은 값

    }

    static boolean[] visited;
    static List<String> resultList = new ArrayList<>();

    private static void permutation(int cnt, String nums) {

        if (cnt == N + 1) {
            resultList.add(nums);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;

            if (cnt == 0) { // 처음은 비교 대상이 없어서 무조건 넣기
                visited[i] = true;
                permutation(cnt + 1, nums + i);
                visited[i] = false;
            } else {
                char now = arr[cnt - 1]; // cnt가 부등호 만족하는 숫자라서 부등호는 숫자 다음
                boolean isPass = false;
                int left = nums.charAt(cnt - 1) - '0'; // nums에 있는 마지막 숫자

                if (now == '>') { // 부등호가 > 이고 왼쪽 숫자가 오른쪽 숫자보다 크면 pass
                    if (left > i) {
                        isPass = true;
                    }
                } else { // 부등호가 < 이고 왼쪽 숫자가 오른쪽 숫자보다 작으면 pass
                    if (left < i) {
                        isPass = true;
                    }
                }
                if (isPass) { // 조건에 만족하면 cnt를 증가 시키고 nums에 현재 숫자 추가해준다.
                    visited[i] = true;
                    permutation(cnt + 1, nums + i);
                    visited[i] = false;
                }

            }

        }

    }
}
