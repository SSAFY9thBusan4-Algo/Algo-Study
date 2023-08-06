import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
        	String temp = br.readLine();
        	if(map.containsKey(temp)) {
        		map.put(temp, map.get(temp)+1);
        	}
        	else {
        		map.put(temp, 1);
        	}
        }
        
        int max = 0;
        ArrayList<String> list = new ArrayList<String>();
        for(String str: map.keySet()) {
        	if(map.get(str) > max) {
        		max = map.get(str); //개수 업데이트
        		list.clear();
        		list.add(str);
        	}
        	else if(map.get(str) == max) {
        		list.add(str);
        	}
        }
        
        Collections.sort(list);
        
        System.out.println(list.get(0));
        
	}
}
