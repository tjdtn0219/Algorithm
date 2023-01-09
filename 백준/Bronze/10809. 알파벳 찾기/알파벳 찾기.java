import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        String word = in.nextLine();
        //a-z == 97-122

        List<Integer> arrList = new ArrayList<>();
        initArr(arrList, 26);

        for(int i=0; i<word.length(); i++){
            int ascii_val = word.charAt(i);
            int alphabet_idx = ascii_val - 97;

            if(arrList.get(alphabet_idx) == -1){
                arrList.set(alphabet_idx, i);
            }
        }

        for(int num : arrList){
            System.out.print(num + " ");
        }
    }

    public static void initArr(List<Integer> arr, int arrLen){
        for(int i=0; i<arrLen; i++){
            arr.add(-1);
        }
    }
}