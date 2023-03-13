package week5.SWEA_4014_활주로건설;

import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
    static int N, X, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<T+1;t++) {
            sb.append("#"+t+" ");
            cnt = 0; //활주로 건설 불가능 경우의 수
            String[] split = br.readLine().split(" ");
            N = Integer.parseInt(split[0]); //지도 한 변 길이
            X = Integer.parseInt(split[1]); //경사로 길이
            int[][] map = new int[N][N];
            int[][] map2 = new int[N][N];
            for(int i=0;i<N;i++) {
                split = br.readLine().split(" ");
                for(int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(split[j]);
                    map2[j][i] = map[i][j];
                }
            }
 
            Search(map);
            Search(map2);
            sb.append(2*N - cnt).append("\n");
             
        }
        System.out.println(sb);
    }
 
    private static void Search(int[][] map) {
        for(int i=0;i<N;i++) {
            int same = 1, dif; 
             
            for(int j=1;j<N;j++) {
                if(map[i][j-1] == map[i][j]) { //앞이랑 같으면
                    same++;
                }
                else {
                    dif = map[i][j-1] - map[i][j];
                     
                    if(dif == 1) { //내려가는 경사로 //지금부터 경사로 길이 체크, 값은 다 같은지
                        same = 0;
                        if(check(i, j, map)) {
                            j+=X-1;
                        }
                        else {
                            cnt++;
                            same = 1;
                            break;
                        }
                    }
                    else if(dif == -1 && same>=X) { //올라가는 경사로 //앞에 유지된 길이 확인
                         
                        same = 1;
                    }
                    else {
                        cnt++;
                        same = 1;
                        break;
                    }
                }
            }
        }       
    }
 
    //경사로 만들 길이가 있는지 확인
    private static boolean check(int i, int j, int[][] map) {
        int k = 1; //경사로
        while(j+k<N && map[i][j+k] == map[i][j] && k<X) { //주어진 길이 안인지, 같은 높이인지, X범위 안에 전부
            k++;
        }
        if(k == X) return true;
        return false;
    }   
}