import java.util.*;

class Solution {
    
    static final int N = 'z' - 'a' + 1;
    // static final int N = 16;
    
    long n;
    String[] bans;
    String answer;
    
    public String solution(long n, String[] bans) {
        init(n, bans);
        solve();
        return answer;
    }
    
    public void solve() {
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
    
            
        for(int i=-0; i<bans.length; i++) {
            String tg = getString(n);
            // System.out.println("Tg : " + tg);
            if(tg.length() == bans[i].length()) {
                if(tg.compareTo(bans[i]) >= 0) {
                    n++;
                    continue;
                }
            } else if(bans[i].length() < tg.length()) {
                n++;
                continue;
            }
        }
        answer = getString(n);
    }

    
    public String getString(long n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            long remain = n % N;
            if(remain == 0) {
                n--;
                sb.append('z');
            } else {
                sb.append((char) ('a' + remain - 1));
            }
            n /= N;
        }
        return sb.reverse().toString();
    }
    
    public void init(long n, String[] bans) {
        this.n = n;
        this.bans = bans;
    }
}