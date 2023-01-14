import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> input_list = new ArrayList<>();
        List<Integer> output_list = new ArrayList<>();

        while(true){
            int num = Integer.parseInt(bf.readLine());
            if(num == 0){
                break;
            }
            input_list.add(num);
        }

        for(int input : input_list){
            int output = getNumOfPnum(input);
            output_list.add(output);
        }

        for(int output : output_list){
            System.out.println(output);
        }

    }

    public static int getNumOfPnum(int num){
        int cnt = 0;
        for(int i=num+1; i<=2*num; i++){
            if(checkPnum(i)){
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean checkPnum(int num){

        if(num==1){
            return false;
        }
        for(int i=2; i*i<=num; i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}