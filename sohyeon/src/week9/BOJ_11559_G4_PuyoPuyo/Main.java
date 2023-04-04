package week9.BOJ_11559_G4_PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

    private static char[][] map = new char[12][6];
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i=0; i<12; i++) {
            map[i] = in.readLine().toCharArray();
        }
        
        int result = 0;
        // 연쇄가 가능한 상태일 동안 반복
        boolean flag = true;
        while (flag) {
            
            flag = check();
            
            // 연쇄가 가능해 터진 뿌요가 있으면 블록 내리기
            if (flag) {
                down();
                result++;
            }
                        
        }
        
        System.out.println(result);
        
    }


    private static int[] dr = {-1,0,1,0};
    private static int[] dc = {0,1,0,-1};
    private static boolean check() {
        
        boolean flag = false;
        
        boolean[][] visited = new boolean[12][6];
        for (int r=0; r<12; r++) {
            for (int c=0; c<6; c++) {
                if (map[r][c] == '.' || visited[r][c]) continue;
                
                List<int[]> list = new ArrayList<int[]>();
                list.add(new int[] {r,c});
                
                Queue<int[]> queue = new ArrayDeque<int[]>();
                queue.offer(new int[] {r,c});
                visited[r][c] = true;
                
                while(!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    int row = poll[0];
                    int col = poll[1];
                    
                    for (int d=0; d<4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];
                        if (0<=nr && nr<12 && 0<=nc && nc<6 
                                && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
                            visited[nr][nc] = true;
                            list.add(new int[] {nr, nc});
                            queue.offer(new int[] {nr,nc});
                        }
                    }
                }
                
                if (list.size() >= 4) {
                    flag = true;
                    for (int[] rc : list) {
                        map[rc[0]][rc[1]] = '.';
                    }
                }
            }
        }
        
        return flag;
        
    }
    
    private static void down() {
        
        for (int c=0; c<6; c++) {
            
            Queue<Character> queue = new ArrayDeque<>();
            for (int r=11; r>=0; r--) {
                if (map[r][c] != '.') {
                    queue.offer(map[r][c]);
                }
            }
            
            int idx = 11;
            while(!queue.isEmpty()) {
                map[idx--][c] = queue.poll();
            }
            while (idx >= 0 ) {
                map[idx--][c] = '.';
            }
            
        }
        
    }
}