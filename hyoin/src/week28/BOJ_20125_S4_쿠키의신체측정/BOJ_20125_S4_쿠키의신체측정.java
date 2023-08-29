import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20125_S4_쿠키의신체측정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for(int i=0; i<N; i++){
            map[i]=br.readLine().toCharArray();
        }

        int[] heart = new int[2];
        out:
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                    if(map[i][j]=='*'){
                        heart[0]=i+1;
                        heart[1]=j;
                        break out;
                    }
            }
        }
        sb.append(heart[0]+1).append(" ").append(heart[1]+1).append("\n");

        // 왼쪽팔 길이 탐색
        int left = 0;
        for(int i=heart[1]-1; i>=0; i--){
            if(map[heart[0]][i]=='*'){
                left++;
            }
            else {
                break;
            }
        }
        sb.append(left).append(" ");

        // 오른쪽팔 길이 탐색
        int right = 0;
        for(int i=heart[1]+1; i<N; i++){
            if(map[heart[0]][i]=='*'){
                right++;
            }
            else {
                break;
            }
        }
        sb.append(right).append(" ");

        // 허리 길이 탐색
        int waist = 0;
        int[] waistXY = new int[2];
        for(int i=heart[0]+1; i<N; i++){
            if(map[i][heart[1]]=='*'){
                waist++;
            }
            else {
                waistXY[0]=i;
                waistXY[1]=heart[1];
                break;
            }
        }
        sb.append(waist).append(" ");

        // 왼쪽다리 길이 탐색
        left = 0;
        for(int i=waistXY[0]; i<N; i++){
            if(map[i][waistXY[1]-1]=='*'){
                left++;
            }
            else {
                break;
            }
        }
        sb.append(left).append(" ");

        // 오른쪽다리 길이 탐색
        right = 0;
        for(int i=waistXY[0]; i<N; i++){
            if(map[i][waistXY[1]+1]=='*'){
                right++;
            }
            else {
                break;
            }
        }
        sb.append(right).append(" ");

        System.out.println(sb.toString());
    }
}
