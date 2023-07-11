import java.util.Scanner;

public class Main {
    static int[][] map;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int N = scanner.nextInt();
        
        
        map = new int[N][2 * N - 1];
        star(N - 1, 0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                if (map[i][j] == 1) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void star(int x, int y, int size) {
        if (size == 3) {
            map[y][x] = 1;
            map[y + 1][x - 1] = 1;
            map[y + 1][x + 1] = 1;
            map[y + 2][x + 2] = 1;
            map[y + 2][x + 1] = 1;
            map[y + 2][x] = 1;
            map[y + 2][x - 1] = 1;
            map[y + 2][x - 2] = 1;
        } else {
            size /= 2;
            star(x, y, size);
            star(x - size, y + size, size);
            star(x + size, y + size, size);
        }
    }
}
