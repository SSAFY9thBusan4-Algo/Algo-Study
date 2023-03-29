import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int result, N, K, durabilityCnt;

    static class Belt {
        boolean hasRobot;
        int durability;

        public Belt(boolean hasRobot, int durability) {
            this.hasRobot = hasRobot;
            this.durability = durability;
        }

        public Belt(int durability) {
            this.durability = durability;
        }

        @Override
        public String toString() {
            return "Belt{" +
                    "hasRobot=" + hasRobot +
                    ", durability=" + durability +
                    '}';
        }
    }

    static Belt[] belts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        belts = new Belt[2 * N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            int durability = Integer.parseInt(st.nextToken());
            belts[i] = new Belt(durability);

            if (durability == 0) {
                durabilityCnt++;
            }
        }

        // 4. 내구도 0인게 K 이상이면 종료
        while (durabilityCnt < K) {
            result ++;
//            System.out.println(Arrays.toString(belts));
            // 1. 벨트 회전
            Belt temp = (belts[2*N-1]);
            for (int i = 2*N-1; i > 0; i--) {
                belts[i] = (belts[i-1]);
            }
            // N-1일때 로봇을 내려줘야하는 조건이 있었는데 제데로 안 읽어서 놓쳐서 틀렸었다.
            belts[N-1].hasRobot = false;
            belts[0] = temp;
//            System.out.println(Arrays.toString(belts));

            // 2. 로봇 이동 (조건 : 이동 칸에 로봇 없어야하고 내구도 1이상이어야함. 먼저 올라간 로봇부터 이동

            Belt now = belts[2*N-1];
            Belt target = belts[0];
            move(now, target);

            for (int i = 2*N-1; i > 0; i--) {
                now = belts[i-1];
                target = belts[i];
//                System.out.println("before : " + belts[i]);
                move(now, target);
//                System.out.println("after : " + belts[i]);
            }
            // N-1일때 로봇을 내려줘야하는 조건이 있었는데 제데로 안 읽어서 놓쳐서 틀렸었다.
            belts[N-1].hasRobot = false;

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇을 올린다.
            if(belts[0].durability>=1){
                belts[0].hasRobot = true;
                belts[0].durability--;
                if(belts[0].durability==0){
                    durabilityCnt ++;
                }
            }
        }
        System.out.println(result);
    }

    private static void move(Belt now, Belt target) {
        if(!target.hasRobot && now.hasRobot && target.durability>=1) {
            target.hasRobot = true;
            now.hasRobot = false;
            target.durability--;
            if(target.durability==0){
                durabilityCnt ++;
            }
        }
    }
}
