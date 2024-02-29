class Solution {
    
    int cap;
    int n;
    int[] deliveries;
    int[] pickups;
    
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        init(cap, n, deliveries, pickups);
        return solve();
        // return answer;
    }
    
    public void init(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.n = n;
        this.deliveries = deliveries.clone();
        this.pickups = pickups;
    }
    
    public long solve() {
        int curDel = getFirstHouse(deliveries);
        int curPickUp = getFirstHouse(pickups);

        long ans = 0;
    
        while(true) {
            ans += Math.max(curDel+1, curPickUp+1) * 2;
            curDel = getNextDestination(curDel, deliveries);
            curPickUp = getNextDestination(curPickUp, pickups);
            // System.out.println("curDel : " + curDel + " , curPickUp : " + curPickUp);
            // printArr(deliveries);
            // printArr(pickups);
            if(curDel == -1 && curPickUp == -1) break;
        }
        return ans;
        
    }
    
    public int getFirstHouse(int[] arr) {
        int maxIdx = -1;
        for(int i=0; i<n; i++) {
            if(arr[i] > 0) maxIdx = i;
        }
        return maxIdx;
    }
    
    public void printArr(int[] arr) {
        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public int getNextDestination(int curDest, int[] arr) {
        int cnt = 0;
        int nextDest = -1;
        for(int i=curDest; i>=0; i--) {
            if(cnt + arr[i] <= cap) {
                cnt += arr[i];
                arr[i] = 0;
            } else {
                int rest = cap - cnt;
                arr[i] -= rest;
                nextDest = i;
                break;
            }
        }
        return nextDest;
    }
}