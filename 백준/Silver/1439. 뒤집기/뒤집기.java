import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
//        System.out.println("Str : " + str);
        int n = str.length();
        int[] cnt = new int[2];
        cnt[str.charAt(0)-'0']++;
        for(int i=1; i<n; i++) {
            if(str.charAt(i-1) != str.charAt(i)) cnt[str.charAt(i)-'0']++;
        }

//        System.out.println(cnt[0] + " " + cnt[1]);

        System.out.println(Math.min(cnt[0], cnt[1]));


    }

    public static boolean isSame(String s) {
        for(int i=1; i<s.length(); i++) {
            if (s.charAt(0) == s.charAt(i)) return false;
        }
        return true;
    }
}
