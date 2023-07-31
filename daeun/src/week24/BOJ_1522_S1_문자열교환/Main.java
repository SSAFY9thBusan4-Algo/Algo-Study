import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        int acnt = 0;
        int len = str.length();
        
        //a 개수
        for(int i=0;i<len;i++) {
        	if(str.charAt(i) == 'a') {
        		acnt++;
        	}
        }
        
        int result = len;
        for(int i=0;i<len;i++) {
        	int bcnt = 0;
          
        	//i만큼 더한 범위 확인하면서 b의 개수 찾기
        	for(int j=i;j<i+acnt;j++) {
        		if(str.charAt((j+len)%len) == 'b') {
        			bcnt++;
        		}
        	}
        	result = Math.min(result, bcnt);
        }

        System.out.println(result);
    }
}
