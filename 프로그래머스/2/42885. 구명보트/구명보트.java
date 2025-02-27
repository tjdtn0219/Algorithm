import java.util.*;

class Solution {
    
    int n;
    int[] people;
    int limit;
    int ans;
    
    public int solution(int[] people, int limit) {
        init(people, limit);
        solve();
        return ans;
    }
    
    public void solve() {
        int sum = 0;
        int left = 0;
        int right = n-1;
        
        while(left <= right) {
            sum = people[left] + people[right];
            if(sum > limit) {
                ans++;
                right--;
            } else {
                ans++;
                left++;
                right--;
            }
        }
    }
    
    //10 20 30 70 95 /// LMT = 100

    public void init(int[] people, int limit) {
        this.n = people.length;
        this.people = people;
        this.limit = limit;
        Arrays.sort(people);
    }
}