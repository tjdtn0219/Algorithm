class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer;
        boolean possible = true;
        int minR = x, maxR = x, minC = y, maxC = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int queryType = queries[i][0], moveCount = queries[i][1];

            if (queryType == 0) {//좌측 이동 쿼리
                if (minC == 0) {//현재 구간 가장자리 좌측이 격자의 좌측(0열)과 겹친 경우
                    maxC = Math.min(m - 1, maxC + moveCount);
                } else {
                    if (minC + moveCount >= m) {//수행 불가능한 쿼리
                        possible = false;
                        break;
                    } else {//단순 이동 (구간의 경우 격자 밖을 벗어나 구간의 축소가 이루어질 수 있음)
                        minC = minC + moveCount;
                        maxC = Math.min(m - 1, maxC + moveCount);
                    }
                }
            }else if (queryType == 1) {//우측 이동 쿼리
                if (maxC == m - 1) {//현재 구간 가장자리 우측이 격자의 우측(m-1열)과 겹친 경우
                    minC = Math.max(0, minC - moveCount);
                } else {
                    if (maxC - moveCount < 0) {//수행 불가능한 쿼리
                        possible = false;
                        break;
                    } else {//단순 이동 (구간의 경우 격자 밖을 벗어나 구간의 축소가 이루어질 수 있음)
                        maxC = maxC - moveCount;
                        minC = Math.max(0, minC - moveCount);
                    }
                }
            }else if (queryType == 2) {//상단 이동 쿼리
                if (minR == 0) {//현재 구간 가장자리 상단이 격자의 상단(0행)과 겹친 경우
                    maxR = Math.min(n - 1, maxR + moveCount);
                } else {
                    if (minR + moveCount >= n) {//수행 불가능한 쿼리
                        possible = false;
                        break;
                    } else {//단순 이동 (구간의 경우 격자 밖을 벗어나 구간의 축소가 이루어질 수 있음)
                        minR = minR + moveCount;
                        maxR = Math.min(n - 1, maxR + moveCount);
                    }
                }
            }else if (queryType == 3) {//하단 이동 쿼리
                if (maxR == n - 1) {//현재 구간 가장자리 하단이 격자의 하단(n-1열)과 겹친 경우
                    minR = Math.max(0, minR - moveCount);
                } else {
                    if (maxR - moveCount < 0) {//수행 불가능한 쿼리
                        possible = false;
                        break;
                    } else {//단순 이동 (구간의 경우 격자 밖을 벗어나 구간의 축소가 이루어질 수 있음)
                        maxR = maxR - moveCount;
                        minR = Math.max(0, minR - moveCount);
                    }
                }
            }
        }

        if (possible) { //쿼리로 가능한 구간이 존재하는 경우 -> 구간 속 점들의 개수 계산
            answer = (long) (maxR - minR + 1) * (long) (maxC - minC + 1);
        } else {
            answer = 0;
        }

        return answer;
    }
}