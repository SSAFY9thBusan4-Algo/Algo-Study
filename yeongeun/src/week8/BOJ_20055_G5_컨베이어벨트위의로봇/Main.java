import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");

        int n = Integer.parseInt(in[0]);
        int k = Integer.parseInt(in[1]);

        int conveyor[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = getResult(n,k,conveyor);
        System.out.println(result);
    }

    private static int getResult(int n, int k, int[] conveyor) {
        int time = 1;
        int up = 0, down = n - 1, n2 = n * 2;
        List<Integer> robot = new ArrayList<>();
        boolean[] checked = new boolean[n2];
        
        while (true) {
            // 1. 벨트가 회전.
            // 올리는 위치, 내리는 위치
            if (--up == -1) up = n2-1;
            if (--down == -1) down = n2-1;
            
            // 2. 이동
            for (int i = 0; i < robot.size(); i++) {
                int curindex = robot.get(i);
                int nextindex = (curindex + 1) == n2 ? 0 : (curindex + 1);
                
                if(curindex == down) {    // 내리는 위치에 도착하면 내린다.
                    checked[curindex] = false;
                    robot.remove(i--);
                    
                }
                else if (conveyor[nextindex] > 0 && !checked[nextindex]) { // 이동할 수 있으면
                    robot.set(i, nextindex);
                    checked[curindex] = false;
                    checked[nextindex] = true;
                    --conveyor[nextindex];
                    
                    if(nextindex == down) {    // 내리는 위치에 도착하면 내린다.
                        checked[nextindex] = false;
                        robot.remove(i--);
                    }
                }
            }
            
            // 3. 로봇 올리기
            if(conveyor[up] != 0 && !checked[up]) {
                conveyor[up]--;
                robot.add(up);
                checked[up] = true;
            }
            
            // 4. 내구도가 0인 칸의 개수가 k개 이상이라면 종료
            int countk = 0;
            for(int i = 0 ; i < n2 ; i++) {
                if(conveyor[i] <= 0) countk++;
            }
            if(countk >= k) break;
            else time++;
        }

        return time;
    }
}
