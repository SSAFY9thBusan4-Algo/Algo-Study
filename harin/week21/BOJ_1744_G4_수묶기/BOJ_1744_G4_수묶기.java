import java.io.*;
import java.util.*;

public class BOJ_1744_G4_수묶기 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        boolean zero = false; //0이 있냐 없냐 확인하는 boolean 변수
        int oneCnt = 0; // 1은 곱하지 않고 더하는 것이 최대의 값을 도출할 수 있다. 
        ArrayList<Integer> negative = new ArrayList<>(); // 음수 저장 리스트
        ArrayList<Integer> positive = new ArrayList<>(); // 양수 저장 리스트

        for(int i=0; i<T; i++) {
            int num = Integer.parseInt(in.readLine());
            if(num == 0) {
                zero = true;
            }
            else if(num < 0) {
                negative.add(num);
            }
            else if(num == 1) {
                if(num == 1) oneCnt++;
            }
            else {
                positive.add(num);
            }
        }

        // 정렬
        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        // 0이 있을 때 음수의 개수가 홀수인 경우 절댓값이 제일 작은 수 없애주기.
        if(zero) {
            if(negative.size() % 2 == 1) {
                negative.remove(negative.size() - 1);
            }
        }

        int ans = oneCnt;

        // 양수 처리
        for(int i=0; i<positive.size(); i+=2) {
            if(positive.size() % 2 == 1 && i == positive.size() - 1) {
                ans += positive.get(i);
                break;
            }
            ans += positive.get(i) * positive.get(i+1);
        }

        // 음수 처리
        for(int i=0; i<negative.size(); i+=2) {
            if(negative.size() % 2 == 1 && i == negative.size() - 1) {
                ans += negative.get(i);
                break;
            }
            ans += (negative.get(i) * negative.get(i+1));
        }

        System.out.println(ans);
    }

}
