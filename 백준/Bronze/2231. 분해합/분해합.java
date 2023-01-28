import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());

        for(int i=1; i<N; i++){
            if(N == func(i)){
                System.out.println(i);
                return;
            }
        }
        System.out.println("0");

    }

    public static int func(int num){
        int tmp = num;
        int sum = num;
        while(num>0){
            sum += num%10;
            num /= 10;
        }
        return sum;
    }
}
