#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int Sort(int a[], int num);

int main()
{
	int n;
	int arr[1000] = {};
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		scanf("%d", &arr[i]);
	}
	*arr = Sort(arr, n);
	int sum = 0;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j <= i; j++)
		{
			sum += arr[j];
		}
	}
	printf("%d\n", sum);
	return 0;
}

int Sort(int a[],int num)
{
	int *arr_sort = a;
	int temp;
	for (int i = 0; i < num; i++)
	{
		for (int j = 0; j < num-1; j++)
		{
			if (arr_sort[j] > arr_sort[j + 1])
			{
				temp = arr_sort[j];
				arr_sort[j] = arr_sort[j + 1];
				arr_sort[j + 1] = temp;
			}
		}
	}
	return *arr_sort;
}