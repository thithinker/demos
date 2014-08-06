package demos.algorithm.dsalgo;

public class FindMaxAndMinInArr {
	public void find1(int[] arr){
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int a, b;
		if(arr.length % 2 == 0){
			for(int i = 0; i < arr.length; i += 2){
				a = arr[i];
				b = arr[i + 1];
				if(a > b){
					if(a > max){
						max = a;
					}
					if(b < min){
						min = b;
					}
				}else{
					if(b > max){
						max = b;
					}
					if(a < min){
						min = a;
					}
				}
			}
		}else {
			for(int i = 0; i < arr.length - 1; i += 2){
				a = arr[i];
				b = arr[i + 1];
				if(a > b){
					if(a > max){
						max = a;
					}
					if(b < min){
						min = b;
					}
				}else{
					if(b > max){
						max = b;
					}
					if(a < min){
						min = a;
					}
				}
			}
			if(arr[arr.length - 1] > max){
				max = arr[arr.length - 1];
			}
			if(arr[arr.length - 1] < min){
				min = arr[arr.length - 1];
			}
		}
		System.out.println("max: " + max + " min: " + min);
	}
	
	public MinMax find2(int[] arr, int start, int end){
		if(start > end)
			return null;
		if(start == end){
			return new MinMax(arr[start], arr[start]);
		}else{
			MinMax left;
			MinMax right;
			left = find2(arr, start, (start + end) / 2);
			right = find2(arr, (start + end) / 2 + 1, end);
			if(left == null){
				return right;
			}
			if(right == null){
				return left;
			}
			return new MinMax(Math.min(left.min, right.min), Math.max(left.min, right.max));
		}
	}
	
	private class MinMax{
		int min; 
		int max;
		public MinMax(int min, int max){
			this.min = min;
			this.max = max;
		}
	}
	public static void main(String[] args) {
		int[] arr = {12, 43, 56, 4, 67, 78, 99, 2, 4, 5, 111};
		new FindMaxAndMinInArr().find1(arr);
		
		MinMax minMax = new FindMaxAndMinInArr().find2(arr, 0, arr.length - 1);
		System.out.println("max:" + minMax.max + " min:" + minMax.min);
	}
}
