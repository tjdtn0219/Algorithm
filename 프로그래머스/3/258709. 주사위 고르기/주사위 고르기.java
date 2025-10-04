import java.util.*;
class Solution {
    int N;
    int[][] D;
    int[] maxList;
    int maxWin;
    int tmpSum;

    int[] A;
    int[] B;

    static List<Integer> sumList;

    public void calSumA(int ind, int sum) {
        if (ind == N/2) {
            // //sum을 가지고 BS
            // int lo = 0;
            // int hi = sumList.size();
            // while (lo + 1 < hi) {
            //     int mid = (lo + hi) / 2;
            //     if (sumList.get(mid) < sum) lo = mid;
            //     else hi = mid;
            // }
            // tmpSum += (lo + 1);
            tmpSum += getUpperIdx(sum);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumA(ind+1, sum + D[A[ind]][i]);
    }
    
    public int getUpperIdx(int target) {
        int left = 0;
        int right = sumList.size();
        
        while(left < right) {
            int mid = (left + right) / 2;
            if(target <= sumList.get(mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public void calSumB(int ind, int sum) {
        if (ind == N/2) {
            sumList.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++)
            calSumB(ind+1, sum + D[B[ind]][i]);
    }

    public void calCnt() {
        sumList = new ArrayList<>();
        tmpSum = 0;
        //B의 모든 경우의 수 계산
        calSumB(0, 0);
        Collections.sort(sumList);

        //A의 모든 경우의 수 계산 후 BS
        calSumA(0, 0);
        if (maxWin < tmpSum) {
            maxWin = tmpSum;
            for (int i = 0; i < N/2; i++)
                maxList[i] = A[i] + 1;
        }
    }

    public void select(int cur, int ind) {
        //선택이 모두 끝났으면
        if (ind == N/2) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < N/2; j++)
                    if (A[j] == i) flag = true;
                if (flag) continue;
                B[index++] = i;
            }
            calCnt();
            return;
        }
        //범위 넘어가면
        if (cur >= N) return;
        A[ind] = cur;
        select(cur+1, ind+1);
        select(cur+1, ind);
    }

    public void init(int[][] dice) {
        N = dice.length;
        D = dice;
        maxList = new int[N/2];
        maxWin = Integer.MIN_VALUE;
        A = new int[N/2];
        B = new int[N/2];
    }

    public int[] solution(int[][] dice) {
        //초기화
        init(dice);
        select(0, 0);
        return maxList;
    }
}