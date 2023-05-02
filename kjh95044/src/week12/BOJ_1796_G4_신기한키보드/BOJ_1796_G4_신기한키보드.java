import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Character> list;
    static int result = Integer.MAX_VALUE;
    static HashMap<Character, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        char[] inputArray = input.toCharArray();

        // 문자열 S의 각 index 값을 char 별로 hash 에 저장한다.
        for(int i=0; i<inputArray.length; i++) {
            List<Integer> temp = map.get(inputArray[i]);
            if (temp == null) {
                temp = new ArrayList<>();
            }
            temp.add(i);
            map.put(inputArray[i], temp);
        }

        // 중복되지 않게 저장하고, 순서대로 정렬한다..
        list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        
        dfs(0, 0, 0);

        // 엔터를 누르는 갯수는 출력할 문자의 갯수와 같다.
        result += input.length();

        System.out.println(result);
    }

    // 처음에는 가장 가까운 곳 먼저 들르면 될꺼라 생각했는데,
    // 반례를 보니 가장 가까운 곳이 최적해가 아니었다.
    // 그래서 왼쪽을 먼저 가거나 오른쪽을 먼저 가는 경우 둘다 신경 써줘야했고,
    // 이를 재귀로 풀었다.
    private static void dfs(int index, int cur, int cnt) {

        if(cnt > result) return;

        if(index == list.size()){
            if(cnt < result){
                result = cnt;
            }
            return;
        }

        char c = list.get(index);
        int max = Collections.max(map.get(c));
        int min = Collections.min(map.get(c));

//        System.out.println(min + " " + max);

        if(max == min){ // 문자열 S에 있는 알파벳을 list에 저장 해놨기 때문에 index값을 하나씩 늘려 가면서 끝까지 탐색한다.
            dfs(index+1,max,cnt + Math.abs(cur - min));
        }else {
            dfs(index+1,max,cnt + Math.abs(cur - min) + Math.abs(max - min)); // 왼쪽 먼저
            dfs(index+1,min,cnt + Math.abs(cur - max) + Math.abs(max - min)); // 오른쪽 먼저
        }
    }
}
