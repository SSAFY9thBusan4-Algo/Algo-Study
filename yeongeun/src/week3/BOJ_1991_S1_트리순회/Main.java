package src.week3.BOJ_1991_S1_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[][] tree;    // 입력받은 트리
    private static StringBuilder sb;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력파일 객체화
        int N = Integer.parseInt(br.readLine());	//1 ≤ N ≤ 26
        tree = new char[26][2];
        String input;
        for(int i = 0 ; i < N ; i++) {
            input = br.readLine();
            int idx = input.charAt(0)-'A';	//A부터 알파벳순으로 index에 들어가게 함.
            tree[idx][0] = input.charAt(2);
            tree[idx][1] = input.charAt(4);
        }

        // solve
        
        // curDelNum으로 어떤 delta를 사용할지 설정하고 travel로 순회 시작.
        for(curDelNum = 0 ; curDelNum < 3 ; curDelNum++) {
            travel(0);
            sb.append("\n");
        }

        // 출력
        System.out.println(sb);
    }
    
    // -1 : 현재 노드 출력. 0 : 좌, 1 : 우
    private static int[][] delta = {{-1,0,1},{0,-1,1},{0,1,-1}};
    private static int curDelNum = 0;	//현재 pre,in,post 중에 뭐인지 나타내는 변수.
    
    private static void travel(int index) {
        for(int i = 0 ; i < 3 ; i++) {
            int direction = delta[curDelNum][i];	//-1,0,1
            
            if(direction == -1) {        // 현재 노드 방문인 경우
                sb.append((char)(index+'A'));
            }
            else if(tree[index][direction] != '.'){    //좌 우 방문이 가능하면
                int nextidx = tree[index][direction] - 'A';
                travel(nextidx);
            }
        }
    }
    /*
    private static void preorder(int index) {
        sb.append((char)(index+'A'));
        
        if(tree[index][0] != '.') {
            int nextidx = tree[index][0] - 'A';
            preorder(nextidx);
        }
        
        if(tree[index][1] != '.') {
            int nextidx = tree[index][1] - 'A';
            preorder(nextidx);
        }
    }
    
    private static void inorder(int index) {
        if(tree[index][0] != '.') {
            int nextidx = tree[index][0] - 'A';
            inorder(nextidx);
        }
        
        sb.append((char)(index+'A'));
        
        if(tree[index][1] != '.') {
            int nextidx = tree[index][1] - 'A';
            inorder(nextidx);
        }
    }
    
    private static void postorder(int index) {
        if(tree[index][0] != '.') {
            int nextidx = tree[index][0] - 'A';
            postorder(nextidx);
        }
        
        if(tree[index][1] != '.') {
            int nextidx = tree[index][1] - 'A';
            postorder(nextidx);
        }

        sb.append((char)(index+'A'));
    }
    */
}