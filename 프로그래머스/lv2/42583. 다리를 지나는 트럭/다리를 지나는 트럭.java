import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> readyQ = new LinkedList<>();     //대기 트럭 큐
        Queue<Integer> crossQ = new LinkedList<>();     //다리를 건너는 트럭 큐
        
        for(int tw : truck_weights) readyQ.add(tw);
        for(int i=0; i<bridge_length; i++) crossQ.add(0);
        
        int cnt = 0;        //경과 시간
        int wsum = 0;       // 다리 위에 건너는 트럭의 총 무게
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