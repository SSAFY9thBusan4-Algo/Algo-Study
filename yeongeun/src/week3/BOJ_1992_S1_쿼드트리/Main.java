package src.week3.BOJ_1992_S1_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static char[][] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력파일 객체화
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0 ; i < N ; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // solve
        /*
         * 풀다가 출력을 어떻게해야할지 좀 의문이 생겨서 질문게시판의 반례를 찾아보았다.
         * (0100)이렇게 4군데가 모두 같아도 압축이 되는줄 알았는데
         * 알고보니 4군데가 모두 1또는0일 경우에만 압축이 되는것이었다.
         * 
         * 바꾸고 제출하니 메모리 초과가 떴다. 
         * 재귀 호출이나 String에서 이상한건가 싶어서 StringBuilder로도 바꿔봤는데
         * 
         * 알고보니 재귀호출에서 length가 1인 경우를 처리안해줘서 무한반복 돌아서
         * 스택 오버플로우가 발생한 것이었다..!!ㅜㅜ
         * 최대값인 64만 계속 쳐다보다가 이거 찾는다고 시간이 좀 걸렸다.
         * 앞으로는 최대값 뿐만아니라 최소값도 신경을 써야겠다.
         * 
         * 1인 경우를 처리해주고 나서 String과 StringBuilder도 차이를 비교해 보았는데
         * 시간이 거의 2배가량 차이났다...!! 재귀 반환 값도 String 사용을 지양해야 겠다.
         */

        if(N == 1) {
            System.out.println(map[0][0]);
        }
        else {
        // 출력
            System.out.println(splitQuad(N,0,0));
        }
    }
    
    private static StringBuilder splitQuad(int length, int startX, int startY) {
        //length : 배열의 가로길이
        if(length == 2) {
            char a = map[startX][startY];
            char b = map[startX][startY+1];
            char c = map[startX+1][startY];
            char d = map[startX+1][startY+1];
            if(a == b && c == d && a == c) {
                return new StringBuilder().append(a);
            }
            else {
                return new StringBuilder("(").append(a).append(b).append(c).append(d).append(")");
            }
        }
        
        
        StringBuilder sa = splitQuad(length/2, startX, startY);
        String a = sa.toString();
        String b = splitQuad(length/2, startX, startY + length/2).toString();
        String c = splitQuad(length/2, startX + length/2, startY).toString();
        String d = splitQuad(length/2, startX + length/2, startY + length/2).toString();
        
        if((a.equals("1")||a.equals("0")) && a.equals(b)&&c.equals(d)&& a.equals(c)) {
            return sa;
        }
        else {
            return new StringBuilder("(").append(a).append(b).append(c).append(d).append(")");
        }
        
    }
    
}