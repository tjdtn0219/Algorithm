import java.util.*;

class Solution {
    long[] numbers;
    
    public int[] solution(long[] numbers) {
        init(numbers);
        return solve();

    }
    
    int sum(StringBuilder node){
        int len = node.length();
        if(len == 1){
            return node.charAt(0) - '0';
        }
        StringBuilder sbLeft = new StringBuilder(node.substring(0, len/2));
        StringBuilder sbRight = new StringBuilder(node.substring(len/2+1));
        
        int left = sum(sbLeft);
        if(left == -1){
            return -1;
        }
        int right = sum(sbRight);
        if(right == -1){
            return -1;
        }
        int mid = node.charAt(len/2) - '0';
        if(left + right > 0 && mid == 0){
            return -1;
        } else if(left + right + mid == 0){
            return 0;
        } else {
            return 1;
        }
    }
    
    public int[] solve() {
        int[] answer = new int[numbers.length];
        int i = 0;
        for(long num : numbers) {
            String bTree = toBinary(num);
            // System.out.println("bTree : " + bTree);
            StringBuilder completeTree = toCompleteBTree(bTree);
            // System.out.println("cpt : " + completeTree.toString());
            // System.out.println("isTree : " + isTree(completeTree));
            int isTree = sum(completeTree);
            if(isTree < 0) answer[i++] = 0;
            else answer[i++] = 1;
        }
        return answer;
    }
    
    public void init(long[] numbers) {
        this.numbers = numbers;
    }
    
    public int isTree(StringBuilder sb) {
        int len = sb.length();
        if(len == 1) {
            if(sb.charAt(0) == '1') return 1;
            else return -1;
        }
        int mid = len / 2;
        StringBuilder sbLeft = new StringBuilder(sb.substring(0, mid));
        StringBuilder sbRight = new StringBuilder(sb.substring(mid+1));
        if(len == 3) {
            // System.out.println("l : " + sbLeft + ", right : " + sbRight + ", sb : " + sb);
            if(sb.charAt(mid) == '0' && (sbLeft.charAt(0) == '1' || sbRight.charAt(0) == '1')) {
                return -1;
            } else {
                return 1;
            }
        }
        int left = isTree(sbLeft);
        int right = isTree(sbRight);
        if(left < 0 || right < 0) return -1;
        return 1;
    }
    
    public StringBuilder toCompleteBTree(String tree) {
        StringBuilder sb = new StringBuilder(tree);
        double len = (double) tree.length();
        double n = 1;
        while(true) {
            double completeLen = Math.pow(2, n) - 1;
            if(len == completeLen) {
                return sb;
            }
            double lastCompleteLen = Math.pow(2, n-1);
            if(lastCompleteLen <= len && len < completeLen) {
                for(int i=0; i<completeLen - len; i++) {
                    sb.insert(0, "0");
                }
                return sb;
            }
            n++;
        }
    }
    
    public String toBinary(long num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            if(num % 2 == 1) {
                sb.insert(0, "1");
            } else {
                sb.insert(0, "0");
            }
            num /= 2;
        }
        return sb.toString();
    }
}