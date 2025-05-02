import java.util.*;

class Solution {
    
    static final int N = 'Z' - 'A' + 1;
    
    long n;
    String[] bans;
    String answer;
    
    public String solution(long n, String[] bans) {
        init(n, bans);
        solve();
        return answer;
    }
    
    public void solve() {
        System.out.println(N);
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        
        for(int i=0; i<bans.length; i++) {
            String tg = getString(n);
            // System.out.println("tg : " + tg);
            if(bans[i].length() < tg.length()) {
                n++;
                continue;
            } else if(bans[i].length() == tg.length()) {
                if(bans[i].compareTo(tg) <= 0) {
                    n++;
                    continue;
                }
            }
            break;
        }
        answer = getString(n);
    }
    
    public String getString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remained = n % 26;
            n /= 26;
            if (remained == 0) {
                n--;
                sb.append('z');
            } else {
                sb.append((char)('a' + remained - 1));
            }
        }
        return sb.reverse().toString();
    }
    
    public void init(long n, String[] bans) {
        this.n = n;
        this.bans = bans;
    }
}