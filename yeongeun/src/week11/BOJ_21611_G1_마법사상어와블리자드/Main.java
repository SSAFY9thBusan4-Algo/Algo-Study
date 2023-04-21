package src.week11.BOJ_21611_G1_마법사상어와블리자드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    private static int N, M;

    private static int[] linemap;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        
        result = new int[4];
        run(br);
        
        int r = getResult();
        System.out.println(r);
    }

    private static int getResult() {
        int sum = 0;
        for (int i = 1; i < 4; i++) {
            sum += i * result[i];
        }
        return sum;
    }

    private static void run(BufferedReader br) throws IOException {
        for (int i = 0; i < M; i++) {
            String[] in = br.readLine().split(" ");
            blizzard(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }
    }

    private static void blizzard(int d, int s) {
        distroy(d, s);
        moveEmptyAndExplode();
    }

    private static void moveEmptyAndExplode() {
    	Deque<int[]> deque = new ArrayDeque<>();
    	// 0 없애기
    	for(int i = 1 ; i < linemap.length; i++) {
    		if(linemap[i] == 0) continue;
    		if(!deque.isEmpty() && linemap[i] == deque.peekLast()[0]) {
    			++deque.peekLast()[1];
    		}
    		else {
    			deque.offerLast(new int[] {linemap[i], 1});
    		}
    	}
    	
    	// 4개 터지기
    	while(true) {
    		Deque<int[]> newdeque = new ArrayDeque<int[]>();
    		boolean flag = true;
        	while(!deque.isEmpty()) {
        		int[] cur = deque.pollFirst();
        		if(cur[1] >= 4) {
        			result[cur[0]] += cur[1];	// 결과 저장
        			continue;
        		}
        		
        		if(!newdeque.isEmpty() && newdeque.peekLast()[0] == cur[0]) {
        			newdeque.peekLast()[1] += cur[1];
        			flag = false;
        		}
        		else {
        			newdeque.offerLast(cur);
        		}
        	}
        	
        	deque = newdeque;
        	if(flag) break;		// 하나라도 병합된게 있으면 다시 확인.
    	}
    	
    	// 그룹별로 map 다시 그리기
    	writeGroup(deque);
    }
    
    private static void writeGroup(Deque<int[]> deque) {
    	// 구슬 개수, 구슬 번호
    	int[] newline = new int[linemap.length];
    	int p = 0;
    	while(!deque.isEmpty()) {
    		int[] cur = deque.pollFirst();
    		if(++p == newline.length) break;
    		newline[p] = cur[1];
    		if(++p == newline.length) break;
    		newline[p] = cur[0];
    	}
    	linemap = newline;
    }
    

    private static int[] start = { 0, 7, 3, 1, 5 };
    private static int[] weight = { 0, 15, 11, 9, 13 };

    private static void distroy(int d, int s) {

        int dx = start[d];
        linemap[dx] = 0;
        int w = weight[d];
        dx += w;

        while (--s > 0) {
            linemap[dx] = 0;
            w += 8;
            dx += w;
        }
    }

    private static void input(BufferedReader br) throws IOException {
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 2차원을 1차원으로 변환해서 저장
        linemap = new int[N * N];
        int N2 = N / 2;
        int cnt = 0;
        for (int k = 1; k <= N2; k++) {

            int start = N2 - k;
            int end = N2 + k;

            for (int i = start + 1; i < end; i++) {
                linemap[++cnt] = map[i][start];
            }
            for (int i = start; i < end; i++) {
                linemap[++cnt] = map[end][i];
            }
            for (int i = end; i > start; i--) {
                linemap[++cnt] = map[i][end];
            }
            for (int i = end; i >= start; i--) {
                linemap[++cnt] = map[start][i];
            }
        }
    }
}
