class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int left = 0, right = 0;
        int len = sequence.length;
        int min_len = sequence.length + 1;
        int sum = 0;
        while(right<len && left<=right) {
            
            if(left==right) {
                sum = sequence[left];
            }
            if(sum==k) {
                if(min_len > right-left+1) {
                    min_len = right-left+1;
                    answer[0] = left;
                    answer[1] = right;
                }
                sum -= sequence[left++];
            } else if(sum<k) {
                right++;
                if(right<len) sum+=sequence[right];
            } else {
                sum -= sequence[left++];

            }
        }
        
        return answer;
    }
}