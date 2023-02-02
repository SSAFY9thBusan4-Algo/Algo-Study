package week1.BOJ_1107_G5_리모컨;

import java.util.*;
public class Main {
    static boolean[] broken = new boolean[10];
    static int N,M;
    static int limit,min;
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        N=sc.nextInt();
        M=sc.nextInt();
        for(int i=0;i<M;i++){
            broken[sc.nextInt()]=true;
        }
        limit = (N+"").length()+1;
        min = Math.abs(N - 100);
        for (int i = 0; i < 10; i++){
            if(broken[i]){
                continue;
            }
            solve(1,i);
        }
        System.out.println(min);
    }
    static void solve(int cnt, int ch) {
        if(limit < cnt){
            return ;
        }
        min = Math.min(min, cnt + Math.abs(N - ch));
        for (int i = 0; i < 10; i++){
            if(broken[i]){
                continue;
            }
            solve(cnt+1, (ch * 10) + i);
        }
    }
}