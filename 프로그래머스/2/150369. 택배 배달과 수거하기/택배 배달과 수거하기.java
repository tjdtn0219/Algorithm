import java.util.*;

class Node implements Comparable<Node> {
    int idx;
    int val;
    
    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
    
    @Override
    public int compareTo(Node o) {
        return o.idx - this.idx;
    }
}

class Solution {
    
    int cap;
    int n;
    PriorityQueue<Node> deliveryPq;
    PriorityQueue<Node> pickupPq;
    long answer;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        init(cap, n, deliveries, pickups);
        solve();
        return answer;
    }
    
    public void solve() {
        while(!deliveryPq.isEmpty() || !pickupPq.isEmpty()) {
            int dis = 0;
            if(!deliveryPq.isEmpty()) {
                dis = Math.max(dis, deliveryPq.peek().idx);
            }
            if(!pickupPq.isEmpty()) {
                dis = Math.max(dis, pickupPq.peek().idx);
            }
            // System.out.println("dis : " + dis);
            // System.out.println("deliveryPq.len : " + deliveryPq.size() + ", pickupPq.len : " + pickupPq.size());
            answer += dis * 2;
            
            int tmp = cap;
            // cap만큼 상자를 싣고 배달지까지 가는 길에 상자 배송
            while(!deliveryPq.isEmpty()) {
                int idx = deliveryPq.peek().idx;
                tmp -= deliveryPq.poll().val;
                if(tmp < 0) {
                    deliveryPq.add(new Node(idx, -tmp));
                    break;
                }
            }
            
            tmp = cap;
            // dis까지 간 뒤 돌아오는 길에 cap만큼 상자를 수거
            while(!pickupPq.isEmpty()) {
                int idx = pickupPq.peek().idx;
                tmp -= pickupPq.poll().val;
                if(tmp < 0) {
                    pickupPq.add(new Node(idx, -tmp));
                    break;
                }
            }
            
            // printPq(deliveryPq, "delivery");
            // printPq(pickupPq, "pickup");
        }
    }
    
    private void printPq(PriorityQueue<Node> pq, String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("======").append(s).append("======\n");
        for(Node node : pq) {
            sb.append(node.idx + ", " + node.val).append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.n = n;
        deliveryPq = new PriorityQueue<>();
        pickupPq = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            if(deliveries[i] > 0) {
                deliveryPq.add(new Node(i+1, deliveries[i]));
            }
            if(pickups[i] > 0) {
                pickupPq.add(new Node(i+1, pickups[i]));
            }
        }
    }
}