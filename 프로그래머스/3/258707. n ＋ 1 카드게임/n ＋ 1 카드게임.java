import java.util.*;

class Solution {
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
    
        // 1. 코인을 안쓰고 n+1을 만족하는 경우
        // 2. 코인 하나를 쓰고 n+1을 만족하는 경우
        // 3. 코인 두 개를 써야 n+1을 만족하는 경우
        // 4. n+1을 만족하지 않는 경우
        
        int n = cards.length;
        int idx = n/3;
        
        // 숫자 중복 X, 포함관계만 확인하면 됨.
        Set<Integer> myCard = new HashSet<>();
        Set<Integer> newCard = new HashSet<>();
        
        for(int i=0; i<idx; i++) {
            myCard.add(cards[i]);
        }
        
        int target = n+1;
        while(true) {
            
            answer++;
            
            if(idx >= n) {
                break;
            }
            
            newCard.add(cards[idx]);
            newCard.add(cards[idx+1]);
            idx += 2;

            boolean flag = false;
            for(int num : myCard) {
                if(myCard.contains(target - num)) {
                    myCard.remove(num);
                    myCard.remove(target - num);
                    flag = true;
                    break;
                }
            }
            
            if(!flag) {
                if(coin >= 1) {
                    for(int num : myCard) {
                        if(newCard.contains(target - num)) {
                            myCard.remove(num);
                            newCard.remove(target - num);
                            coin--;
                            flag = true;
                            break;
                        }
                    } 
                }
            }
            
            if(!flag) {
                if(coin >= 2) {
                    for(int num : newCard) {
                        if(newCard.contains(target - num)) {
                            newCard.remove(num);
                            newCard.remove(target - num);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    } 
                }
            }
            
            if(!flag) {
                break;
            }
        }
        return answer;
    }
}