import java.util.*;

class Solution {
    
    int n;
    char[] nums;
    int k;
    String ans = "";
    
    public String solution(String number, int k) {
        init(number, k);
        solve();
        return ans;
    }
    
    public void solve() {
        StringBuilder sb = new StringBuilder();
        int len = nums.length - k;
        Stack<Character> stk = new Stack<>();
        
        for(char num : nums) {
            while(!stk.isEmpty() && stk.peek() < num && k > 0) {
                stk.pop();
                k--;
            }
            stk.push(num);
        }
        for(int i=0; i<len; i++) {
            sb.append(stk.get(i));
        }
        ans = sb.toString();
    }
    
    public void init(String number, int k) {
        this.n = number.length();
        nums = number.toCharArray();
        this.k = k;
    }
}

