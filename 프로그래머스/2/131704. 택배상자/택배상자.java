import java.util.*;

class Solution {
    
    int answer;
    Queue<Integer> q;
    Stack<Integer> stk;
    int n;
    int[] order;
    
    public int solution(int[] order) {
        init(order);
        solve();
        return answer;
    }
    
    // 1 2 3 4 5
    // 4 3 1 2 5
    
    public void solve() {
        int idx = 0;
        while(!q.isEmpty()) {
            // System.out.println("다음 : " + q.peek() + ", idx : " + idx);
            if(order[idx] == q.peek()) {
                // 컨테이너에서 꺼낸 상자가 다음 순서인 경우
                idx++;
                q.poll();
            } else {
                // 컨테이너에서 꺼낸 상자가 다음 순서가 아닌 경우
                
                // 보조 벨트에서 바로 꺼낼 수 있는 경우
                while(!stk.isEmpty()) {
                    if(stk.peek() == order[idx]) {
                        stk.pop();
                        idx++;
                    } else {
                        break;
                    }
                }
                
                // 보조 벨트에서 더 이상 꺼낼 수 없는 경우 -> 보조 벨트에 쌓음
                if(q.peek() != order[idx]) {
                    stk.add(q.poll());
                }
            }
        }
        
        while(!stk.isEmpty()) {
            if(order[idx] == stk.pop()) {
                idx++;
            } else {
                break;
            }
        }
        answer = idx;
    }
    
    public void init(int[] order) {
        this.order = order;
        this.n = order.length;
        this.q = new LinkedList<>();
        this.stk = new Stack<>();
        for(int i=1; i<=n; i++) {
            q.add(i);
        }
    }
}