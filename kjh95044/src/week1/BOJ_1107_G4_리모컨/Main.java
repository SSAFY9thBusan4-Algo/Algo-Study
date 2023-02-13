import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static boolean isFoundNomalButton(int target, List<Integer> brokenButtons) {
        boolean isFound = false;
        for (int bb : brokenButtons) {
            if (String.valueOf(target).contains(String.valueOf(bb))) {
                isFound = false;
                break;
            }
            isFound = true;
        }
        return isFound;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());
        int result = 0;

        int brokenButtonCnt = Integer.parseInt(br.readLine());
        List<Integer> brokenButtons = new ArrayList<>();
        if (brokenButtonCnt != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int bb = 0; bb < brokenButtonCnt; bb++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 풀이 시작

        // +-로 채널 찾기
        result = Math.abs(target - 100);

        // 고장난 버튼이 없으면 target length 출력
        if(brokenButtonCnt == 0) {
            result = Math.min(result, String.valueOf(target).length());
        }

        // 버튼과 +- 조합으로 채널 찾기
        // target에서 고장나지 않은 버튼으로 도달할 수 있는 가장 가까운 값을 찾는다.

        if(isFoundNomalButton(target, brokenButtons)) {
            result = Math.min(result, String.valueOf(target).length());
        }

        boolean isFound = false;
        int targetPlus = target;

        // 문제에서 N은 500000 이하로 주어졌고, 9번만 고장나지 않았을 경우까지 생각하여 최대값 999999
        while (!isFound && targetPlus < 999999 && brokenButtons.size() != 0) {
            targetPlus++;

            isFound = isFoundNomalButton(targetPlus, brokenButtons);
        }

        if (isFound) {
            int targetPlusLen = String.valueOf(targetPlus).length();
            result = Math.min(result, Math.abs(targetPlus - target) + targetPlusLen);
        }
        isFound = false;
        int targetMinus = target;

        // 문제에서 채널은 0까지 있다고 주어짐
        while (!isFound && targetMinus > 0 && brokenButtons.size() != 0) {
            targetMinus--;
            isFound = isFoundNomalButton(targetMinus, brokenButtons);
        }

        if (isFound) {
            int targetMinusLen = String.valueOf(targetMinus).length();
            result = Math.min(result, Math.abs(targetMinus - target) + targetMinusLen);
        }

        System.out.println(result);
    }
}