import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        List<Point> pList = new ArrayList<>();

        for(int i=0; i<N; i++){
            String[] str = bf.readLine().strip().split(" ");
            Point point = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            pList.add(point);
        }

        Collections.sort(pList, (final Point p1, final Point p2)->{
            if(p1.y == p2.y) return p1.x - p2.x;
            return p1.y - p2.y;
        });

        for(Point p : pList){
            System.out.println(p.x + " " + p.y);
        }
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
