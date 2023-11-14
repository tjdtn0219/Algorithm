class Solution {
    public long solution(int k, int d) {
        long ans = 0;
        
        long d2 = (long) d*d;
        
        int x;
        int y = 0;
        
        for(x=0; x<=d; x+=k) {
            long tg = d2 - (long) x*x;
            int num = bSearch(tg);
            ans += num/k + 1;
            // System.out.println(bSearch(tg));
        }
        
        return ans;
    }
    
    public int bSearch(long tg) {
        // System.out.println("tg : " + tg);
        long st = 0;
        long en = 1000000L * 1000000L;
        // System.out.println("en : " + en);
        while(st < en) {
            long mid = (st + en) / 2L;
            if(tg <= mid) en = mid;
            else st = mid + 1;
        }
        // System.out.println("st : " + st);
        
        int x = (int)(Math.sqrt(st));
        return x;
    }
}