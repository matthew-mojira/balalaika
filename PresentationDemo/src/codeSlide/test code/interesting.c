#include <stdio.h>

int main(void) {
	signed int i = 0;
	
	for (i = -20; i < 127; i += 17) {
		printf("The number at present is %d\n", i);
	}
	printf("i was located at: %p\n", &i);
}