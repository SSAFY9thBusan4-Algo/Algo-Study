package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ7682 {

    static String s;
    static String[] ss;
    static String[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        s = in.readLine();
        while(!s.equals("end")) {
            ss = s.split("");
            map = new String[3][3];

            int oNum = 0;
            int xNum = 0;
            for(int i = 0; i < 9; i++) {
                map[i / 3][i % 3] = ss[i];
                if(ss[i].equals("O")) {
                    oNum++;
                }
                else if(ss[i].equals("X")) {
                    xNum++;
                }
            }

            boolean oWin = win("O");
            boolean xWin = win("X");

            if(xWin && !oWin && xNum - oNum == 1) {
                sb.append("valid\n");
            }
            else if(!xWin && oWin && xNum == oNum) {
                sb.append("valid\n");
            }
            else if(!xWin && !oWin && xNum == 5 && oNum == 4) {
                sb.append("valid\n");
            }
            else {
                sb.append("invalid\n");
            }

            s = in.readLine();
        }

        System.out.println(sb);
    }

    static boolean win(String str) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (map[i][0].equals(str) && map[i][0].equals(map[i][1]) && map[i][1].equals(map[i][2]))
                return true;
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            if (map[0][i].equals(str) && map[0][i].equals(map[1][i]) && map[1][i].equals(map[2][i]))
                return true;
        }

        // 대각선
        if (map[0][0].equals(str) && map[0][0].equals(map[1][1]) && map[1][1].equals(map[2][2]))
            return true;
        if (map[0][2].equals(str) && map[0][2].equals(map[1][1]) && map[1][1].equals(map[2][0]))
            return true;

        return false;
    }
}
