import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        int temp = 0;
        int result = 0;
        boolean zero = false;
        for(int i=0;i<N;i++){
            temp = Integer.parseInt(br.readLine());
            if(temp == 1){
                result++;
            }else if(temp > 1) {
                positive.add(temp);
            }
            else if(temp < 0 ){
                negative.add(temp);
            }
            else if(temp == 0){
                zero = true;
            }
        }

        Collections.sort(positive);
        Collections.sort(negative);

        //양수
        if(positive.size()%2 == 0){ //짝수
            for(int i=0;i<positive.size();i+=2){
                result += positive.get(i)*positive.get(i+1);
            }
        }
        else if(positive.size() == 1){
            result += positive.get(0);
        }
        else{ //홀수
            for(int i=1;i<positive.size();i+=2){
                result += positive.get(i)*positive.get(i+1);
            }
            result += positive.get(0);
        }

        //음수
        if(negative.size()%2 == 0){ //짝수
            for(int i=0;i<negative.size();i+=2){
                result += negative.get(i)*negative.get(i+1);
            }
        }
        else if(negative.size() == 1){
            if(!zero){ //0이 없다면
                result += negative.get(0);
            }
        }
        else{ //홀수
            for(int i=0;i<negative.size();i+=2){
                result += negative.get(i)*negative.get(i+1);
            }
            if(!zero){ //0이 없다면
                result += negative.get(negative.size()-1);
            }
        }

        System.out.println(result);
    }
}
