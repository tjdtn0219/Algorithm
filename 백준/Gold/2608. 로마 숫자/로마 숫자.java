import java.io.*;
import java.util.*;

public class Main {

    String roma1, roma2;
    HashMap<String, Integer> charMap;
    HashMap<Integer, String> intMap;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        init();
        solve();
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            roma1 = br.readLine();
            roma2 = br.readLine();
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void init() {
        charMap = new HashMap<>();
        charMap.put("I", 1);
        charMap.put("V", 5);
        charMap.put("X", 10);
        charMap.put("L", 50);
        charMap.put("C", 100);
        charMap.put("D", 500);
        charMap.put("M", 1000);
        charMap.put("IV", 4);
        charMap.put("IX", 9);
        charMap.put("XL", 40);
        charMap.put("XC", 90);
        charMap.put("CD", 400);
        charMap.put("CM", 900);

        intMap = new HashMap<>();
        intMap.put(1, "I");
        intMap.put(5, "V");
        intMap.put(10, "X");
        intMap.put(50, "L");
        intMap.put(100, "C");
        intMap.put(500, "D");
        intMap.put(1000, "M");
        intMap.put(4, "IV");
        intMap.put(9, "IX");
        intMap.put(40, "XL");
        intMap.put(90, "XC");
        intMap.put(400, "CD");
        intMap.put(900, "CM");
    }

    public void solve() {
//        System.out.println(romaToInt(roma1));
//        System.out.println(romaToInt(roma2));
        int sum = romaToInt(roma1) + romaToInt(roma2);
        System.out.println(sum);
        System.out.println(intToRoma(sum));
//        System.out.println(intToRoma(235));
//        System.out.println(intToRoma(2493));
    }

    public int romaToInt(String roma) {
        int val = 0;
        int i;
        for(i=0; i<roma.length()-1; i++) {
            char curChar = roma.charAt(i);
            char nxtChar = roma.charAt(i+1);
            if(charMap.get(curChar + "") >= charMap.get(nxtChar + "")) {
                val += charMap.get(curChar + "");
//                System.out.println("TAG1 : " + curChar);
            } else {
                String tmp = curChar + "" + nxtChar;
//                System.out.println("TAG2 : " + tmp);
                val += charMap.get(tmp);
                i++;
            }
        }
        if(i == roma.length()-1) val += charMap.get(roma.charAt(i) + "");
        return val;
    }

    public String intToRoma(int val) {
        int digit = 1;
        String roma = "";
        while(val > 0) {
            int mod = val % 10;
            if(1<=mod && mod<=3) {
                for(int i=0; i<mod; i++) {
                    roma = intMap.get(digit) + roma;
                }
            } else if(mod==4) {
                roma = intMap.get(4*digit) + roma;
            } else if(5<=mod && mod<=8) {
                for(int i=0; i<mod-5; i++) {
                    roma = intMap.get(digit) + roma;
                }
                roma = intMap.get(5*digit) + roma;
            } else if(mod==9) {
                roma = intMap.get(9*digit) + roma;
            }
            val /= 10;
            digit *= 10;
        }
        return roma;
    }

}