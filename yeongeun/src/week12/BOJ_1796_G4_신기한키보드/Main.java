package src.week12.BOJ_1796_G4_신기한키보드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        
        // 각 알파벳의 시작, 끝 위치 구하기
        int[] start = new int[26];
        int[] end = new int[26];
        Arrays.fill(start, -1);
        
        int len = str.length();
        for(int i = 0 ; i < len ; i++) {
            int n = str.charAt(i) - 'a';
            
            if(start[n] == -1) start[n] = i;
            end[n] = i;
        }
        
        // ll, lr, rl, rr
        int[] D = new int[2];
        
        int pre = -1;
        int result = len;    // enter개수
        for(int i = 0 ; i < 26 ; i++) {
            if(start[i] == -1) continue;

            // 이전 알파벳에서 현재 알파벳으로 오기위해 움직이는 횟수
            if(pre != -1) {
                // 이전의 왼/오 에서 현재 왼/오 를 들리고 현재 왼/오 로 올때
                // 왼->오->왼, 오->오->왼
                int n1 = D[0]+dist(start[pre], end[i], start[i]);
                int n2 = D[1]+dist(end[pre], end[i], start[i]);
                n1 = n1 < n2 ? n1 : n2;
                // 오->왼->오, 왼->왼->오
                int n3 = D[1]+dist(end[pre], start[i], end[i]);
                int n4 = D[0]+dist(start[pre], start[i], end[i]);
                n3 = n3 < n4 ? n3 : n4;
                
                D[0] = n1;
                D[1] = n3;
            }
            else {    // 첫번째일때
                // 오른쪽 들렸다가 왼쪽 오기
                D[0] = end[i] + end[i] - start[i];
                // 오른쪽
                D[1] = end[i];
            }
            pre = i;
        }
        
        result = result + (D[0] < D[1] ? D[0] : D[1]);
        System.out.println(result);
    }
    
    private static int dist(int a, int b, int c) {
    	int n = Math.abs(a - b);
    	n += Math.abs(b - c);
    	return n;
    }
}