import java.util.*;

public class Main{
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        String bf = in.nextLine();

        int cnt = 0;
        for(int i=0; i<N; i++){
            String word = in.nextLine();
            if(checkGroupWord(word)){
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    public static boolean checkGroupWord(String s){
        List<Integer> arrList = new ArrayList<>();
        init_list(arrList, 26);

        for(int i=0; i<s.length(); i++){
            int list_idx = s.charAt(i) - 'a';
            if(arrList.get(list_idx) == 0){
                arrList.set(list_idx, arrList.get(list_idx)+1);
            }
            else {
                if(i>0 && s.charAt(i-1)==s.charAt(i)){
                    //pass
                } else {
                    return false;
                }
            }
        }
        return true;

    }

    public static void init_list(List<Integer> list, int size){
        for(int i=0; i<size; i++){
            list.add(0);
        }
    }
}
