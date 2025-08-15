import java.util.*;

class Solution {
    
    int answer;
    int[] people;
    int limit;
    int n;
    
    public int solution(int[] people, int limit) {
        init(people, limit);
        solve();
        return answer;
    }
    
    public void solve() {
        // 50 50 70 80
        int left = 0;
        int right = n - 1;
        while(left <= right) {
            int sum = people[left] + people[right];
            if(sum > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            answer++;
        }
    }
    // 5 5 7 8
    // 100_000_000
    
    public void init(int[] people, int limit) {
        this.people = people;
        this.limit = limit;
        this.n = people.length;
        Arrays.sort(people);
    }
}