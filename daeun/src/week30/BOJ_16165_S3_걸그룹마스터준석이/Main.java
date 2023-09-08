import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String group = br.readLine();
            int num = Integer.parseInt(br.readLine());
            ArrayList<String> names = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                names.add(br.readLine());
            }
            Collections.sort(names); //사전순 출력을 위한 정렬
            map.put(group, names);
        }


        for (int i = 0; i < M; i++) {
            String quiz = br.readLine();
            String type = br.readLine();
            if (type.equals("0")) {
                for (String name : map.get(quiz)) {
                    sb.append(name).append("\n");
                }
            }
            else{
                for(String name: map.keySet()){
                    if(map.get(name).contains(quiz)){
                        sb.append(name).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
