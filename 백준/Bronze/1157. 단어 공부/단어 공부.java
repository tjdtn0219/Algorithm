import java.util.*;

public class Main{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        List<Integer> list = new ArrayList<>();
        initArr(list);

        //A-Z 65-90 , a-z 97-122
        s = s.toUpperCase();

        for(int i=0; i<s.length(); i++){
            int asciiVal = s.charAt(i);
            int arrIdx = asciiVal - 65;
            list.set(arrIdx, list.get(arrIdx) + 1);
        }

        int max = list.get(0);
        int max_idx = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i) > max){
                max = list.get(i);
                max_idx = i;
            }
        }
        int max_cnt = 0;
        for(int num : list){
            if(max == num){
                max_cnt++;
            }
        }
        if(max_cnt > 1){
            System.out.println("?");
        }
        else {
            char c = (char) (max_idx + 65);
            System.out.println(c);
        }

    }
    public static void initArr(List<Integer> list){
        for(int i=0; i<26; i++){
            list.add(0);
        }
    }
}