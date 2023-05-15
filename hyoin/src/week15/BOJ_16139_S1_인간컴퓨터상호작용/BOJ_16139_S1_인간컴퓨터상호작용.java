package week15.BOJ_16139_S1_인간컴퓨터상호작용;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16139_S1_인간컴퓨터상호작용 {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String S = in.readLine();

		st = new StringTokenizer(in.readLine());
		int q = Integer.parseInt(st.nextToken());
		
		int[][] count = new int[26][S.length()]; // a~z의 알파벳이 0~S.length까지 몇개 존재하는지에 대한 누적합
		count[S.charAt(0)-97][0]=1;
		for(int i=1; i<S.length(); i++) {
			count[S.charAt(i)-'a'][i]=1; // 해당 알파벳이 몇번째 문자 위치에 있는지 저장
		}
		
		// i알파벳이 0~j문자열까지 몇개 존재하는지 저장(누적합)
		for(int i=0; i<26; i++) {
			for(int j=1; j<S.length(); j++) {
				count[i][j]+=count[i][j-1];
			}
		}
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(in.readLine());
			char alphabet = st.nextToken().charAt(0);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if(start==0) { // 시작이 0일 때
				sb.append(count[alphabet-'a'][end]).append("\n");
			}
			else {
				sb.append(count[alphabet-'a'][end]-count[alphabet-'a'][start-1]).append("\n");;
			}
		}
		
		System.out.println(sb.toString());
	}
}
