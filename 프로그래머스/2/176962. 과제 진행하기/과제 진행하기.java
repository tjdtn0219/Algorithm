import java.util.*;

class Task {
    String name;
    int start;
    int rest;
    public Task(String name, int start, int rest) {
        this.name = name;
        this.start = start;
        this.rest = rest;
    }
}

class Solution {
    
    List<String> ansList;
    Queue<Task> q;
    Stack<Task> stk;
    
    public String[] solution(String[][] plans) {
        init(plans);
        solve();
        return ansList.toArray(new String[0]);
    }
    
    public void solve() {
        Task cur = q.poll();
        
        while(!q.isEmpty()) {
            Task nxt = q.poll();
            int finishTime = cur.start + cur.rest;
            if(finishTime <= nxt.start) {
                // 현재 과제 끝났는 데 다음 과제까지 시간이 남을 때
                int idle = nxt.start - finishTime;
                ansList.add(cur.name);
                while(!stk.isEmpty()) {
                    Task stopped = stk.pop();
                    if(stopped.rest <= idle) {
                        ansList.add(stopped.name);
                        idle -= stopped.rest;
                    } else {
                        stopped.rest -= idle;
                        stk.add(stopped);
                        break;
                    }
                }
            } else {
                // 현재 과제 끝나기 전에 다음 과제 차례
                cur.rest = finishTime - nxt.start;
                stk.add(cur);
            }
            cur = nxt;
            if(q.isEmpty()) {
                stk.add(cur);
            }
        }
        
        while(!stk.isEmpty()) {
            ansList.add(stk.pop().name);
        }
    }
    
    public void init(String[][] plans) {
        this.q = new LinkedList<>();
        this.stk = new Stack<>();
        this.ansList = new ArrayList<>();
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));
        for(String[] plan : plans) {
            String name = plan[0];
            int start = toInt(plan[1]);
            int rest = Integer.parseInt(plan[2]);
            q.add(new Task(name, start, rest));
        }
    }
    
    public int toInt(String time) {
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return 60*h + m;
    }
}