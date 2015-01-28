/**
 * CS 2261 - Linked List Lab
 *
 * list.c
 **/

#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "list.h"

/** create_list
  *
  * Creates a list by allocating memory for it on the heap.
  * Be sure to initialize head and tail to NULL.
  *
  * @return an empty linked list
  */
list* create_list(void) {
    list* plist;
    plist = malloc(sizeof(list));
    if (plist == NULL) {
        printf("Failed to allocate memory for list");
        exit(EXIT_FAILURE);
    }
    plist->head = NULL;
    plist->tail = NULL;

    return plist;
}

/** create_node
  *
  * Helper function that creates a node by allocating memory for it on the heap.
  * Be sure to set its pointers to NULL and to set the data
  *
  * @param data a an integer to store in the list
  * @return a node
  */
static node* create_node(int data) {
  node* pnode;
  pnode = malloc(sizeof(node));
  if (pnode == NULL) {
      printf("Failed to allocate memory for node");
      exit(EXIT_FAILURE);
  }
  pnode->data = data;
  pnode->prev = NULL;
  pnode->next = NULL;

  return pnode;
}

/** push_front
  *
  * Adds the data to the front of the linked list
  *
  * @param llist a pointer to the list
  * @param data an integer to store in the list
  */
void push_front(list* llist, int data) {
  node *pnode = create_node(data);

  if (llist->head != NULL) { //list isn't empty
      pnode->next = llist->head;
      llist->head->prev = pnode;
  } else { //list is empty
      llist->tail = pnode;
  }
  llist->head = pnode;
}

/** push_back
  *
  * Adds the data to the end of the linked list
  *
  * @param llist a pointer to the list.
  * @param data an integer to store in the list
  */
void push_back(list* llist, int data) {
  node *pnode = create_node(data);
  if(llist->head != NULL){
    pnode->prev = llist->tail;
    llist->tail->next = pnode;
  }
  else{
    llist->head = pnode;
  }
  llist->tail = pnode;
}

/** pop_front
  *
  * Removes the node at the front of the linked list and returns its value.
  * Popping an empty list returns -1
  *
  * @param llist a pointer to the list.
  */
int pop_front(list* llist) {
  if(llist->head == NULL) return -1;
  
  int temp = llist->head->data;
  node *mem = llist->head;
  llist->head = llist->head->next;

  if(llist->head == NULL) llist->tail = NULL;
  else if(llist->head->next == NULL) llist->tail = llist->head;
  if(llist->head != NULL) llist->head->prev = NULL;

  free(mem);
  return temp;
}

/** pop_back
  *
  * Removes the node at the back of the linked list
  *
  * Popping an empty list returns -1
  * @param llist a pointer to the list.
  */
int pop_back(list* llist) {
  if(llist->head == NULL) return -1;
  
  int temp = llist->tail->data;
  node *mem = llist->tail;
  llist->tail = llist->tail->prev;

  if(llist->head->next == NULL){
    llist->head = NULL;
    llist->tail = NULL;
  }

  if(llist->head == NULL)
    llist->tail = NULL;
  else if(llist->head->next == NULL)
    llist->tail = llist->head;
  if(llist->head != NULL)
    llist->tail->next = NULL;

  free(mem);
  return temp;
}

/** size
  * Gets the number of nodes in the linked list
  *
  * @param llist a pointer to the list
  * @return The size of the linked list
  */
int size(list* llist) {
  int size = 0;
  if(llist->head != NULL){
    node *p = llist->head;
    while(p != NULL){
      size++;
      p = p->next;
    }
    return size;
  }
  else{
    return 0;
  }
  return 0;
}

/** empty_list
  *
  * Empties the list after this is called the list should be empty.
  *
  * @param llist a pointer to a linked list.
  *
  */
void empty_list(list* llist) {
  while(llist->head != NULL){
    pop_front(llist);
  }
}

/** traverse
  *
  * Traverses the linked list calling a function on each node's data to modify
  * that node's data.
  *
  * @param llist a pointer to a linked list.
  * @param function a function that does something to each node's data.
  */
void traverse(list* llist, list_operation function) {
  node *p = llist->head;
   while (p != NULL) {
      p->data = function(p->data);
      p = p->next;
  }
}

/** scale_up
  *
  * Scales each number in the list up by a factor of 1024 (2^10) times for
  * use in fixed point physics math.
  *
  * NOTE! if a number would be larger than the maximum value of a signed int
  *       after scaling, scale_up replaces the number with INT_MAX from
  *       limits.h instead of scaling it.
  *
  * @param llist a pointer to a linked list.
  */
void scale_up(list* llist) {
  node *p = llist->head;
  int temp = 0;
    while(p!=NULL){
      temp = p->data << 10;
      if(temp >= INT_MAX || temp < 0){
        p->data = INT_MAX;
        p = p->next;
      }
      else{
        p->data = p->data << 10;
        p = p->next;
      }
    }
}

