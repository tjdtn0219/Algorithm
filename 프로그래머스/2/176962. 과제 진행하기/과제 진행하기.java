import java.util.*;

class Solution {
    
    Queue<Plan> q;
    Stack<Plan> stk;
    List<String> answer;
    
    public String[] solution(String[][] plans) {
        
        init(plans);
        solve();
        
        return answer.toArray(new String[0]);
    }
    
    public void init(String[][] plans) {
        q = new LinkedList<>();
        stk = new Stack<>();
        answer = new ArrayList<>();
        Arrays.sort(plans, (p1, p2) -> convertToInt(p1[1]) - convertToInt(p2[1]));
        for(String[] plan : plans) {
            q.add(new Plan(plan[0], convertToInt(plan[1]), Integer.parseInt(plan[2])));
        }
        
    }
    
    public int convertToInt(String time) {
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);
        return 60*h + m;
    }
    
    public void solve() {
        Plan cur = q.poll();
        while(!q.isEmpty()) {
            Plan nxt = q.poll();
            if(cur.startTime + cur.restTime < nxt.startTime) {
                answer.add(cur.name);
                int idleTime = nxt.startTime - (cur.startTime + cur.restTime);
                doStoppedPlans(idleTime);
            } else if(cur.startTime + cur.restTime > nxt.startTime) {
                int restTime = (cur.startTime + cur.restTime) - nxt.startTime;
                cur.restTime  = restTime;
                stk.add(cur);
            } else {
                // ==
                answer.add(cur.name);
            }
            cur = nxt;
        }
        answer.add(cur.name);
        doRestStopped();
    }
    
    public void doRestStopped() {
        while(!stk.isEmpty()) {
            answer.add(stk.pop().name);
        }
    }
    
    public void doStoppedPlans(int time) {
        while(!stk.isEmpty()) {
            Plan stopped = stk.pop();
            if(stopped.restTime <= time) {
                answer.add(stopped.name);
                time -= stopped.restTime;
            } else {
                stopped.restTime -= time;
                stk.add(stopped);
                break;
            }
            
        }
    }
}

class Plan {
    String name;
    int startTime;
    int restTime;
    public Plan(String name, int startTime, int restTime) {
        this.name = name;
        this.startTime = startTime;
        this.restTime = restTime;
    }
}