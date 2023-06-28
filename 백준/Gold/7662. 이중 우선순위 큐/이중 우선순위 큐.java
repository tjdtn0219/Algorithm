import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(bf.readLine());
            TreeMap<Integer, Integer> tm = new TreeMap<>();

            for(int i=0; i<n; i++) {
                String[] input = bf.readLine().split(" ");
                int num = Integer.parseInt(input[1]);
                if(input[0].charAt(0) == 'I') {
                    if(!tm.containsKey(num)) tm.put(num, 0);
                    else tm.put(num, tm.get(num) + 1);
                } else if(input[0].charAt(0) == 'D') {
                    if(tm.isEmpty()) continue;
                    if(num==-1) {
                        Map.Entry<Integer, Integer> entry = tm.firstEntry();
                        if(entry.getValue() == 0) {
                            tm.pollFirstEntry();
                        } else {
                            tm.put(entry.getKey(), entry.getValue() - 1);
                        }
                    } else if(num == 1) {
                        Map.Entry<Integer, Integer> entry = tm.lastEntry();
                        if(entry.getValue() == 0) {
                            tm.pollLastEntry();
                        } else {
                            tm.put(entry.getKey(), entry.getValue() - 1);
                        }
                    }
                }
            }
            if(tm.isEmpty()) System.out.println("EMPTY");
            else System.out.println(tm.lastKey() + " " + tm.firstKey());
        }
//        System.out.println(set); //전체출력 [2,3,4]
//        System.out.println(set.first());//최소값 출력
//        System.out.println(set.last());//최대값 출력
//        System.out.println(set.higher(3));//입력값보다 큰 데이터중 최소값 출력 없으면 null
//        System.out.println(set.lower(3));//입력값보다 작은 데이터중 최대값 출력 없으면 null

    }
}