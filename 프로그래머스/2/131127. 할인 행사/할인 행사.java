import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        HashMap<String, Integer> wantMap = new HashMap<>();
        for(int i=0; i<want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        // for(String s : wantMap.keySet()) {
        //     System.out.println(s + " " + wantMap.get(s));
        // }
        
        for(int i=0; i+9<discount.length; i++) {
            HashMap<String, Integer> dcMap = new HashMap<>();
            for(int j=i; j<i+10; j++) {
                int cnt = dcMap.getOrDefault(discount[j], 0);
                dcMap.put(discount[j], cnt+1);
                // System.out.println(j + " " + discount[j] + " " + (cnt+1));
            }
            
            // System.out.println("===========");
            
            // for(String s : dcMap.keySet()) {
            //     System.out.println(s + " " + dcMap.get(s));
            // }
            
            boolean flag = false;
            for(String item : wantMap.keySet()) {
                if(wantMap.get(item) > dcMap.getOrDefault(item, 0)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) answer++;
        }
        
        return answer;
    }
}