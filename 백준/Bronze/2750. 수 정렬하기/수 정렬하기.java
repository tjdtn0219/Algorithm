import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

n= Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

sort(arr);

        for(int i=0; i<n; i++) System.out.println(arr[i]);
        System.out.println();

    }

    public static void sort(int[] arr) {
//        quickSort(arr, 0, n-1);
        bubbleSort(arr);
//        mergeSort(arr, 0,n-1);
    }

    public static void bubbleSort(int[] arr) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }


    public static void mergeSort(int[] arr, int low, int high) {
        if(low >= high) return;

        int mid = (low + high)/2;

mergeSort(arr, low, mid);
mergeSort(arr, mid+1, high);

merge(arr, low, mid, high);
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int idx = 0;

        int left = low;
        int right = mid+1;
        while(left<=mid && right<=high) {
            if(arr[left] <= arr[right]) {
                temp[idx++] = arr[left++];
            } else {
                temp[idx++] = arr[right++];
            }
        }
        while(left<=mid) {
            temp[idx++] = arr[left++];
        }
        while(right<=high) {
            temp[idx++] = arr[right++];
        }
        for(int i=low; i<=high; i++) {
            arr[i] = temp[i-low];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low>=high) return ;

        int pivot = (low + high) / 2;
        int pivotValue = arr[pivot];

        int left = low;
        int right = high;
        while(left<=right) {
            while(arr[left] < pivotValue) left++;
            while(arr[right] > pivotValue) right--;
            if(left <= right) {
                int tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        }

        quickSort(arr, low, right);
        quickSort(arr, left, high);
    }
}
