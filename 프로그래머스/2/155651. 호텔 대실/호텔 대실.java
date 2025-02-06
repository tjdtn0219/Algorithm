import java.util.*;

class Reserve {
    int st;
    int en;
    public Reserve(int st, int en) {
        this.st = st;
        this.en = en;
    }
}

class Solution {
    
    int n;
    Reserve[] reserves;
    int ans;
    
    public int solution(String[][] book_time) {
        init(book_time);
        solve();
        return ans;
    }
    
    public void solve() {
        int[] arr = new int[timeToNum("25:00")];
        int[] sum = new int[timeToNum("25:00")];
        for(Reserve reserve : reserves) {
            // System.out.println(reserve.st + " : " + reserve.en);
            arr[reserve.st] += 1;
            arr[reserve.en + 10] -= 1;
        }
        
        sum[0] = arr[0];
        ans = sum[0];
        for(int i=1; i<=timeToNum("24:00"); i++) {
            sum[i] = sum[i-1] + arr[i];
            ans = Math.max(ans, sum[i]);
        }
    }
    
    public void init(String[][] book_time) {
        this.n = book_time.length;
        reserves = new Reserve[n];
        for(int i=0; i<n; i++) {
            reserves[i] = new Reserve(timeToNum(book_time[i][0]), timeToNum(book_time[i][1]));
        }
        
    }
    
    public int timeToNum(String time) {
        String[] tmp = time.split(":");
        int hour = Integer.parseInt(tmp[0]);
        int min = Integer.parseInt(tmp[1]);
        return 60*hour + min;
    }
}