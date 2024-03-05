import java.io.*;
import java.util.*;

public class Main {

    String S;
    String T;
    LinkedList<Character> sList;
    LinkedList<Character> tList;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        init();
        System.out.println(solve());
    }
    
    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            S = br.readLine();
            T = br.readLine();
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void init() {
        sList = new LinkedList<>();
        tList = new LinkedList<>();
        for(int i=0; i<S.length(); i++) {
            sList.add(S.charAt(i));
        }
        for(int i=0; i<T.length(); i++) {
            tList.add(T.charAt(i));
        }
    }

    public int solve() {
        while(tList.size() > sList.size()) {
            if(tList.getLast() == 'A') {
                tList.removeLast();
            } else {
                //B
                tList.removeLast();
                Collections.reverse(tList);
            }
        }
        if(isSame()) return 1;
        else return 0;
    }

    public boolean isSame() {
        for(int i=0; i<sList.size(); i++) {
            if(sList.get(i) != tList.get(i)) return false;
        }
        return true;
    }
}
