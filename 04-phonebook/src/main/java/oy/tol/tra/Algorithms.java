package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {
	public static <T>void swap(T[] array,int first,int second){
		T temp=array[first];
		array[first]=array[second];
		array[second]=temp;
	}
	public static <T extends Comparable<T>> void sort(T[] array) {
		int currentIndex = 0;
		while (currentIndex < array.length - 1) {
			int innerIndex = 0;
			while (innerIndex < array.length - currentIndex - 1) {
				if (array[innerIndex].compareTo(array[innerIndex + 1]) > 0) {
					swap(array, innerIndex, innerIndex + 1);
				}
				innerIndex++;
			}
			currentIndex++;
		}

	}

	public static <T> void reverse(T[] array) {
		int startIndex = 0;
		int endIndex = array.length - 1;
		for (; startIndex < endIndex; startIndex++, endIndex--) {
			swap(array, startIndex, endIndex);
		}
	}
	public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
		while (fromIndex <= toIndex) {
			int mid = fromIndex + (toIndex - fromIndex) / 2;
			int compareResult = aValue.compareTo(fromArray[mid]);
			if (compareResult == 0) {
				return mid;
			} else if (compareResult < 0) {
				toIndex = mid - 1;
			} else {
				fromIndex = mid + 1;
			}
		}
		return -1;
	}
	public static <T extends Comparable<T>> void fastSort(T[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
		if (low < high) {
			int pivotIndex = partition(array, low, high);
			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}

	private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
		T pivot = array[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, high);
		return i + 1;
	}


	public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
		// Find first element rules applies to.
		// Index of that element will be in variable index.
		int index = 0;
		for (; index < count; index++) {
			if (rule.test(array[index])) {
				break;
			}
		}
		// If went to the end, nothing was selected so quit here.
		if (index >= count) {
			return count;
		}
		// Then start finding not selected elements starting from next from index.
		// If the element is not selected, swap it with the selected one.
		int nextIndex = index + 1;
		// Until end of array reached.
		while (nextIndex != count) {
			if (!rule.test(array[nextIndex])) {
				swap(array, index, nextIndex);
				// If swapping was done, add to index since now it has non-selected element.
				index++;
			}
			nextIndex++;
		}
		return index;
	}

	public static void sortWithComparator(Person[] array, Comparator comparator) {
		Arrays.sort(array,comparator);
	}
}
