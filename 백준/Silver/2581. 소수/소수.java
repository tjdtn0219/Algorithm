import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n1 = in.nextInt();
        int n2 = in.nextInt();

        List<Integer> pNum_List = new ArrayList<>();

        for(int i=n1; i<=n2; i++){
            if(checkIsPNum(i)){
                pNum_List.add(i);
            }
        }

        int min = -1;
        if(pNum_List.size() > 0){
            min = pNum_List.get(0);
        } else{
            System.out.println(min);
            return;
        }
        int sum = 0;
        for(int pnum : pNum_List){
            if(pnum < min){
                min = pnum;
            }
            sum += pnum;
        }
        System.out.print(sum + "\n" + min);
    }

    public static boolean checkIsPNum(int num){
        if(num==1){
            return false;
        }
        for(int i=2; i<num; i++){
            if((num%i)==0){
                return false;
            }
        }
        return true;
    }
}