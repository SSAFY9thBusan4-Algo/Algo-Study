import java.io.*;
import java.util.*;

public class BOJ_16165_S3_걸그룹마스터준석이 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, String[]> girlGroup = new HashMap<>();
		
		// 걸그룹 사전 입력 
		while(N-->0) {
			String groupName = in.readLine();
			int groupNumber = Integer.parseInt(in.readLine());
			String[] groupMember = new String[groupNumber];
			
			for(int i=0; i<groupNumber; i++) {
				groupMember[i] = in.readLine();
			}
			
			Arrays.sort(groupMember);
			
			girlGroup.put(groupName, groupMember);
		}
		
		// 문제 출제 
		while(M-->0) {
			String problem = in.readLine();
			int type = Integer.parseInt(in.readLine());
			// 해당 팀에 속한 멤버의 이름을 사전순으로 한 줄에 한 명씩 출력
			if(type == 0) {
				for(String girlGroupName : girlGroup.keySet()) {
					if(girlGroupName.equals(problem)) {
						for(int i=0; i<girlGroup.get(problem).length; i++) {
							sb.append(girlGroup.get(problem)[i]).append('\n');
						}
					}
				}
			}
			// 해당 멤버가 속한 팀의 이름을 출력
			else {
				for(String girlGroupName : girlGroup.keySet()) {
					for(int i=0; i<girlGroup.get(girlGroupName).length; i++) {
						if(girlGroup.get(girlGroupName)[i].equals(problem)) {
							sb.append(girlGroupName).append('\n');
						}
					}
				}
			}
		}
		
		System.out.println(sb);
	}

}
