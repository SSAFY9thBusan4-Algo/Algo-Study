import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	static class Point {
		int island;
		int weight;
		public Point(int island, int weight) {
			super();
			this.island = island;
			this.weight = weight;
		}
	}
	
	static int N, M, fact, ory, max, min;
	static boolean[] visit;
	static ArrayList<Point>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        
        list = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) {
        	list[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++) {
        	split = br.readLine().split(" ");
        	int a = Integer.parseInt(split[0]);
        	int b = Integer.parseInt(split[1]);
        	int c = Integer.parseInt(split[2]);
        	list[a].add(new Point(b, c));
        	list[b].add(new Point(a, c));
        	
        	max = Math.max(max, c);
        }

        split = br.readLine().split(" ");
        fact = Integer.parseInt(split[0]);
        ory = Integer.parseInt(split[1]);
        
        //이분탐색으로 중량 제한을 찾는다.
        //bfs로 두 섬을 연결할 때까지 돌리는데, 최대 중량을 넘지 않는 값중 제일 큰 값을 찾아야 한다.
        while(min <= max) {
        	boolean flag = false;
        	
        	int mid = (min + max) / 2;
        	
        	Queue<Integer> queue = new ArrayDeque<>();
        	visit = new boolean[N+1];
        	queue.offer(fact);
        	visit[fact] = true;
        	
        	while(!queue.isEmpty()) {
        		int now = queue.poll();
        		
        		if(now == ory) {
        			flag = true;
        			break;
        		}
        		
        		for(int i=0;i<list[now].size();i++) {
        			Point p = list[now].get(i);
        			if(p.weight >= mid 
        				&& !visit[p.island]) {
        				visit[p.island] = true;
        				queue.offer(p.island);
        			}
        		}
        	}
        	
        	if(flag) {
        		min = mid + 1;
        	}
        	else {
        		max = mid - 1;
        	}
        }
        System.out.println(max);
    }
}
