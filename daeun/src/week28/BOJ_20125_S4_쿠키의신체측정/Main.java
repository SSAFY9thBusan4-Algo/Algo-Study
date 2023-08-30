import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] cookie = new boolean[N+1][N+1];
		int heartX = 0, heartY = 0, waistX = 0;
		int leftArm = 0, rightArm = 0, waist = 0, leftLeg = 0, rightLeg = 0;
		boolean flag = true;
		
		for(int i=1;i<N+1;i++) {
			String str = br.readLine();
			for(int j=1;j<N+1;j++) {
				char temp = str.charAt(j-1);
				cookie[i][j] = temp=='*' ? true : false;
				if(flag && cookie[i][j]) {
					flag = false;
					heartX = i+1;
					heartY = j;
				}
			}
		}
		
		//팔은 y축만 바뀐다.. 허리는 밑으로만 간다..
		int idx = 1;
		while(true) {
			flag = true;
			//왼쪽 팔
			if(heartY-idx>0 && cookie[heartX][heartY-idx]) {
				leftArm++;
				flag = false;
			}
			//오른쪽 팔
			if(heartY+idx<N+1 && cookie[heartX][heartY+idx]) {
				rightArm++;
				flag = false;
			}
			//허리
			if(heartX+idx<N+1 && cookie[heartX+idx][heartY]) {
				waist++;
				waistX = heartX+idx;
				flag = false;
			}
			
			if(flag) break;
			idx++;
		}
		
		//다리는 x축만 바뀐다..
		idx = 1;
		while(true) {
			if(waistX+idx>N) {
				break;
			}
			//왼쪽 다리
			if(cookie[waistX+idx][heartY-1]) {
				leftLeg++;
			}
			//오른쪽 다리
			if(cookie[waistX+idx][heartY+1]) {
				rightLeg++;
			}
			idx++;
		}
		
		
		System.out.println(heartX+" "+heartY);
		System.out.println(leftArm+" "+rightArm+" "+waist+" "+leftLeg+" "+rightLeg);
	}
}
