import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{

        BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for(int i=0; i<N; i++) {
            String input = bf.readLine();
            if(is_VPS(input)){
                System.out.println("YES");
            } else{
                System.out.println("NO");
            }
        }

    }

    public static boolean is_VPS(String str){
        int lcnt = 0;
        int rcnt = 0;

        for(int i=0 ;i<str.length(); i++){
            if(str.charAt(i) == '(') lcnt++;
            else rcnt++;
            if(rcnt > lcnt) return false;
        }
        if(lcnt == rcnt) return true;
        return false;
    }
}
