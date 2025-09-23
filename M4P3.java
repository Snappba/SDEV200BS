//202509022 SDEV200 Java - Module 4, Programming Assignment 3, Chapter 24 - exercise 24.3
//ex 24.3 is dependent on live example 24.5 MyLinkedList.java which is illegible
//https://ecampus.vitalsource.com/reader/books/9780138123352/epubcfi/6/758[%3Bvnd.vst.idref%3DP700101832900000000000000000B4A3]!/4/2[P700101832900000000000000000B4A3]/18[P700101832900000000000000000B5AA]/2/2[P700101832900000000000000000B5AB]/3:19[%20fo%2Cr%20]
import java.util.ListIterator;
import java.util.NoSuchElementException;

// package-private top-level interface so M4P3 can implement it in the same file
interface MyList<E> {
    void add(E e);
    void add(int index, E e);
    E remove(int index);
    boolean remove(E e);
    E get(int index);
    E set(int index, E e);
    int size();
    boolean isEmpty();
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);
}

public class M4P3<E> implements MyList<E> {

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;
        Node(E e) { element = e; }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public M4P3() { head = tail = null; size = 0; }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) head = tail = newNode;
        else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.previous = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            Node<E> curr = getNodeAt(index);
            Node<E> prev = curr.previous;
            prev.next = newNode;
            newNode.previous = prev;
            newNode.next = curr;
            curr.previous = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> target = getNodeAt(index);
        E element = target.element;
        Node<E> prev = target.previous;
        Node<E> next = target.next;
        if (prev != null) prev.next = next; else head = next;
        if (next != null) next.previous = prev; else tail = prev;
        target.next = target.previous = null;
        target.element = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(E e) {
        Node<E> curr = head;
        int idx = 0;
        while (curr != null) {
            if ((e == null && curr.element == null) || (e != null && e.equals(curr.element))) {
                remove(idx);
                return true;
            }
            curr = curr.next; idx++;
        }
        return false;
    }

    @Override
    public E get(int index) {
        return getNodeAt(index).element;
    }

    @Override
    public E set(int index, E e) {
        Node<E> node = getNodeAt(index);
        E old = node.element;
        node.element = e;
        return old;
    }

    private Node<E> getNodeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> curr;
        if (index < (size >> 1)) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.previous;
        }
        return curr;
    }

    @Override public int size() { return size; }
    @Override public boolean isEmpty() { return size == 0; }

    @Override public ListIterator<E> listIterator() { return new TwoWayListIterator(0); }
    @Override public ListIterator<E> listIterator(int index) { return new TwoWayListIterator(index); }

    private class TwoWayListIterator implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> nextNode;
        private int nextIndex;
        private boolean canRemoveOrSet;

        TwoWayListIterator(int index) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            nextNode = (index == size) ? null : getNodeAt(index);
            nextIndex = index;
            lastReturned = null;
            canRemoveOrSet = false;
        }

        @Override public boolean hasNext() { return nextIndex < size; }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            canRemoveOrSet = true;
            return lastReturned.element;
        }

        @Override public boolean hasPrevious() { return nextIndex > 0; }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            if (nextNode == null) nextNode = tail; else nextNode = nextNode.previous;
            lastReturned = nextNode;
            nextIndex--;
            canRemoveOrSet = true;
            return lastReturned.element;
        }

        @Override public int nextIndex() { return nextIndex; }
        @Override public int previousIndex() { return nextIndex - 1; }

        @Override
        public void remove() {
            if (!canRemoveOrSet) throw new IllegalStateException();
            Node<E> nodeToRemove = lastReturned;
            Node<E> prev = nodeToRemove.previous;
            Node<E> next = nodeToRemove.next;
            if (prev != null) prev.next = next; else head = next;
            if (next != null) next.previous = prev; else tail = prev;
            if (nextNode == nodeToRemove) nextNode = next; else nextIndex--;
            lastReturned = null;
            canRemoveOrSet = false;
            size--;
        }

        @Override
        public void set(E e) {
            if (!canRemoveOrSet) throw new IllegalStateException();
            lastReturned.element = e;
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            Node<E> prev = (nextNode == null) ? tail : nextNode.previous;
            newNode.next = nextNode;
            newNode.previous = prev;
            if (prev == null) head = newNode; else prev.next = newNode;
            if (nextNode == null) tail = newNode; else nextNode.previous = newNode;
            nextIndex++;
            lastReturned = null;
            canRemoveOrSet = false;
            size++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.element);
            curr = curr.next;
            if (curr != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // quick test
    public static void main(String[] args) {
        M4P3<String> list = new M4P3<>();
        list.add("A"); list.add("B"); list.add("C");
        System.out.println(list);
    }
}
