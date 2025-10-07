import java.util.*;

class Solution {
    
    // 1. 처음 시작하는 카드 뭉치에서 n+1 만드는 경우
    // 2. 추가적으로 뽑은 카드 1개를 사용해서 n+1 만드는 경우
    // 3. 추가적으로 뽑은 카드 2개를 이용해서 n+1 만드는 경우
    // 4. n+1을 만족하지 않는 경우
    
    int answer;
    int[] cards;
    int coin;
    int n;

    // 숫자 중복 X, 포함관계만 확인하면 됨.
    Set<Integer> myCard;
    Set<Integer> newCard;
    
    public int solution(int coin, int[] cards) {
        init(coin, cards);
        solve();
        return answer;
    }
    
    private void printSet(Set<Integer> set) {
        StringBuilder sb = new StringBuilder();
        for(int num : set) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        int idx = n/3;
        for(int i=0; i<idx; i++) {
            myCard.add(cards[i]);
        }
        
        int target = n+1;
        
        while(true) {
            
            answer++;
            
            if(idx >= n) break;
            
            newCard.add(cards[idx++]);
            newCard.add(cards[idx++]);
            
            boolean flag = false;
            // 1. 처음 시작하는 카드 뭉치에서 n+1 만드는 경우
            for(int num : myCard) {
                if(myCard.contains(target - num)) {
                    // System.out.println("TAG1");
                    myCard.remove(num);
                    myCard.remove(target - num);
                    flag = true;
                    break;
                }
            }
            // 2. 추가적으로 뽑은 카드 1개를 사용해서 n+1 만드는 경우
            if(!flag && coin >= 1) {
                for(int num : myCard) {
                    if(newCard.contains(target - num)) {
                        // System.out.println("TAG2");
                        myCard.remove(num);
                        newCard.remove(target - num);
                        flag = true;
                        coin--;
                        break;
                    }
                }
            }
            // 3. 추가적으로 뽑은 카드 2개를 이용해서 n+1 만드는 경우
            if(!flag && coin >= 2) {
                for(int num : newCard) {
                    if(newCard.contains(target - num)) {
                        // System.out.println("TAG3");
                        newCard.remove(num);
                        newCard.remove(target - num);
                        flag = true;
                        coin -= 2;
                        break;
                    }
                }
            }
            
            // 4. n+1을 만족하지 않는 경우
            if(!flag) {
                break;
            }
            
        }
        
    }
    
    public void init(int coin, int[] cards) {
        this.myCard = new HashSet<>();
        this.newCard = new HashSet<>();
        this.cards = cards;
        this.coin = coin;
        this.n = cards.length;
    }
}