import java.util.*;

class Solution {
    
    int n, tt, m;
    String answer;
    Queue<Integer> q;
    
    public String solution(int n, int t, int m, String[] timetable) {
        init(n, t, m, timetable);
        solve();
        return answer;
    }
    
    public void solve() {
        int busT = toInt("09:00");
        int lastT = -1;
        int takeCnt = 0;
        
        for(int i=0; i<n; i++) {
            takeCnt = 0;
            while(!q.isEmpty()) {
                int curT = q.peek();
                if(curT <= busT && takeCnt < m) {
                    q.poll();
                    takeCnt++;
                } else {
                    break;
                }
                lastT = curT - 1;
            }
            busT += tt;
        }
        
        if(takeCnt < m) {
            lastT = busT - tt;
        }
        answer = toStr(lastT);
    }
    
    public void init(int n, int t, int m, String[] timeTable) {
        this.n = n;
        this.tt = t;
        this.m = m;
        int idx = 0;
        this.q = new LinkedList<>();
        Arrays.sort(timeTable);
        for(String timeStr : timeTable) {
            q.add(toInt(timeStr));
        }
    }
    
    public int toInt(String s) {
        String[] tmp = s.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return 60*h + m;
    }
    
    public String toStr(int t) {
        int h = t / 60;
        int m = t % 60;
        return String.format("%02d", h) + ":" + String.format("%02d", m);
    }
}