import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int len = Integer.parseInt(split[1]);
        int max = Integer.parseInt(split[2]);
        int time = 0;
        int weight = 0;
        
        Queue<Integer> truck = new ArrayDeque<>();
        Queue<Integer> bridge = new ArrayDeque<>();
        
        split = br.readLine().split(" ");
        for(int i=0;i<N;i++) {
        	truck.offer(Integer.parseInt(split[i]));
        }
        
        for(int i=0;i<len;i++) {
        	bridge.add(0); //각 다리 위치에 있는 트럭 무게
        }
        
        while(!bridge.isEmpty()) {
        	time++;
        	weight -= bridge.poll(); //시간이 지나니까 다리 위치 하나씩 옮기는 느낌으로 빼기
        	
        	if(!truck.isEmpty()) {
        		if(truck.peek()+weight <= max) { //최대하중 확인
        			int now = truck.poll();
        			weight += now;
        			bridge.offer(now);
        		}
        		else {
        			bridge.offer(0); //못 가면 0 올리기
        		}
        	}
        }
        
        System.out.println(time);
    }
}
