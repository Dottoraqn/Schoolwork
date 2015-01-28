/**
 * CS 2261 - Linked List Lab
 *
 * list.h
 **/


#ifndef LIST_H
#define LIST_H

/* The node struct.  Has a prev pointer, next pointer, and data. */
typedef struct lnode
{
  struct lnode* prev; /* Pointer to previous node */
  struct lnode* next; /* Pointer to next node */
  int data; /* User data */
} node;

/* The linked list struct.  Has a head pointer. */
typedef struct llist
{
  struct lnode* head; /* Head pointer either points to a node with data or NULL */
  struct lnode* tail;
} list;

/* A function pointer type that points to a function that takes in a void* and an int */
typedef int (*list_operation)(int x);


/* Prototypes */

/* Creating */
static node* create_node(int data);
list* create_list(void);

/* Adding */
void push_front(list* llist, int data);
void push_back(list* llist, int data);

/* Removing */
int pop_front(list* llist);
int pop_back(list* llist);

/* Querying List */
int size(list* llist);

/* Freeing */
void empty_list(list* llist);

/* Traversal */
void traverse(list* llist, list_operation do_func);


#endif
