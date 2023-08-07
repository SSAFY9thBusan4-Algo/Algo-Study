import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, K, result, kit[], day[];
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[26];
        int[] check = new int[26];
        int N = Integer.parseInt(br.readLine());

        String word = br.readLine();
        for(int i=0;i<word.length();i++){
            count[word.charAt(i)-'A']++; //알파벳 개수 증가
        }

        int result = 0;
        for(int i=1;i<N;i++){
            check = count.clone(); //이거 안해서 답이 계속 1만 나왔네
            String str = br.readLine();
            //길이로 비슷한 단어가 될 수 없음
            if(Math.abs(str.length() - word.length()) > 1) {
                continue;
            }
            int cnt = 0;
            //문자열 알파벳 비교
            for(int j=0;j<str.length();j++){
                int temp = str.charAt(j) - 'A';
                //알파벳이 존재하면, 값 증가 시키기
                if(check[temp] > 0){
                    cnt++;
                    check[temp]--; //있으면 하나씩 빼기
                }
            }
            //길이가 짧으면 그 전부가 같아야 함
            if(word.length() > str.length() ){
                if(cnt == str.length()){
                    result++;
                }

            }
            //길이가 길다면 기준과 같아야 함
            else if(word.length() < str.length()){
                if(cnt == word.length()){
                    result++;
                }
            }
            else{
                //같은 구성
                if(cnt == word.length()){
                    result++;
                }
                //하나를 바꾸면 같은 구성
                else if(cnt == word.length()-1){
                    result++;
                }
            }
        }

        System.out.println(result);

    }
}
