import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] split = str.split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        char[] array = new char[N];
        boolean[] visit = new boolean[N];
        array = br.readLine().toCharArray();

        int result = 0;
        for(int i=0;i<N;i++){
            //사람 위치에서
            if(array[i]=='P'){
                //갈 수 있는 범위
                int start = Math.max(i-K, 0);
                int end = Math.min(i+K, N-1);
                for(int j=start;j<end+1;j++){
                    //방문하지 않은 햄버거
                    if(array[j] == 'H' && !visit[j]){
                        visit[j] = true;
                        result++;
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
