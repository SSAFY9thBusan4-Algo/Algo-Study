import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean[][] arr = new boolean[101][101];
        
        int n = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향(0, 1, 2, 3)(우, 상, 좌, 하)
            int g = Integer.parseInt(st.nextToken()); // 세대
            
            ArrayList<int[]> list = new ArrayList<>();
            arr[y][x] = true;

            for(int j=0; j<=g; j++) {
                if(j == 0) {
                    switch (d) {
                    case 0:
                        list.add(new int[] {x+1, y, d});
                        break;
                    case 1:
                        list.add(new int[] {x, y-1, d});
                        break;
                    case 2:
                        list.add(new int[] {x-1, y, d});
                        break;
                    case 3:
                        list.add(new int[] {x, y+1, d});
                        break;
                    }
                    
                    continue;
                }
                
                int nx = list.get(list.size()-1)[0];
                int ny = list.get(list.size()-1)[1];
                for(int k=list.size()-1; k>=0; k--) {
                    int[] temp = list.get(k);
                    int nd = (temp[2]+1) % 4;
                    
                    switch (nd) {
                    case 0:
                        list.add(new int[] {++nx, ny, nd});
                        break;
                    case 1:
                        list.add(new int[] {nx, --ny, nd});
                        break;
                    case 2:
                        list.add(new int[] {--nx, ny, nd});
                        break;
                    case 3:
                        list.add(new int[] {nx, ++ny, nd});
                        break;
                    }
                }
            }
            
            for(int[] xy: list) {
            	arr[xy[1]][xy[0]] = true;
            }
        }
        
        // 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수
        int ans = 0;
        for(int i=0; i<100; i++) {
        	for(int j=0; j<100; j++) {
        		if(arr[i][j] && arr[i][j+1] && arr[i+1][j] && arr[i+1][j+1]) ans++; 
        	}
        }
        
        System.out.println(ans);
    }
}
