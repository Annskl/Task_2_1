package ru.vsu.cr.uskova_anna_task2;

public class LinkedList<E> {
    private Node<E> first;
    public int size;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (first != null) {
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public void addLast(E element) {
        if (first == null) {
            addFirst(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> currentNode = first;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        size++;
    }

    public void add(E element, int index) {
        if (indexIsNotCorrect(index)) {
            return;
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> currentNode = first;
        int currentIndex = 0;
        while (currentIndex++ != index - 1) {
            currentNode = currentNode.next;
        }
        newNode.next = currentNode.next;
        currentNode.next = newNode;
        size++;
    }

    public void removeFirst() {
        first = first.next;
        size--;
    }

    public void removeLast() {
        Node<E> currentNode = first;
        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = null;
        size--;
    }

    public void remove(int index) {
        if (indexIsNotCorrect(index)) {
            return;
        }

        if (index == 0) {
            removeFirst();
            return;
        }

        if(index == size - 1){
            removeLast();
            return;
        }

        Node<E> currentNode = first;
        Node<E> previousNode = first;
        int currentIndex = 0;
        while (currentIndex != index) {
            previousNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }
        previousNode.next = currentNode.next;
        size--;
    }

    @SuppressWarnings("unchecked")
    public long countMaxElements(){
        if(size == 0){
            return 0;
        }

        E max = max();

        long count = 0;
        Node<E> current = first;
        while(current != null){
            if(((Comparable<E>)max).compareTo(current.item) == 0){
                count++;
            }
            current = current.next;
        }

        return count;
    }

    @SuppressWarnings("unchecked")
    public E max(){
        if(size == 0){
            return null;
        }

        E max = first.item;

        Node<E> current = first;
        while(current.next != null){
            if(((Comparable<E>)max).compareTo(current.next.item) < 0){
                max = current.next.item;
            }
            current = current.next;
        }
        return max;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder strLinkedList = new StringBuilder();
        Node<E> current = first;
        while (current.next != null) {
            strLinkedList.append(current.item).append(" ");
            current = current.next;
        }
        return strLinkedList.append(current.item).toString();
    }

    private boolean indexIsNotCorrect(int index) {
        return index < 0 || index > size;
    }
}

