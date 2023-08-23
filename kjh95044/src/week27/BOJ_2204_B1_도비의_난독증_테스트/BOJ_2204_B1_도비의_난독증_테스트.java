import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N;

        do {
            N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            for(int i=0; i<N; i++){
                arr[i] = br.readLine();
            }
            if(arr.length!=0) {
                // 기존 코드
//                Arrays.sort(arr, new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return o1.toUpperCase().compareTo(o2.toUpperCase());
//                    }
//                });
                Arrays.sort(arr, Comparator.comparing(String::toUpperCase));

                sb.append(arr[0]).append("\n");
            }
        } while (N != 0);

        System.out.println(sb);
    }



}
