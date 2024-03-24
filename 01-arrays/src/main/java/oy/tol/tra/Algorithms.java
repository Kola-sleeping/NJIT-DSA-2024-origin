package oy.tol.tra;

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
}
