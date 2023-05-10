package week14.BOJ_2529_S1_부등호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int k;
	static double min = Double.MAX_VALUE;
	static double max = 0;
	static String minr, maxr;
	
	static String[] signs;
	static int[] numbers;
	static boolean visited[] = new boolean[10];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(in.readLine());
		signs = in.readLine().split(" ");
		numbers = new int[k+1];
		
		dfs(0);
		
		System.out.println(maxr);
		System.out.println(minr);
		
	}

	private static void dfs(int cnt) {
		if (cnt == k+1) {
			String num = "";
			for (int i=0; i<=k; i++) {
				num += numbers[i];
			}
			double r = Double.parseDouble(num);
			if (min > r) {
				min = r;
				minr = num;
			}
			else if (max < r) {
				max = r;
				maxr = num;
			}
			return;
		}
		
		for (int i=0; i<10; i++) {
			if (!visited[i]) {
				if (cnt==0) numbers[cnt] = i;
				else {
					if ((signs[cnt-1].equals("<") 
							&& numbers[cnt-1]>i) ||
							(signs[cnt-1].equals(">") 
									&& numbers[cnt-1]<i)) {
						continue;
					}
					numbers[cnt] = i;
				}
				visited[i] = true;
				dfs(cnt+1);
				visited[i] = false;
			}
		}
		
	}
	
}
