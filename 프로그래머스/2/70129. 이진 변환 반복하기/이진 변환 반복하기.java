import java.util.*;

class Node {
    String s;
    int cnt;
    public Node(String s, int cnt) {
        this.s = s;
        this.cnt = cnt;
    }
}

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int cnt = 0;
        int idx = 0;
        while(true) {
            Node node = removeZero(s);
            cnt += node.cnt;
            int len = node.s.length();
            s = Integer.toString(len, 2);
            idx++;
            if(s.equals("1")) {
                break;
            }
        }
        
        answer[0] = idx;
        answer[1] = cnt;
        
        return answer;
    }
    
    public Node removeZero(String s){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(char c : s.toCharArray()) {
            if(c == '1') {
                sb.append(c);
            } else {
                cnt++;
            }
        }
        return new Node(sb.toString(), cnt);
    }
    
}