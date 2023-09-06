import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] d = new int[str.length()+1];
        d[0] = 1;
        //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
        //A B C D E F G H I J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
        //26 : BF Z         265 : BFE ZE
        //27 : BG
        //34 : CD
        //37 : CG
        //11 : AA K         111 : AA+A, K+A, A+K
        d[0] = 1;
        d[1] = 1;

        for(int i=2; i<=str.length(); i++) {
            int now = str.charAt(i-1) - '0';
            
            if(now != 0) d[i] = d[i-1]%MOD;

            int prev = str.charAt(i-2) - '0';

            int value = prev*10+now;

            if(value >= 10 && value <= 26) {
                d[i] += d[i-2];
                d[i] %= MOD;
            }
        }

        if(str.charAt(0)=='0') System.out.println(0);
        else System.out.println(d[str.length()]);

    }
}
