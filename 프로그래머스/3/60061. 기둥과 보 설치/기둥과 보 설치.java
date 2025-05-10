import java.awt.Point;
import java.util.*;


class Frame {
    Point p;
    int type;
    public Frame(Point p, int type) {
        this.p = p;
        this.type = type;
    }
    
    @Override
    public boolean equals(Object o) {
        Frame other = (Frame) o;
        return this.p.equals(other.p) && this.type == other.type;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(p, type);
    }
}

class Solution {
    
    int n;
    int[][] frames;
    int[][] answer;
    HashSet<Frame> set;
    
    public int[][] solution(int n, int[][] build_frame) {
        init(n, build_frame);
        solve();
        return answer;
    }
    
    private void test() {
        set.add(new Frame(new Point(1, 2), 2));
        set.add(new Frame(new Point(1, 2), 2));
        set.add(new Frame(new Point(1, 2), 2));
        System.out.println(set.size());
        set.remove(new Frame(new Point(1, 2), 2));
        System.out.println(set.size());
    }
    
    public void solve() {
        // test();
        //[[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],
        //[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]]
        for(int[] frame : frames) {
            Point p = new Point(frame[0], frame[1]);
            int type = frame[2];
            Frame f = new Frame(p, type);
            if(frame[3] == 1) {
                // 설치
                set.add(f);
                if(!isOkay()) {
                    // System.out.println(f.p);
                    set.remove(f);
                }
            } else {
                // 삭제
                set.remove(f);
                if(!isOkay()) set.add(f);
            }
        }
        
        List<Frame> frameList = new ArrayList<>(set);
        Collections.sort(frameList, (o1, o2) -> {
            if(o1.p.x == o2.p.x) {
                if(o1.p.y == o2.p.y) {
                    return o1.type - o2.type;
                }
                return o1.p.y - o2.p.y;
            } else {
                return o1.p.x - o2.p.x;
            }
        });
        
        answer = new int[frameList.size()][3];
        for(int i=0; i<frameList.size(); i++) {
            answer[i][0] = frameList.get(i).p.x;
            answer[i][1] = frameList.get(i).p.y;
            answer[i][2] = frameList.get(i).type;
        }
    }
    
    public boolean isOkay() {
        for(Frame frame : set) {
            Point p = frame.p;
            int type = frame.type;
            if(type == 0) {
                //기둥
                if(p.y == 0 || set.contains(getFrame(p.x-1, p.y, 1))
                  || set.contains(getFrame(p.x, p.y, 1)) || set.contains(getFrame(p.x, p.y-1, 0))) {
                    continue;
                } else {
                    // System.out.println("ERROR 기둥 :");
                    return false;
                }
            } else {
                //보
                if(set.contains(getFrame(p.x, p.y-1, 0)) || set.contains(getFrame(p.x+1, p.y-1, 0))) {
                    //한쪽 끝에 기둥이 있는 경우
                    continue;
                } else {
                    if(p.x == 0 || p.x == n) {
                        // 한쪽 끝에 기둥이 없는데 끝자락일 경우
                        // System.out.println("ERROR 보 :한쪽 끝에 기둥이 없는데 끝자락이지 않은 경우");
                        return false;
                    } else {
                        if(set.contains(getFrame(p.x-1, p.y, 1)) && set.contains(getFrame(p.x+1, p.y, 1))) {
                            // 한쪽 끝이 기둥이 없지만 양쪽 끝 부분이 다른 보와 연결
                            continue;
                        } else {
                            // System.out.println("ERROR 보 :한쪽 끝이 기둥이 없지만 양쪽 끝 부분이 다른 보와 연결 X");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public Frame getFrame(int x, int y, int type) {
        return new Frame(new Point(x, y), type);
    }
    
    public void init(int n, int[][] frames) {
        this.n = n;
        this.frames = frames;
        this.set = new HashSet<>();
    }
}
