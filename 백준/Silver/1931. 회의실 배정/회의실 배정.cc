#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <algorithm>

struct schedule {
	int start_time;
	int end_time;
};

using namespace std;

bool compare(struct schedule s1, struct schedule s2) {
	if (s1.end_time == s2.end_time)
		return s1.start_time < s2.start_time;
	else
		return s1.end_time < s2.end_time;
}

int main()
{
	int n, i;
	int count = 0;
	scanf("%d", &n);
	struct schedule arr[100001];
	for (i = 0; i < n; i++)
	{
		scanf("%d %d", &arr[i].start_time, &arr[i].end_time);
	}
	sort(arr, arr + n, compare);
	int standard = 0;
	for (i = 0; i < n; i++)
	{
		if (standard <= arr[i].start_time)
		{
			count++;
			standard = arr[i].end_time;
		}
		
		
	}
	printf("%d", count);
	return 0;
}