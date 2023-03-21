package week7.BOJ_2174_G5_로봇시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    
    private static int A,B;
    private static Map<Integer, Robot> robots = new HashMap<>();
    private static int[][] map;
    private static int[] dr = {-1,0,1,0};
    private static int[] dc = {0,1,0,-1};
    private static boolean flag;
    
    static class Robot {
        int x;
        int y;
        int dir;
        public Robot(int x, int y, int dir) {
            super();
            this.x = x;
            this.y = y;
            this.dir = dir;
        }                
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());
        
        map = new int[B+1][A+1];
        
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String d = st.nextToken();    
            map[y][x] = i;
            switch (d) {
            case "E":
                robots.put(i, new Robot(x, y, 1));
                break;
            case "W":
                robots.put(i, new Robot(x, y, 3));
                break;
            case "S":
                robots.put(i, new Robot(x, y, 0));
                break;
            case "N":
                robots.put(i, new Robot(x, y, 2));
                break;
            }
        }
        
        // 명령 저장
        String[][] orders = new String[M][3];
        for (int i=0; i<M; i++) {
            orders[i] = in.readLine().split(" ");
        }
        
        for (String[] ord : orders) {
            if (flag) break;
            go(ord); 
        }
        
        if (!flag) System.out.println("OK");
        
    }

    private static void go(String[] ord) {
        int num = Integer.parseInt(ord[0]);
        String type = ord[1];
        int cnt = Integer.parseInt(ord[2]);
        
        Robot bot = robots.get(num);
        int col = bot.x;
        int row = bot.y;
        int dir = bot.dir;        
        for (int i=0; i<cnt; i++) {
            
            if (type.equals("R")) {
                dir = (dir==0) ? 3 : dir-1;
            }else if (type.equals("L")) {
                dir = (dir==3) ? 0 : dir+1;
            }else {
                int nr = row + dr[dir];
                int nc = col + dc[dir];
                
                if (0>=nr || nr>B || 0>=nc || nc>A) {  // 벽에 충돌
                    System.out.println("Robot "+num+" crashes into the wall");
                    flag = true;
                    return;
                }
                else if (map[nr][nc] != 0) {  // 다른 로봇과 충돌
                    System.out.println("Robot "+num+" crashes into robot "+map[nr][nc]);
                    flag = true;
                    return;
                }
                else {
                    map[row][col] = 0;
                    map[nr][nc] = num;
                    row = nr;
                    col = nc;
                }
            }    
            
        }
        
        bot.y = row;
        bot.x = col;
        bot.dir = dir;
        robots.replace(num, bot);
        
    }
    
}
