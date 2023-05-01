#include <cstdio>
#include <algorithm>
using namespace std;
 
int main(){
    int N, w[100000];
    scanf("%d", &N);
    for(int i=0; i<N; i++)
        scanf("%d", w+i);
 
    // 로프의 강도 정렬
    sort(w, w+N);
 
    // 결과 구하기
    int result = 0;
    for(int i=1; i<=N; i++)
        result = max(result, w[N-i]*i);
    printf("%d\n", result);
}

