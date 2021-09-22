#include <stdio.h>

static void test();

int main() {
    test();
    return 0;
}

void organiseNumbers(int *numbers, int len) {
    for(int i = 0, j = 0; i < len; i++)
        if(numbers[i] < 0) swap(numbers, i, j++);
}

void swap(int *numbers, int a, int b) {
    int t = numbers[a];
    numbers[a] = numbers[b];
    numbers[b] = t;
}

void printNumbers(int numbers[], int len) {
    printf("[");
    for(int i = 0; i < len; i++) {
        printf("%d", numbers[i]);
        if(i < len - 1) printf(", ");
    }
    printf("]");
}



static void test() {
    printf("\n Write 's' to start, 'q' to exit \n$ ");
    while(1) {
        char res;
        scanf("%c", &res);
        if(res == 'q') break;
        printf("\n How many numbers \n$ ");
        int n;
        scanf("%d", &n);
        int numbers[n];
        for(int i = 0; i < n; i++) {
            printf("Enter number %d of %d\n$ ", i + 1, n);
            int num;
            scanf("%d", &num);
            numbers[i] = num;
        }
        
        organiseNumbers(numbers, n);
        printf("Result: ");
        printNumbers(numbers, n);
        printf("\n");
    }
}