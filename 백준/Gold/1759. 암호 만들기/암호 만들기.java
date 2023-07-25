import java.util.*;
import java.io.*;

public class Main {

    public static int m, n;
    public static char[] arr;
    public static char[] res;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        m = Integer.parseInt(strings[0]);
        n = Integer.parseInt(strings[1]);

        arr = new char[n];
        res = new char[m];

        strings = br.readLine().split(" ");
        for(int i=0; i<n; i++) arr[i] = strings[i].charAt(0);

        Arrays.sort(arr);

        btk(0,0);


    }

    public static void btk(int k, int last_i) {
        if(k==m) {
            if(check(res)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < m; i++) sb.append(res[i]);
                System.out.println(sb.toString());
            }
            return ;
        }

        for(int i=last_i; i<n; i++) {
            res[k] = arr[i];
            btk(k+1, i+1);
        }

    }

    public static boolean check(char[] res) {
        int cnt_vowel = 0;      //모음 개수
        int cnt_cons = 0;       //자음 개수

//        System.out.println("len : " + res.length);
        for(char c : res) {
//            System.out.print(c + " ");
            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') cnt_vowel++;
            else cnt_cons++;
        }
//        System.out.println();
        if(cnt_vowel>=1 && cnt_cons>=2) return true;
        return false;
    }
}
