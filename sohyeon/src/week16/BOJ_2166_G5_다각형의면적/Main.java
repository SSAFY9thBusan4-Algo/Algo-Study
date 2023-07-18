package week16.BOJ_2166_G5_다각형의면적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		double[] xlist = new double[N+1];
		double[] ylist = new double[N+1];
		
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			xlist[i] = Integer.parseInt(st.nextToken());
			ylist[i] = Integer.parseInt(st.nextToken());
		}
		
		xlist[N] = xlist[0];
		ylist[N] = ylist[0];
				
		double sum1 = 0;
		double sum2 = 0;
		for (int i=0; i<N; i++) {						
			sum1 += xlist[i]*ylist[i+1];
			sum2 += xlist[i+1]*ylist[i];
		}
		double result = Math.abs(sum1-sum2)/2.0;
//		result = ((double)Math.round(result*10)/10);
//		System.out.println(result);
		String area = String.format("%.1f", result);
		System.out.println(area);
		
	}
	
}
