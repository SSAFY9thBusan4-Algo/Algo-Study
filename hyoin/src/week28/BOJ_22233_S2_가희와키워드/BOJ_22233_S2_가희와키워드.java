import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_22233_S2_가희와키워드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashSet<String> set = new HashSet<>();

        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }

        for(int i=0; i<M; i++){
            String str = br.readLine();
            String[] keyword = str.split(",");
            for (int j=0; j<keyword.length; j++){
                if (set.contains(keyword[j])){
                    set.remove(keyword[j]);
                }
            }
            sb.append(set.size()).append("\n");
        }

        System.out.println(sb.toString());

    }
}
