import java.io.*;
import java.util.*;

public class Main {

    public static long[] arr;
    public static long[] maxArr;
    public static long[] minArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        int dep = getDepth(n);
        int treeSize = (int) Math.pow(2, dep+1);
//        System.out.println("TAG : " + Math.pow(2, dep+1));

        minArr = new long[treeSize];
        maxArr = new long[treeSize];
        Arrays.fill(minArr, Long.MAX_VALUE);
        Arrays.fill(maxArr, Long.MIN_VALUE);

        int size = (int) Math.pow(2, dep) - 1;
        arr = new long[n+1];
        for(int i=1; i<=n; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
            maxArr[size+i] = num;
            minArr[size+i] = num;
        }

        initMinTree(treeSize-1);
        initMaxTree(treeSize-1);
//        for(int i=0; i<minArr.length; i++) {
//            System.out.print(i + " : " + minArr[i] + "\n");
//        }
//        System.out.println();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            sb.append(getMinVal(size+a, size+b) + " " + getMaxVal(size+a, size+b) + "\n");
        }
        System.out.println(sb);
    }

    public static long getMinVal(int st, int en) {
        long partMinVal = Long.MAX_VALUE;
        while (st <= en) {
            if(st%2 == 1) {
                partMinVal = Math.min(partMinVal, minArr[st]);
                st++;
            }
            if(en%2 == 0) {
                partMinVal = Math.min(partMinVal, minArr[en]);
                en--;
            }
            st /= 2;
            en /= 2;
        }
        return partMinVal;
    }

    public static long getMaxVal(int st, int en) {
        long partMaxNum = Long.MIN_VALUE;
        while (st <= en) {
            if(st%2 == 1) {
                partMaxNum = Math.max(partMaxNum, maxArr[st]);
                st++;
            }
            if(en%2 == 0) {
                partMaxNum = Math.max(partMaxNum, maxArr[en]);
                en--;
            }
            st /= 2;
            en /= 2;
        }
        return partMaxNum;
    }

    public static int getDepth(int n) {
        int num = 2;
        int cnt = 1;

        while(num < n) {
            num *= 2;
            cnt++;
        }
        return cnt;
    }

    public static void initMinTree(int num) {
        while(num != 1) {
            minArr[num/2] = Math.min(minArr[num/2], minArr[num]);
            num--;
        }
    }

    public static void initMaxTree(int num) {
        while(num != 1) {
            maxArr[num/2] = Math.max(maxArr[num/2], maxArr[num]);
            num--;
        }
    }

}
