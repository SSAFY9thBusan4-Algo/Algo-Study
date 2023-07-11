pakage src.week21.BOJ_2448_G4_별찍기-11;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    static char [][] array;
    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        array = new char[N][(2 * N) - 1];

        for(int i = 0; i < N; i++)
        {
            Arrays.fill(array[i],' ');
        }

        star(N,N - 1,0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < (2 * N) - 1;j++)
            {
                sb.append(array[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    static void star(int n, int x, int y)
    {
        if(n == 3)
        {
            array[y][x] = '*';
            array[y+1][x-1] = '*';
            array[y+1][x+1] = '*';
            array[y+2][x-2] = '*';
            array[y+2][x-1] = '*';
            array[y+2][x] = '*';
            array[y+2][x+1] = '*';
            array[y+2][x+2] = '*';
            return;
        }
        star((n/2), x, y);
        star((n/2), x - (n/2), y + (n/2));
        star((n/2), x + (n/2), y + (n/2));
    }
}
