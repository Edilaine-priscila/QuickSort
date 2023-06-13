package Unifaccamp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {

	private int comparisons=0;
	private int movements =0;
	
	private class QuickSortTask extends RecursiveAction {
        private final Integer[] arr;
        private final int low;
        private final int high;

        public QuickSortTask(Integer[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                int partitionIndex = partition(arr, low, high);

                QuickSortTask leftTask = new QuickSortTask(arr, low, partitionIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(arr, partitionIndex + 1, high);

                invokeAll(leftTask, rightTask);
            }
        }
    }
	
    private void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
        	comparisons++;
            if (arr[j] < pivot) {
                i++;
                movements++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public int getComparisons() {
        return comparisons;
    }

    public int getMovements() {
        return movements;
    } 
    
    public List<Integer> Parallelized(List<Integer> numbers) {
    	comparisons=0;
    	movements=0;
		Integer[] arr = numbers.toArray(new Integer[0]);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new QuickSortTask(arr, 0, arr.length - 1));
        
        return Arrays.asList(arr);
	}
	
	public List<Integer> Sequential(List<Integer> numbers){
		comparisons=0;
    	movements=0;
		Integer[] arr = numbers.toArray(new Integer[0]);
        quickSort(arr, 0, arr.length - 1);
        
        return Arrays.asList(arr);
	}
}
