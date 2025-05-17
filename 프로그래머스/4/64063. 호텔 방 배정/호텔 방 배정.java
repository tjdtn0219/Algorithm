import java.util.*;

class Solution {
    
    long k;
    long[] requires;
    long[] answer;
    HashMap<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        init(k, room_number);
        solve();
        return answer;
    }
    
    public void solve() {
        int idx = 0;
        for(long r : requires) {
            answer[idx++] = find(r);
        }
    }
    
    
    public long find(long x) {
        if(!map.keySet().contains(x)) {
            map.put(x, x+1);
            return x;
        }
        long nxt = find(map.get(x));
        map.put(x, nxt);
        return nxt;
        
    }
    
    public void init(long k, long[] room_number) {
        this.k = k;
        this.requires = room_number;
        this.answer = new long[room_number.length];
        this.map = new HashMap<>(); 
    }
}