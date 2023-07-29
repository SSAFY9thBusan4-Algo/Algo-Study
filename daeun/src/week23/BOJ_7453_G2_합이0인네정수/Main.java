import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        for(int i=0;i<N;i++) {
        	String[] split = br.readLine().split(" ");
        	A[i] = Integer.parseInt(split[0]);
        	B[i] = Integer.parseInt(split[1]);
        	C[i] = Integer.parseInt(split[2]);
        	D[i] = Integer.parseInt(split[3]);
        }
        
        //두개 합해서 부분합
        int idx = 0;
        int[] AB =  new int[N*N];
        int[] CD =  new int[N*N];
        for(int i=0;i<N;i++) {
        	for(int j=0;j<N;j++) {
        		AB[idx] = A[i]+B[j];
        		CD[idx++] = C[i]+D[j];
        	}
        }
        
        Arrays.sort(AB);
        Arrays.sort(CD);
        
        //투포인터
        int left = 0;
        int right = N*N-1;
        long result = 0;
        while(left<N*N && right>=0) {
        	int one = AB[left];
        	int two = CD[right];
        	
        	if(one+two < 0) {
        		left++;
        	}
        	else if(one+two > 0) {
        		right--;
        	}
        	//합이 0이면
        	else {
        		long three = 0;
        		long four = 0;
        		//AB랑 CD를 돌면서 각 값이랑 같은게 있는지 찾음
        		while(left<N*N && one==AB[left]) {
        			left++;
        			three++;
        		}
        		while(right>=0 && two==CD[right]) {
        			right--;
        			four++;
        		}
        		//그 개수만큼 더하기
        		result += three*four;
        	}
        }
        System.out.println(result);
    }
}
