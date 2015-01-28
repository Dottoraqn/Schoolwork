#include "list.h"
#include <stdlib.h>
#include <limits.h>
#include <stdio.h>
#include <string.h>

int print_node(int x) {
    printf("%i, ",x);
    return x;
}

int main(void)
{
    /* Test create_list */
    list* llist = create_list();
    printf("Testing create_list()\n");
    printf("\texpected output: []\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head ptr: 0x0\n");
    printf("\t  actual head ptr: %p\n", llist->head);
    printf("\texpected tail ptr: 0x0\n");
    printf("\t  actual tail ptr: %p\n", llist->tail);

    /* Test push_front */
    push_front(llist, 5);
    push_front(llist, 10);
    push_front(llist, 15);
    push_front(llist, 42);
    printf("Testing push_front()\n");
    printf("\texpected output: [42, 15, 10, 5, ]\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head: 42\n");
    printf("\t  actual head: %i\n", llist->head->data);
    printf("\texpected tail: 5\n");
    printf("\t  actual tail: %i\n", llist->tail->data);
    
    /* Test push_back */
    push_back(llist, 99);
    push_back(llist, 7);
    printf("Testing push_back()\n");
    printf("\texpected output: [42, 15, 10, 5, 99, 7, ]\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head: 42\n");
    printf("\t  actual head: %i\n", llist->head->data);
    printf("\texpected tail: 7\n");
    printf("\t  actual tail: %i\n", llist->tail->data);

    
    /* Test pop_front */
    printf("Testing pop_front()\n");
    printf("\texpected output: 42\n");
    printf("\t  actual output: %i\n", pop_front(llist));
    printf("\texpected output: [15, 10, 5, 99, 7, ]\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head: 15\n");
    printf("\t  actual head: %i\n", llist->head->data);
    printf("\texpected tail: 7\n");
    printf("\t  actual tail: %i\n", llist->tail->data);
    
    /* Test empty_list */
    printf("Testing empty_list()\n");
    empty_list(llist);
    printf("\texpected output: []\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head ptr: 0x0\n");
    printf("\t  actual head ptr: %p\n", llist->head);
    printf("\texpected tail ptr: 0x0\n");
    printf("\t  actual tail ptr: %p\n", llist->tail);
        
    /* Test pop_front II */
    printf("Testing pop_front() again\n");
    pop_front(llist);
    printf("\texpected output: []\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected head ptr: 0x0\n");
    printf("\t  actual head ptr: %p\n", llist->head);
    printf("\texpected tail ptr: 0x0\n");
    printf("\t  actual tail ptr: %p\n", llist->tail);
    
    /* Test pop_back */
    push_back(llist, 15);
    push_back(llist, 42);
    printf("Testing pop_back()\n");
    printf("\texpected output: 42\n");
    printf("\t  actual output: %i\n", pop_back(llist));
    printf("\texpected output: [15, ]\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    printf("\texpected output: 15\n");
    printf("\t  actual output: %i\n", pop_back(llist));
    printf("\texpected output: []\n");
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
    
    /* Test popping empty lists*/
    empty_list(llist);
    printf("Testing popping from empty lists\n");
    printf("\texpected output: -1\n");
    printf("\t  actual output: %i\n", pop_front(llist));
    printf("\texpected output: -1\n");
    printf("\t  actual output: %i\n", pop_back(llist));
    
    /* Test size */
    printf("Testing size()\n");
    printf("\texpected output: 0\n");
    printf("\t  actual output: %i\n", size(llist));
    push_back(llist, 0);
    push_back(llist, 2);
    printf("\texpected output: 2\n");
    printf("\t  actual output: %i\n", size(llist));
    push_back(llist, 3);
    push_back(llist, 4);
    push_back(llist, 3000000);
    push_back(llist, 7000000);
    printf("\texpected output: 6\n");
    printf("\t  actual output: %i\n", size(llist));
    
    /* Test scale_up */
    printf("Testing scale_up()\n");
    scale_up(llist);
    printf("\texpected output: [0, 2048, 3072, 4096, %i, %i, ]\n", INT_MAX, INT_MAX);
    printf("\t  actual output: [");
    traverse(llist, print_node);
    printf("]\n");
        
    /* Testing over, clean up*/
    empty_list(llist);
    free(llist);
	
    return 0;
}
