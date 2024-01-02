class Solution {
    
    boolean[] op;   //true + , false -
    int n;
    int tg;
    int ans = 0;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        tg = target;
        
        n = numbers.length;
        op = new boolean[n];
        
        dfs(0, numbers);
        answer = ans;
        
        return answer;
    }
    
    public void dfs(int k, int[] numbers) {
        if(k==n) {
            func(numbers);
            return ;
        }
        
        op[k] = true;
        dfs(k+1, numbers);
        
        op[k] = false;
        dfs(k+1, numbers);
    } 
    
    public void func(int[] numbers) {
        int result = 0;
        for(int i=0; i<n; i++) {
            if(op[i]) result += numbers[i];
            else result -= numbers[i];
        }
        if(result == tg) ans++;
    }
}