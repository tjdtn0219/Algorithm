import java.util.*;

class Task {
    String name;
    int startTime;
    int playTime;
    
    public Task(String name, int startTime, int playTime) {
        this.name = name;
        this.startTime = startTime;
        this.playTime = playTime;
    }
}

class Solution {
    
    List<Task> taskList;
    Stack<Task> stk;
    
    public String[] solution(String[][] plans) {
        
        init(plans);
        return solve();
    }
    
    public String[] solve() {
        List<String> ansList = new ArrayList<>();
        Collections.sort(taskList, (t1, t2) -> t1.startTime - t2.startTime);
        Task cur = taskList.get(0);
        
        for(int i=1; i<taskList.size(); i++) {
            Task nxt = taskList.get(i);
            // System.out.println("cur : " + cur.name + ", nxt : " + nxt.name);
            if(cur.startTime + cur.playTime < nxt.startTime) {
                // System.out.println("TAG1");
                ansList.add(cur.name);
                int restTime = nxt.startTime - (cur.startTime + cur.playTime);
                
                while(!stk.isEmpty()) {
                    Task task = stk.pop();
                    if(task.playTime <= restTime) {
                        restTime -= task.playTime;
                        ansList.add(task.name);
                    } else {
                        task.playTime -= restTime;
                        stk.push(task);
                        break;
                    }
                }
            } else if(cur.startTime + cur.playTime > nxt.startTime) {
                // System.out.println("TAG2");
                cur.playTime = cur.startTime + cur.playTime - nxt.startTime;
                stk.push(cur);
            } else {
                // System.out.println("TAG3");
                ansList.add(cur.name);
            }
            cur = nxt;
        }
        
        ansList.add(cur.name);

        while(!stk.isEmpty()) {
            ansList.add(stk.pop().name);
        }

        return ansList.toArray(new String[0]);
    }
    
    public void init(String[][] plans) {
        taskList = new ArrayList<>();
        stk = new Stack<>();
        for(String[] plan : plans) {
            taskList.add(new Task(plan[0], timeToAmount(plan[1]), Integer.parseInt(plan[2])));
        }
    }
    
    public int timeToAmount(String time) {
        String[] tmp = time.split(":");
        int h = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        return 60*h + m;
    }
}