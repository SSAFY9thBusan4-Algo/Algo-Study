import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			list.add(i);
		}
		while(list.size()>=2) {
			//앞을 지우면 하나씩 당겨지니까 홀수가 지워짐
			for(int i=0;i<list.size();i++) {
				list.remove(i);
			}
		}
		System.out.println(list.get(0));
		
	}
}
