import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        Stack<Subject> stk = new Stack<>();
        Arrays.sort(plans, (o1,o2)->o1[1].compareTo(o2[1]));
        
        // List<Subject> list = new ArrayList<>();
        // for(String[] plan : plans) {
        //     System.out.println(convertToInt(plan[1]));
        //     list.add(new Subject(plan[0]), convertToInt(plan[1]));
        // }
        
        String curName = plans[0][0];
        int curT = convertToInt(plans[0][1]);
        int playT = Integer.parseInt(plans[0][2]);
        int idx = 0;
        
        for(int i=1; i<plans.length; i++) {
            int nextT = convertToInt(plans[i][1]);
            if(curT + playT <= nextT) {  //기존 과제 진행 후 새로운 과제 시작
                curT +=playT;
                answer[idx++] = curName;
                
                while(!stk.isEmpty()) {
                    
                    Subject popped = stk.pop();
                    if(curT + popped.restTime <= nextT) {
                        answer[idx++] = popped.name;
                        curT += popped.restTime;
                    } else {
                        stk.add(
                            new Subject(popped.name, curT+popped.restTime-nextT)
                        );
                        break;
                    }
            
                }
                curT = nextT;
                playT = Integer.parseInt(plans[i][2]);
                curName = plans[i][0];
                
            } else { //기존 과제 진행 도중 새로운 과제 시작
                stk.add(
                    // new Subject(plans[i-1][0], convertToInt(plans[i-1][1])+Integer.parseInt(plans[i-1][2]) - nextT)
                    new Subject(curName, curT + playT - nextT)
                );
                curName = plans[i][0];
                playT = Integer.parseInt(plans[i][2]);
                curT = nextT;
            }
        }
        
        answer[idx++] = curName;
        // System.out.println(curName);
        while(!stk.isEmpty()) {
            // System.out.println(stk.pop().name);
            answer[idx++] = stk.pop().name;
        }
        
        return answer;
    }
    
    public int convertToInt(String time) {
        String[] strings = time.split(":");
        int h = Integer.parseInt(strings[0]) * 60;
        int m = Integer.parseInt(strings[1]);
        return h+m;
    }
}

class Subject {
    String name;
    int restTime;
    
    public Subject(String name, int restTime) {
        this.name = name;
        this.restTime = restTime;
    }
}
