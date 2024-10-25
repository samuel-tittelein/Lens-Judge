#include <stdio.h>

int main(void) {
    int h, m;
    scanf("%d %d", &h, &m);
    printf("%d %d\n", (12 - h) % 12, (60 - m) % 60);
    return 0;
}
