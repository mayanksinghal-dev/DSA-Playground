package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap {
    private List<Integer> heapList;
    private boolean minHeap = true;

    public Heap(boolean minHeap) {
        heapList = new ArrayList<>();
        this.minHeap = minHeap;
    }

    public Heap(List<Integer> inputList, boolean minHeap) {
        this.minHeap = minHeap;
        heapList = new ArrayList<>(inputList);
        this.heapify();
    }

    //Shift up
    //Use when moving child to upwards based on heap prop
    //Used when inserted a new value, it
    private void heapifyUp(int childIdx) {
        int parentIdx = parentNode(childIdx);
        if (parentIdx == childIdx) return;
        int parentVal = heapList.get(parentIdx);
        int childVal = heapList.get(childIdx);
        if ((minHeap && parentVal > childVal) || (!minHeap && parentVal < childVal)) {
            swap(parentIdx, childIdx);
            heapifyUp(parentIdx);
        }
    }

    //Get Parent Node
    private int parentNode(int childIdx) {
        return (childIdx - 1) / 2;
    }

    //Swap parent and child node
    private void swap(int parentIdx, int childIdx) {
        int parent = heapList.get(parentIdx);
        int child = heapList.get(childIdx);
        heapList.set(parentIdx, child);
        heapList.set(childIdx, parent);
    }

    //Use when moving parent to downwards based on heap prop
    private void heapifyDown(int parentIdx) {
        int leftIdx = leftIdx(parentIdx);
        int rightIdx = rightIdx(parentIdx);
        int length = heapList.size();
        int swapIdx = parentIdx;
        if (minHeap) {
            if (leftIdx < length && heapList.get(swapIdx) > heapList.get(leftIdx)) {
                swapIdx = leftIdx;
            }
            if (rightIdx < length && heapList.get(swapIdx) > heapList.get(rightIdx)) {
                swapIdx = rightIdx;
            }
        } else {
            if (leftIdx < length && heapList.get(swapIdx) < heapList.get(leftIdx)) {
                swapIdx = leftIdx;
            }
            if (rightIdx < length && heapList.get(swapIdx) < heapList.get(rightIdx)) {
                swapIdx = rightIdx;
            }
        }
        if (swapIdx != parentIdx) {
            swap(parentIdx, swapIdx);
            heapifyDown(swapIdx);
        }
    }

    private void heapify() {
        int length = heapList.size();
        for (int parentIdx = (length / 2) - 1; parentIdx >= 0; parentIdx--) {
            heapifyDown(parentIdx);
        }
    }

    private int leftIdx(int parentIdx) {
        return (parentIdx * 2) + 1;
    }

    private int rightIdx(int parentIdx) {
        return (parentIdx * 2) + 2;
    }

    //Push/Insert Value in heap
    public int push(int value) {
        heapList.add(value);
        heapifyUp(heapList.size() - 1);
        return value;
    }

    //Peek top element
    public int peek() {
        if (heapList.isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heapList.get(0);
    }

    //Remove top element
    public void remove() {
        if (heapList.isEmpty()) throw new IllegalStateException("Heap is empty");
        if (heapList.size() < 3) {
            heapList.remove(heapList.size()-1);
            return;
        }
        heapList.set(0,heapList.remove(heapList.size()-1));
        heapifyDown(0);
    }
}
