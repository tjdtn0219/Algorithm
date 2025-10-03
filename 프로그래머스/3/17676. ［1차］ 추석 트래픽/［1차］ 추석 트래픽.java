import java.util.*;

class Time {
    int h;
    int m;
    int sec;
    int ing;
    public Time(int h, int m, int sec, int ing) {
        this.h = h;
        this.m = m;
        this.sec = sec;
        this.ing = ing;
    }
}

class Node {
    int st;
    int en;
    public Node(int st, int en) {
        this.st = st;
        this.en = en;
    }
}

class Solution {
    
    List<Time> timeList;
    int answer;
    
    public int solution(String[] lines) {
        
        init(lines);
        solve();
        return answer;
    }
    
    public void solve() {
        int max = toInt(24, 0, 0);
        
        List<Node> nodeList = new ArrayList<>();
        for(Time time : timeList) {
            int st = toInt(time.h, time.m, time.sec) - (time.ing - 1);
            int en = toInt(time.h, time.m, time.sec);
            // System.out.println(st + " ~ " + en);
            nodeList.add(new Node(st, en));
        }
        
        System.out.println("-------------");
        
        answer = -1;
        for(int i=0; i<nodeList.size(); i++) {
            int cnt = 1;
            int en = nodeList.get(i).en;
            // System.out.println("i en : " + en);
            for(int j=0; j<nodeList.size(); j++) {
                if(i == j) continue;
                // System.out.println("j st : " + nodeList.get(j).st + ", en : " + nodeList.get(j).en);
                if(nodeList.get(j).st < en + 1000 && nodeList.get(j).en >= en) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
    }
    
    public void init(String[] lines) {
        timeList = new ArrayList<>();
        for(String line : lines) {
            String tmp = line.split(" ")[1];
            int h = Integer.parseInt(tmp.substring(0, 2));
            int m = Integer.parseInt(tmp.substring(3, 5));
            int sec = Integer.parseInt(tmp.substring(6, 8) + tmp.substring(9, 12));
            tmp = line.split(" ")[2];
            // System.out.println("ingStr : " + tmp);
            int ingS = Integer.parseInt(tmp.substring(0, 1));
            int ingM = 0;
            if(tmp.length() >= 4) {
                ingM = Integer.parseInt(tmp.substring(2, tmp.length()-1));
            }
            int ing = 1000*ingS + ingM;
            timeList.add(new Time(h, m, sec, ing));
        }
    }
    
    public int toInt(int h, int m, int sec) {
        return 60_000 * 60 * h + 60_000 * m + sec;
    }
}