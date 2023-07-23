import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> readyQ = new LinkedList<>();
        Queue<Integer> crossQ = new LinkedList<>();
        
        for(int tw : truck_weights) readyQ.add(tw);
        for(int i=0; i<bridge_length; i++) crossQ.add(0);
        
        int cnt = 0;
        int wsum = 0;
        while(!readyQ.isEmpty() || !crossQ.isEmpty()) {
            if(readyQ.isEmpty()) {
                crossQ.poll();
                cnt++; continue;
            }
            
            wsum -= crossQ.poll();
            if(wsum + readyQ.peek() > weight) {
                crossQ.add(0);
            } else {
                wsum += readyQ.peek();
                crossQ.add(readyQ.poll());
            }
            cnt++;
            
        }        
        
        return cnt;
    }
}