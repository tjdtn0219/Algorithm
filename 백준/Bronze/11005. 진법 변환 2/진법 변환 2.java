import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

interface Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] input = br.readLine().split(" ");
         int N = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        String result = "";
        result = convertBinary(N, B);
        System.out.println(result);
    }
    public static String convertBinary(int N, int B) {
        int quotient = N / B;
        int remainder = N % B;
        String result = "", str;
        str = convertChar(remainder, true);

        if(quotient >= B) {
            result = convertBinary(quotient, B);
        } else {
            result = convertChar(quotient, false);
        }
        return result+str;
    }

    public static String convertChar(int c, boolean isZero) {
        String result = "";
        if(c > 9) {
            result = String.valueOf((char)('7'+c));
        } else if(c > 0) {
            result = String.valueOf(c);
        } else if(isZero) {
            result = "0";
        }
        return result;
    }
}