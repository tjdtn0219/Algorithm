import java.util.*;

class Solution {
    
    int[] order;
    Queue<Integer> q;
    Stack<Integer> stk;
    int ans;
    int n;
    
    public int solution(int[] order) {
        init(order);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int i=1; i<=n; i++) {
            q.add(i);
        }
        
        // q: 1 2 3 4 5
        // tg: 4 3 1 2 5
        // stk: 1 2 3 
        int idx = 0;
        while(!q.isEmpty()) {

            int nxt = order[idx];
            // System.out.println("nxt : " + nxt + ", q.peek : " + q.peek() + ", ans : " + ans);
            // printStk();
            if(nxt == q.peek()) {
                // System.out.println("TAG1");
                q.poll();
                idx++;
                ans++;
            } else if (nxt < q.peek()) {
                // System.out.println("TAG2");
                boolean flag = false;
                if(!stk.isEmpty() && nxt == stk.peek()) {
                    stk.pop();
                    idx++;
                    ans++;
                    flag = true;
                }
                if(!flag) return ;
            } else {
                // System.out.println("TAG3");
                stk.add(q.poll());
            }
        }
        while(!stk.isEmpty() && stk.pop() == order[idx++]) {
            ans++;
        }
    }
    
    private void printStk() {
        StringBuilder sb = new StringBuilder();
        sb.append("stk : ");
        for(int num : stk) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] order) {
        this.n = order.length;
        this.order = order;
        q = new LinkedList<>();
        stk = new Stack<>();
    }
}