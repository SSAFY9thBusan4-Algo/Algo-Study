import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int N, couple;
	static ArrayList<Integer> tMan, sMan, tWoman, sWoman;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        tMan = new ArrayList<>();
        sMan = new ArrayList<>();
        tWoman = new ArrayList<>();
        sWoman = new ArrayList<>();
      
        String[] split1 = br.readLine().split(" ");
        String[] split2 = br.readLine().split(" ");
        for(int i=0;i<N;i++) {
        	int a = Integer.parseInt(split1[i]);
        	int b = Integer.parseInt(split2[i]);
        	
        	if(a > 0) {
        		sMan.add(a);
        	}
        	else {
        		tMan.add(a*-1);
        	}
        	
        	if(b > 0) {
        		sWoman.add(b);
        	}
        	else {
        		tWoman.add(b*-1);
        	}
        }

        //정렬
        Collections.sort(tMan);
        Collections.sort(sMan);
        Collections.sort(sWoman);
        Collections.sort(tWoman);
        
        for(int i=0, j=0;i<tMan.size() && j<sWoman.size();) {
        	int man = tMan.get(i);
        	int woman = sWoman.get(j);
        	
        	if(man <= woman) {
        		i++;
        	}
        	else {
        		couple++;
        		i++;
        		j++;
        	}
        }
        
        for(int i=0, j=0;i<sMan.size() && j<tWoman.size();) {
        	int man = sMan.get(i);
        	int woman = tWoman.get(j);
        	
        	if(man >= woman) {
        		j++;
        	}
        	else {
        		couple++;
        		i++;
        		j++;
        	}
        }
        System.out.println(couple);
    }
}
