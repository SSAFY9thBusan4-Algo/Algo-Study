package week1.BOJ_1107_G5_리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static boolean[] possible;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // (0 ≤ N ≤ 500,000)
        int M = Integer.parseInt(br.readLine());

        
        possible = new boolean[10];
        Arrays.fill(possible, true);
        
        if(M != 0) {    //0이면 입력이 안들어옴. 여기 생각지도 못함
            String[] input = br.readLine().split(" ");
        
            for(int i = 0 ; i < M ; i++) {
                possible[Integer.parseInt(input[i])] = false;
            }
        }
        
        //solve
        /*
         * 경우의 수
         * 1. 100번에서 위아래만 누르고 가기
         * 2. 숫자로 눌러서 가기
         * 3. 숫자로 누르고 위로이동/ 아래로 이동
         */
        
        //1.
        int result1 = Math.abs(N-100);
        
        //2.
        int result2 = isPossible(N);
        
        //3.
        if(M != 10 && result2 == Integer.MAX_VALUE) {
        
            //System.out.println("----");
        	int cnt =1;
            int n1 = Integer.MAX_VALUE;
            while(true) {
            	if(N-cnt >= 0) n1 = isPossible(N-cnt);	//아래
                if(n1 != Integer.MAX_VALUE) {
                	result2 = n1 + cnt;			//누르는 횟수 = 번호 누르는 횟수 + 이동횟수
                    break;
                }
                n1 = isPossible(N+cnt);			//위
                if(n1 != Integer.MAX_VALUE) {
                    result2 = n1 + cnt;
                    break;
                }
                    
                if(cnt > result1) break;
                cnt++;
            }
            
            
        }
        
        System.out.println(Math.min(result1, result2));
        
    }
    
    private static int isPossible(int num) {
    	// 누를 수 있는 번호면 누르는 횟수를 반환. 아니면 Integer.MAX_VALUE
        if(num == 0) {
            if(possible[0]) return 1;
            else return Integer.MAX_VALUE;
        }
        int result2 = 0;
        int tempN = num;
        while(tempN > 0) {
            int t = tempN % 10;
            if(possible[t]) {
                result2++;
                tempN = tempN / 10;
            }
            else {
                return Integer.MAX_VALUE;
            }
        }
        
        return result2;
    }
    
    
}
/*
+ 메모리 : 11768 KB
+ 시간 : 96 ms
*/