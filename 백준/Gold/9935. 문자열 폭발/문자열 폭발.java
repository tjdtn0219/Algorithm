import java.io.*;
import java.util.*;

public class Main {

    Stack<Character> stk;
    String str;
    String bombStr;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            str = br.readLine();
            bombStr = br.readLine();
            stk = new Stack<>();
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            stk.add(c);
            if(stk.peek() == bombStr.charAt(bombStr.length() - 1)) {
                isBomb();
            }
        }

        printStack();
    }

    public boolean isBomb() {
        String s = "";
        if(stk.size() >= bombStr.length()) {
            for(int i=0; i<bombStr.length(); i++) {
                s = stk.pop() + s;
            }
        }

        if(bombStr.equals(s)) return true;

        for(int i=0; i<s.length(); i++) {
            stk.add(s.charAt(i));
        }
        return false;
    }

    public void printStack() {
        if(stk.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : stk) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }

}