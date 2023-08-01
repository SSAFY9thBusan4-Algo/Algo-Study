import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int L = Integer.parseInt(split[2]);
        int[] station = new int[N+2];
        
        split = br.readLine().split(" ");
        for(int i=1;i<N+1;i++) {
        	station[i] = Integer.parseInt(split[i-1]);
        }
      
        station[0] = 0;
        station[N+1] = L; //고속도로 길이
        
        Arrays.sort(station); //정렬 필요
        
        int start = 1, end = L;
        while(start <= end) {
        	int mid = (start+end)/2;
        	
        	//휴게소 세울  수 있는 수
        	int count = 0;
        	for(int i=1;i<N+2;i++) {
        		count += (station[i]-station[i-1]-1)/mid;
        	}
        	
        	if(count > M) {
        		start = mid + 1;
        	}
        	else {
        		end = mid -1;
        	}
        }
        System.out.println(start);
	}
}
