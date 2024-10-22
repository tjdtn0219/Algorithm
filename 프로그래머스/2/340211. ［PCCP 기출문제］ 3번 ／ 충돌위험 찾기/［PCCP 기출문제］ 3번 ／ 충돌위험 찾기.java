import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private class Robot {
        private int r;
        private int c;
        private int nextStopover;

        public Robot(int r, int c, int nextStopover) {
            this.r = r;
            this.c = c;
            this.nextStopover = nextStopover;
        }

        public void moveToTarget(int targetR, int targetC) {
            if (r < targetR) {
                r++;  
                return;
            } else if (r > targetR) {
                r--; 
                return;
            }

            if (c < targetC) {
                c++; 
            } else if (c > targetC) {
                c--;  
            }
        }

        public boolean hasArrived(int targetR, int targetC) {
            return r == targetR && c == targetC;
        }

        public void stopoverPlus() {
            nextStopover++;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int routeStep = routes[0].length;
        List<Robot> robots = new ArrayList<>();
        Map<Integer, Integer> startCollisions = new HashMap<>();

        for (int[] route : routes) {
            int startPoint = route[0] - 1;
            int r = points[startPoint][0];
            int c = points[startPoint][1];
            robots.add(new Robot(r, c, 1));
            startCollisions.merge(route[0], 1, Integer::sum);
        }

        int result = 0;

        for (Integer value : startCollisions.values()) {
            if (value > 1) {
                result++;
            }
        }

        while (!robots.isEmpty()) {
            Map<String, Integer> collisionMap = new HashMap<>();
            boolean[] arrived = new boolean[robots.size()];

            for (int i = 0; i < robots.size(); i++) {
                Robot robot = robots.get(i);
                int nextStopover = robot.nextStopover;

                if (nextStopover >= routeStep) {
                    arrived[i] = true;
                    continue;
                }

                int pointIndex = routes[i][nextStopover] - 1;
                int targetR = points[pointIndex][0];
                int targetC = points[pointIndex][1];

                robot.moveToTarget(targetR, targetC);

                String posKey = robot.r + " " + robot.c;
                collisionMap.merge(posKey, 1, Integer::sum);

                if (robot.hasArrived(targetR, targetC)) {
                    robot.stopoverPlus();
                }
            }

            for (Integer value : collisionMap.values()) {
                if (value > 1) {
                    result++;
                }
            }

            boolean allArrived = true;
            for (boolean arrivedStatus : arrived) {
                if (!arrivedStatus) {
                    allArrived = false;
                    break;
                }
            }

            if (allArrived) {
                break;
            }
        }

        return result;
    }
}