import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];

        for(int i=0; i<N; i++){
            arr[i] = br.readLine();
        }

        sol(arr);
    }

    public static void sol(String[] arr){
        for(String str : arr){
            StringBuilder reverseStr = new StringBuilder();
            for(int i=0; i<str.length(); i++){
                reverseStr.append(str.charAt(str.length() - 1 - i));
            }

            for(int i=0; i< arr.length; i++){
                if(reverseStr.toString().equals(arr[i])){
                    System.out.println(reverseStr.length() + " " + reverseStr.charAt(reverseStr.length()/2));
                    return;
                }
            }
        }
    }

}
