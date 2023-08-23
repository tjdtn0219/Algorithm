import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            System.out.println(func(a,b));
        }


    }

    public static int func(int a, int b) {
        int num = 1;
        for(int i=0; i<b; i++) {
            num = (num*a)%10;
        }
        if(num%10 == 0) return 10;
        else return num%10;
    }
}