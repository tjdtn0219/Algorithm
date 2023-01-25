import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(bf.readLine());
        
        int result = 1;

        if(num != 0) {
            result = func(num);
        }

        System.out.print(result);
    }

    public static int func(int num){
        if(num == 1){
            return 1;
        } else{
            return num * func(num-1);
        }
    }
}
