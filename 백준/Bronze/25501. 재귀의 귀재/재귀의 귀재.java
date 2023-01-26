import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());

        String[] input_arr = new String[N];

        for(int i=0; i<N; i++){
           String str = bf.readLine();
           input_arr[i] = str;
        }

        for(String str : input_arr){
            int result = Palindrome(str)[0];
            int cnt = Palindrome(str)[1];
            sb.append(result).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    public static int[] recursion(final String s, int l, int r, int cnt){
        if(l >= r) {
            int[] arr = new int[]{1, cnt};
            return arr;
        }
        else if(s.charAt(l) != s.charAt(r)) {
            int[] arr = new int[]{0, cnt};
            return arr;
        }
        else return recursion(s, l+1, r-1, ++cnt);
    }

    public static int[] Palindrome(final String s){
        return recursion(s, 0, s.length()-1, 1);
    }
}
