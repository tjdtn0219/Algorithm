class Solution {
    public int solution(int storey) {
        int ans = 0;
        
        while(storey > 0) {
            int mod = storey % 10;
            if(mod==5) {
                if((storey/10)%10 >= 5) {
                    ans += 10-mod;
                    storey /= 10;
                    storey++;
                    // System.out.println("TAG");
                } else {
                    ans += mod;
                    storey /= 10;
                }
            } else if(mod<5) {  //버림
                ans += mod;
                storey /= 10;
            } else {    //mod=6-9   //올림
                ans += 10-mod;
                storey /= 10;
                storey++;
            }
        }
        
        return ans;
    }
}