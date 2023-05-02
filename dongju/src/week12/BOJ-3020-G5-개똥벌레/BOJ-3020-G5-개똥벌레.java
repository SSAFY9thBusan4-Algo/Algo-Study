import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        
        int[] top = new int[h+1];
        int[] bot = new int[h+1];
        
        for(int i=0; i<n/2; i++) {
           bot[Integer.parseInt(br.readLine())]++;
           top[Integer.parseInt(br.readLine())]++;
        }
        
        int[] dTop = new int[h+1];
        int[] dBot = new int[h+1];
        
        for(int i=1; i<=h; i++) {
        	dTop[i] = dTop[i-1] + top[i];
        	dBot[i] = dBot[i-1] + bot[i];
        }
        
        int min = 200000;
        int cnt = 1;
        for(int i=1; i<=h; i++) {
        	int destroy = 0;
        	
        	destroy += dTop[h] - dTop[i-1];
        	destroy += dBot[h] - dBot[h-i];
        	
        	if(destroy < min) {
        		min = destroy;
        		cnt = 1;
        	}
        	else if(destroy == min) cnt++;
        }
        
        System.out.println(min + " " + cnt);
    }
}
