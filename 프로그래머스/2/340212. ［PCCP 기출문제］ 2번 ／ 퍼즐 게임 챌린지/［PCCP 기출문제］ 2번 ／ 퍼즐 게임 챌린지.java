class Solution {
    
    int maxLevel;
    int[] diffs;
    int[] times;
    long limit;
    int n;
    int ans;
    
    public int solution(int[] diffs, int[] times, long limit) {
        init(diffs, times, limit);
        solve();
        return ans;
    }
    
    public void solve() {
        ans = bSearch();
    }
    
    public int bSearch() {
        int st = 1;
        int en = maxLevel;
        
        int res = maxLevel;
        while(st <= en) {
            int mid = (st + en) / 2;
            if(isSolve(mid)) {
                en = mid - 1;
                res = Math.min(res, mid);
            } else {
                st = mid + 1;
            }
        }
        return res;
    }
    
    public boolean isSolve(int level) {
        long time = 0;
        for(int i=0; i<n; i++) {
            if(diffs[i] <= level) {
                time += times[i];
            } else {
                int repeat = diffs[i] - level;
                time += repeat * (times[i-1] + times[i]);
                time += times[i];
            }
        }
        if(time <= limit) return true;
        return false;
    }
    
    public void init(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        n = diffs.length;
        maxLevel = 0;
        for(int diff : diffs) {
            maxLevel = Math.max(maxLevel, diff);
        }
    }
}