package week7.BOJ_10775_G2_공항;

import java.util.*;
import java.io.*;

public class BOJ_10775_G2_공항 {

    static StringBuilder sb = new StringBuilder();
    static int[] parents;
    static int V;

    static void makeSet() {
        parents = new int[V+1];
        for(int i=1; i<=V; i++){
            parents[i] = i;
        }
    }

    static int findSet(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot){
            return false;
        }
        parents[aRoot] = bRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {

        // 1~G까지의 번호가 있다.
        // P개의 비행기가 도착 예정
        // i번째 비행기를 1번부터 i번째 게이트중에 하나에 도킹

        // 앞에서부터 모든 경우의 수를 탐색하는 방법으로 풀자고 하니
        // 수가 너무 커서 무조건 시간 초과가 날거 같았다.
        // 한 시간정도 고민하다가 알고리즘 분류를 보고
        // 분리 집합이라는 힌트를 봤다.
        // 분리 집합은 서로소 집합이라고도 불린다.
        // 게이트를 서로소 집합에 넣다가 대표자가 0이 되면 종료하게 하면 될거 같았다.
        // 가중치는 없어서 따로 edge 클래스를 만들지는 않았다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int gateCnt = Integer.parseInt(br.readLine());
        V = gateCnt;
        int planeCnt = Integer.parseInt(br.readLine());
        int[] gates = new int[planeCnt];

        for (int i = 0; i < planeCnt; i++) {
            gates[i] = Integer.parseInt(br.readLine());
        }

        makeSet();
        int result =0;

        for (int g: gates
             ) {
            int temp = findSet(g);
            if(temp == 0){ // 대표자가 0이되면 종료
                break;
            }

            // 해당 게이트를 쓸수 있으면 쓰고, 못 쓴다면 그 아래번호 게이트틀 사용
            union(temp, temp-1); 
            result ++;
        }

        System.out.println(result);
    }
}