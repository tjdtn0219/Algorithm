class Solution {
    
    int k, d;
    
    public long solution(int k, int d) {
        init(k, d);
        return solve();
        
    }
    
    public void init(int k, int d) {
        this.k = k;
        this.d = d;
    }
    
    public long solve() {
        long cnt = 0L;
        long squareD = (long) d*d;
        for(int x=0; x<=d; x+=k) {
            long squareY = squareD - (long)x*x;
            int maxY = (int) Math.sqrt(squareY);
            // int maxY = (bSearch(squareY);
            cnt += (long) (maxY/k + 1);
            // System.out.print("Y^2 : " + squareY + ", ");
            // System.out.println(maxY);
        }
        // System.out.println("cnt : " + cnt);
        return cnt;
    }
    
    public int bSearch(long tg) {
        int st = 0;
        int en = d+1;
        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < (long)mid*mid) en = mid;
            else st = mid+1;
        }
        return st-1;
    }
}