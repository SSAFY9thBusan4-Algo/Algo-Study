import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int M, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]); //책의 개수
		M = Integer.parseInt(split[1]); //한 번에 들 수 있는 개수
		split = br.readLine().split(" ");

		//양수, 음수 따로 진행
		ArrayList<Integer> positive = new ArrayList<>();
		ArrayList<Integer> negative = new ArrayList<>();
		int temp;
		for(int i=0;i<N;i++) {
			temp = Integer.parseInt(split[i]);
			if(temp < 0) {
				negative.add(temp);
			}
			else {
				positive.add(temp);
			}
		}
		
		Collections.sort(positive);
		Collections.sort(negative, Collections.reverseOrder());
		int ne = 0;
		int po = 0;
		if(negative.size()!=0) {
			ne = Math.abs(negative.get(negative.size()-1));
		}
		if(positive.size()!=0) {
			po = Math.abs(positive.get(positive.size()-1));
		}
		//가장 먼 거리는 마지막에 가면 되니까 빼기 위해서
		temp = po>ne?po:ne;
		
		result = 0;
		go(positive);
		go(negative);
		
		System.out.println(result-temp);
	}

	private static void go(ArrayList<Integer> list) {
		for(int i = list.size()-1; i>=0;i-=M) {
			result += Math.abs(list.get(i))*2;
		}
	}
}
