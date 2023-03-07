package week5.SWEA_1953_탈주범검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
 
public class Main {
    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        } 
    }
    public static boolean[][] visit;
    public static int cnt, n, m, r, c, l, map[][];
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       int T = Integer.parseInt(br.readLine());
       for(int t=1;t<T+1;t++) {
           sb.append("#"+t+" ");
           cnt = 0; //탈주범이 위치할 수 있는 곳
           String[] split = br.readLine().split(" ");
           n = Integer.parseInt(split[0]); //map 세로
           m = Integer.parseInt(split[1]); //map 가로
           r = Integer.parseInt(split[2]); //맨홀 위치 x (시작)
           c = Integer.parseInt(split[3]); //맨홀 위치 y (시작)
           l = Integer.parseInt(split[4]); //소요 시간
            
           map = new int[n][m];
           visit = new boolean[n][m];
           for(int i=0;i<n;i++) {
               split = br.readLine().split(" ");
               for(int j=0;j<m;j++) {
                   map[i][j] = Integer.parseInt(split[j]);
               }
           }
 
           bfs();
            
           int result = 0;
           for(int i=0;i<n;i++) {
               for(int j=0;j<m;j++) {
                   if(visit[i][j]) result++;
               }
           }
            
           sb.append(result).append("\n");
       }       
        
       System.out.println(sb);
    }
 
    static Queue<Point> queue;
    private static void bfs() {
        queue = new ArrayDeque<>();
        queue.offer(new Point(r, c));
        visit[r][c] = true;
         
        int round = 0;
        while(!queue.isEmpty()) {
            round++;
            if(round==l) {
                break;
            }
            int size = queue.size();
            for(int i=0;i<size;i++) { 
                Point p = queue.poll();
                if(map[p.x][p.y] == 1) {
                    go(1, p.x-1, p.y, round); //상
                    go(2, p.x+1, p.y, round); //하
                    go(3, p.x, p.y-1, round); //좌
                    go(4, p.x, p.y+1, round); //우
                }
                else if(map[p.x][p.y] == 2) {
                    go(1, p.x-1, p.y, round); //상
                    go(2, p.x+1, p.y, round); //하
                }
                else if(map[p.x][p.y] == 3) {
                    go(3, p.x, p.y-1, round); //좌
                    go(4, p.x, p.y+1, round); //우
                }
                else if(map[p.x][p.y] == 4) {
                    go(1, p.x-1, p.y, round); //상
                    go(4, p.x, p.y+1, round); //우
                }
                else if(map[p.x][p.y] == 5) {
                    go(2, p.x+1, p.y, round); //하
                    go(4, p.x, p.y+1, round); //우
                }
                else if(map[p.x][p.y] == 6) {
                    go(2, p.x+1, p.y, round); //하
                    go(3, p.x, p.y-1, round); //좌
                }
                else if(map[p.x][p.y] == 7) {
                    go(1, p.x-1, p.y, round); //상
                    go(3, p.x, p.y-1, round); //좌
                }
            }
             
             
        }
    }
 
    private static void go(int d, int x, int y, int round) { 
         
        boolean flag = false;
        if(check(x, y)){ //범위 안에 들면
             
            // 연결되어 있는지 확인하고
            if(d==1) { //상
                if(map[x][y]==1||map[x][y]==2||map[x][y]==5||map[x][y]==6) {
                    flag = true;
                }
            }
            else if(d==2) { //하
                if(map[x][y]==1||map[x][y]==2||map[x][y]==4||map[x][y]==7) {
                    flag = true;
                }
            }
            else if(d==3) { //좌
                if(map[x][y]==1||map[x][y]==3||map[x][y]==4||map[x][y]==5) {
                    flag = true;
                }
            }
            else if(d==4) { //우
                if(map[x][y]==1||map[x][y]==3||map[x][y]==6||map[x][y]==7) {
                    flag = true;
                }
            }
             
            if(flag) {
                queue.offer(new Point(x, y));
                visit[x][y] = true;
            }
        }
    }
 
    private static boolean check(int x, int y) { //범위 확인
        if(0<=x && x<n && 0<=y && y<m && !visit[x][y]) {
            return true;
        }
        else {
            return false;
        }
    }
}
