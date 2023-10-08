package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20125 {

    static int N;
    static char[][] map;
    static int sy, sx, ey, ex;
    static String s;
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int bodyLen, leftArmLen, rightArmLen, leftLegLen, rightLegLen;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++) {
            s = in.readLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);

                if(sy == 0 && sx == 0 && map[i][j] == '*') {
                    sy = i + 1;
                    sx = j;
                }
            }
        }

        leftArmLen = checkLen(sy, sx - 1, 3);
        rightArmLen = checkLen(sy, sx + 1, 1);


        ey = sy + checkLen(sy + 1, sx, 2);
        ex = sx;
        bodyLen = ey - sy;
        leftLegLen = checkLen(ey + 1, sx - 1, 2);
        rightLegLen = checkLen(ey + 1, sx + 1, 2);

        sb.append(sy + 1).append(' ').append(sx + 1).append('\n');
        sb.append(leftArmLen + " " + rightArmLen + " " + bodyLen + " " + leftLegLen + " " + rightLegLen);
        System.out.println(sb);
    }

    private static int checkLen(int nowY, int nowX, int dir) {
        int totalLen = 1;
        int nextY = nowY + dy[dir];
        int nextX = nowX + dx[dir];
        while(nextY >= 0
                && nextY < N
                && nextX >= 0
                && nextX < N
                && map[nextY][nextX] == '*') {
            totalLen++;

            nextY = nextY + dy[dir];
            nextX = nextX + dx[dir];
        }

        return totalLen;
    }
}
