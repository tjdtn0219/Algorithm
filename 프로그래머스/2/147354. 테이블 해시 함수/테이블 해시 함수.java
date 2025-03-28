import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        col -= 1;
        row_begin -= 1;

        // 2. 정렬
        int finalCol = col;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[finalCol] == o2[finalCol]) return o2[0] - o1[0];
            return o1[finalCol] - o2[finalCol];
        });

        // 3. S_i 합 구하기
        for (int i = row_begin; i < row_end; i++) {

            int finalI = i + 1;
            int dataTotal = Arrays.stream(data[i])
                    .map(j -> j % finalI)
                    .sum();

            // 4. XOR한 값 저장
            answer = (answer ^ dataTotal);
        }

        return answer;
    }
    
}