import java.util.*;

class Solution {
    
    public static final int INF = 105;
    public static int maxIdx = 0;
    
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        int[] cntArr = new int['Z'-'A'+1];
        Arrays.fill(cntArr, INF);
        
        for(String target : targets) {
            maxIdx = Math.max(maxIdx, target.length());    
        }
        
        for(char a='A'; a<='Z'; a++) {
            for(String key : keymap) {
                for(int i=0; i<key.length(); i++) {
                    if(a==key.charAt(i)) {
                        cntArr[a-'A'] = Math.min(cntArr[a-'A'], i+1);
                        break;
                    }
                }
            }
        }
        
        // for(int i=0; i<cntArr.length; i++) {
        //     System.out.println(i + " : " + cntArr[i]);
        // }
        
        for(int i=0; i<targets.length; i++) {
            String target = targets[i];
            int ans = 0;
            boolean flag = false;
            for(char c : target.toCharArray()) {
                if(cntArr[c-'A']==105) {
                    flag = true;
                    break;
                }
                ans += cntArr[c-'A'];
            }
            if(flag) answer[i] = -1;
            else answer[i] = ans;
        }
        
        return answer;
    }
}