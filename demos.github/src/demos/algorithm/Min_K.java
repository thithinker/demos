package demos.algorithm;

import java.util.Arrays;

public class Min_K {
	/**
	 * ��ȡ��С��k������ ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)�����ʺ�Ԫ�ط�Χ�Ƚϴ������
	 * @param num ��������
	 * @param k ��ȡԪ�ظ���
	 * @return ��Сk������ɵ�����
	 */
	public int[] min_k(int[] num, int k) {
		//����num�е������
		int max = 0;
		//��������ȡ�����
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
		}
		//��ʱ���飬����num��ÿ�������ֵĴ���
		int[] tmp = new int[max + 1];
		for (int i = 0; i < num.length; i++) {
			tmp[num[i]]++;
		}
		//������
		int[] result = new int[k];
		//tmp�����±�
		int index = 0;
		//result�����±�
		int pos = 0;
		while (pos < k) {
			while (tmp[index] != 0) {
				result[pos++] = index;
				tmp[index]--;
			}
			index++;
		}
		return result;
	}
	
	/**
	 * ���ѣ�δ��������ĵ�һ��Ԫ��
	 * @param a �����ѵ�����
	 * @return ������
	 */
	private int[] buildHeap(int[] a){
		if(a == null){
			return null;
		}
		for(int i = a.length / 2; i >= 1; i--){
			//�����ɴ����
			maxHeap(a, i, a.length);
		}
		return a;
	}
	/**
	 * �����ɴ����
	 * @param a ������������
	 * @param i ��������ʼλ��
	 * @param l ���鳤��
	 */
	private void maxHeap(int[] a, int i, int l){
		int large =  -1; 
		int left = 2 * i;
		int right = 2 * i + 1;
		
		//�ҳ���ǰ�ڵ����Һ����нϴ��Ԫ��
		if(left < l && a[i] < a[left]){
			large = left;
		}else{
			large = i;
		}
		if(right < l && a[large] < a[right]){
			large = right;
		}
		
		//���������бȵ�ǰ�ڵ������������λ�ã��ݹ齨��
		if(large != i){
			int tmp = a[i];
			a[i] = a[large];
			a[large] = tmp;
			maxHeap(a, large, l);
		}
	}

	/**
	 * ��ȡ��С��k������ ʱ�临�Ӷ�O(n * log(k))���ռ临�Ӷ�O(k)
	 * @param num ��������
	 * @param k ��ȡԪ�ظ���
	 * @return ��Сk������ɵ�����
	 */
	public int[] min_k2(int[] num, int k) {
		//�����ɰ���k + 1��Ԫ�صĶ����飬��һ��λ�ò���
		int[] heap = new int[k + 1];
		//��������
		for(int i = 1; i <= k; i++){
			heap[i] = num[i - 1];
		}
		//����
		buildHeap(heap);
		//����ʣ���Ԫ�أ��б���k��Ԫ��С�ģ��滻����һ����Ԫ��
		for(int i = k; i < num.length; i++){
			if(num[i] < heap[1]){
				heap[1] = num[i];
				maxHeap(heap, 1, k + 1);
			}
		}
		return Arrays.copyOfRange(heap, 1, k + 1);
	}
	
	/**
	 * ��ȡ��С��k������
	 * @param num ��������
	 * @param k ѡȡ���ĸ���
	 * @return ������k����С����
	 */
	public int[] min_k3(int[] num, int k){
		quickSelect(num, 0, num.length - 1, k);
		return Arrays.copyOfRange(num, 0, k);
	}
	/**
	 * ����ѡ�񣬹ؼ�������ŦԪ��ѡȡ���ʵ�ѡȡ��ŦԪ����ʹʱ�临�Ӷ�����O(n).����ǰѡȡ���ܣ�
	 * @param num ��������
	 * @param low �����д�����λ�������±�
	 * @param high �����д�����λ�������̱�
	 * @param k Ҫѡȡ���ĸ���
	 */
	private void quickSelect(int[] num, int low, int high, int k) {
		//���������λ�õ��±���ϱ�
		int low_tmp = low; 
		int high_tmp = high;
		//ѡȡ��ŦԪ���������ѡȡ�����Եõ���������ʱ�䣩
		int pivot = num[low];
		//����Ԫ��λ��
		while(low < high){
			while(low < high && num[high] >= pivot){
				high--;
			}
			//С����ŦԪ��ֵǰ��
			num[low] = num[high];
			while(low < high && num[low] < pivot){
				low++;
			}
			//������ŦԪ��ֵ����
			num[high] = num[low];
		}
		//������ŦԪ
		num[low] = pivot;
		if(low == k - 1){
			return;
		}else if(low > k -1){
			quickSelect(num, low_tmp, low - 1, k);
		}else{
			quickSelect(num, low + 1, high_tmp, k);
		}
	}

	public static void main(String[] args){
		int[] datas = new int[]{2, 34, 56, 1, 43, 6, 232, 56, 78, 2, 5, 76, 23, 56,678, 98, 75, 12, 5};
		System.out.println("Method one: ");
		int[] t = new Min_K().min_k(datas, 9);
		for(int i = 0; i < t.length; i++){
			System.out.print(t[i] + " ");
		}
		System.out.println("\nMethod two: ");
		int[] tt = new Min_K().min_k2(datas, 9);
		for(int i = 0; i < tt.length; i++){
			System.out.print(tt[i] + " ");
		}
		System.out.println("\nMethod three: ");
		int[] ttt = new Min_K().min_k3(datas, 9);
		for(int i = 0; i < ttt.length; i++){
			System.out.print(ttt[i] + " ");
		}
	}
}