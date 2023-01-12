import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<String> arr_result = new ArrayList<>();

        int T = in.nextInt();

        for(int i=0; i<T; i++){
            int H = in.nextInt();
            int W = in.nextInt();
            int N = in.nextInt();

            arr_result.add(function_room(H,W,N));
        }

        for(String room : arr_result){
            System.out.println(room);
        }
    }

    public static String function_room(int H, int W, int N){
        int room_num = 1 + N/H;
        int floor_num = N % H;

        if(floor_num == 0){
            floor_num = H;
            room_num--;
        }

        if(room_num < 10){
            return floor_num + "0" + room_num;
        }

        String room = floor_num + "" + room_num;
        return room;
    }
}