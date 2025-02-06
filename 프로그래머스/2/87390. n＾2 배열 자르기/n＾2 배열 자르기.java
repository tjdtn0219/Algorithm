import java.util.*;

class Solution {
    
    int n;
    long left, right;
    
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        init(n, left, right);
        return solve();
    }
    
    public int[] solve() {
        LinkedList<Integer> ansList = new LinkedList<>();
        int leftRow = (int)(left / n);
        int leftCol = (int)(left % n);
        
        int rightRow = (int)(right / n);
        int rightCol = (int)(right % n);
        
        for(int i=leftRow; i<=rightRow; i++) {
            int num = i+1;
            for(int j=0; j<n; j++) {
                if(j<=i) {
                    ansList.add(num);
                } else {
                    ansList.add(j+1);
                }
            }
        }
        // printAns(ansList);
        
        //remove
        for(int i=0; i<leftCol; i++) {
            ansList.pollFirst();
        }
        // printAns(ansList);
        
        for(int i=0; i<n-rightCol-1; i++) {
            ansList.pollLast();
        }
        // printAns(ansList);
        
        return ansList.stream().mapToInt(Integer::intValue).toArray();
        
    }
    
    public void printAns(List<Integer> ansList) {
        StringBuilder sb = new StringBuilder(); 
        for(int num : ansList) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int n, long left, long right) {
        this.n = n;
        this.left = left;
        this.right = right;
    }
}