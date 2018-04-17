package blackmage.util;

public class ArrayManip {
	public static float max(float[] array) {
		float max = array[0];
		for (int j = 1; j < array.length; j++) {
			if (array[j] > max) {
				max = array[j];
			}
		}
		return max;
	}
	
	public static float min(float[] array) {
		float min = array[0];
		for(int i = 1; i < array.length; i++) {
			if(array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}
}
