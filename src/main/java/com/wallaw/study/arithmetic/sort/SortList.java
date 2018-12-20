package com.wallaw.study.arithmetic.sort;

import java.util.Random;

/**
 * 排序总览
 * 
 * @author beck
 * @since 2017-11-1 10:46:47
 */
public class SortList {

	public static void main(String[] args) {
		 int[] a = createArray(30);
	     int[] b = new int[30];
	     int[] c = new int[30];
	     int[] d = new int[30];
	     int[] e = new int[30];
	     int[] f = new int[30];
	     int[] g = new int[30];
	     System.arraycopy(a, 0, b, 0, 30);
	     System.arraycopy(a, 0, c, 0, 30);
	     System.arraycopy(a, 0, d, 0, 30);
	     System.arraycopy(a, 0, e, 0, 30);
	     System.arraycopy(a, 0, f, 0, 30);
	     System.arraycopy(a, 0, g, 0, 30);
	    System.out.println("=======================================");
	    insertSort(a);
	    System.out.println("=======================================");
		shellSort(b);
		System.out.println("=======================================");
		selectOrder(c);
		System.out.println("=======================================");
		heapSort(d);
		System.out.println("=======================================");
		//e = new int[]{21,28,19,18,56,17};
		printQuickSort(e);
		System.out.println("=======================================");
		mergeSort(f);
		System.out.println("=======================================");
		bubbleSort(g);
	}
	
	  //随机动态产生数组
    public static int[] createArray(int len){
     int[] test = new int[len];
     Random random = new Random();
     for(int i=0;i<test.length;i++){
        test[i] = random.nextInt(1000);
     }
     System.out.println("最初生成的随机数数组内容为：");
     printArray(test);
     return test;
    }
    
    /**
     * 插入排序：待插入的数据，插入到一个有序的数组中
     * @param sorts
     */
    public static void insertSort(int[] sorts){
    	System.out.println("插入排序之前：");
		printArray(sorts);
		int j=0;
		if (sorts != null && sorts.length > 0){
			for (int i = 1; i < sorts.length; i++){
				int temp = sorts[i];
				for (j = i; j >0 && temp < sorts[j-1]; j--){
					sorts[j] = sorts[j-1];
				}
				sorts[j] = temp;
			}
		}
		
		System.out.println("插入排序之前：");
		printArray(sorts);
	}

	/**
	 * 希尔排序： 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组， 每组中记录的下标相差d.对每组中全部元素进行直
	 * 接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。 当增量减到1时，进行直接插入排序后，排序完成。
	 * 
	 * @param a
	 */
	public static void shellSort(int a[]) {
		System.out.println("希尔排序之前：");
		printArray(a);
		int n = a.length;
		int d = n % 2 == 0 ? n / 2 : n / 2 + 1;
		while (d > 1) {
			for (int i = 0; i < i + d && d + i < n; i++) {
				int j = i + d;
				int temp = a[j];
				for (; j > 0 && temp < a[j - 1]; j--) {
					a[j] = a[j - 1];
				}
				a[j] = temp;
			}
			d = d % 2 == 0 ? d / 2 : d / 2 + 1;
		}
		System.out.println("希尔排序之后：");
		printArray(a);
	}
	
	/**
	 * 选择排序：选定某位置上的值，依次遍历比较，将较大的/较小的替换
	 * @param a
	 */
	public static void selectOrder(int[] a) {
		int temp;
		System.out.println("选择排序之前：");
		printArray(a);
		long start = System.nanoTime();
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i+1; j < a.length; j++) {
				if (a[i] > a[j]) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		System.out.println("选择排序之后，耗时：" + (System.nanoTime() - start) + "nano");
		printArray(a);
	}
	//==================冒泡排序结束====================//
	/**
	 * 冒泡排序：相邻两个进行比较，遍历至末尾，并得到最大/最小
	 * @param a
	 */
	public static void bubbleSort(int[] a){
		System.out.println("冒泡排序前：");
		printArray(a);
		int temp;
		long start=System.nanoTime();
		for(int i=0;i<a.length-1;i++){
			for(int j=0;j<a.length-i-1;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1] =temp;
				}
			}
		}
		System.out.println("冒泡排序，耗时："+(System.nanoTime()-start)+"nano");
		printArray(a);
	}
	//==================冒泡排序结束====================//

	//=================堆排序（顶堆）=====================//
	public static void heapSort(int[] a){
		System.out.println("堆构建前：");
		printArray(a);
		heap_create(a, a.length);
		System.out.println("堆构建后：");
		printArray(a);
		heap_sort(a, a.length);
		System.out.println("堆排序后：");
		printArray(a);
	}
	
	static void heap_create(int arr[], int n)
	{
	    int i;      // from bottom to top
	    //for(i=(n>>1)-1; i>-1; heap_adjust(arr,i--,n));
	    for(i=(n>>1)-1; i>-1; i--){ // ">>" 右移动值变小，代表节点坐标变小，top的节点坐标最小
			heap_adjust(arr,i,n);
		}
	}

	/**
	 * 移位的一个好处是，可以让结果除于2并得到一个整除结果,此方法是构建大顶推
	 * @param arr
	 * @param p
	 * @param n
	 */
	static void heap_adjust(int arr[], int p, int n)
	{   // c - children, p - parent
	    int maxid=p, temp;
	    // heap_adjust for maxtop, from top to bottom
	    while(p<(n>>1)){
	        if ((p<<1)+1<n && arr[(p<<1)+1]>arr[maxid])
	            maxid = (p<<1)+1;
	        if ((p<<1)+2<n && arr[(p<<1)+2]>arr[maxid])
	            maxid = (p<<1)+2;
	        if (maxid == p) break;
	        // swap arr[maxid] and arr[p]
	        temp = arr[maxid];
	        arr[maxid] = arr[p];
	        arr[p] = temp;
	        p = maxid;
	    }
	}   // Time O(logn)

	/**
	 * Heap Sort - ascending order
 	 */
	static void heap_sort(int arr[], int n)
	{
	    int i, temp;
	    // init maxtop heap, using method 2 (from bottom to top)
	    //for (i=(n>>1)-1; i>-1; heap_adjust(arr,i--,n));
	    for (i=n-1; i>0; --i){
	        // mv heap top to end (heap top is max)
	        temp = arr[0];
	        arr[0] = arr[i];
	        arr[i] = temp;
	        heap_adjust(arr,0,i);
	    }
	}   // Time O(nlogn)
	
	
	//==================堆排序结束====================//
	//==================快速排序开始====================//
	/**
	 * 将待排序数组，与某参考值进行比较，小的放左边，大的方右边，对左右子集再做类似的比较，结果即所得
	 * @param a
	 */
	public static void printQuickSort(int[] a){
		System.out.println("快速排序前：");
		printArray(a);
		int[] b = new int[a.length];
		System.arraycopy(a, 0, b, 0, a.length);
		printArray(b);
		quickSort1(a, 0, a.length);
		System.out.println("快速排序后a：");
		printArray(a);
		System.out.println("快速排序后b：");
		quickSort2(b, 0, b.length - 1);
		printArray(b);
	}

	public static void quickSort2(int[] b, int low, int high){
		int sign = b[low], signIndex = low, temp;
		int start = low, end = high;
		while(low < high){
			// 高助手，从左往右遍历
			while (low < high  && b[high] >= sign){
				high--;
			}
			// 说明sign较大
			b[low] = b[high];
			signIndex = high;

			// 抵助手从左向右比较
			while (low < high && b[low] < sign){
				low++;
			}
			//找到后赋值给高助手当前所在位置
			b[high]=b[low];
			signIndex=low;//记录当前助手位置

			//直到循环结束  -->低助手和高助手重叠 就把基准数赋到当前中轴重叠位置
			b[signIndex]=sign;
			/*if (signIndex < high && b[high] < sign){
				temp = b[high];
				b[high] = sign;
				b[signIndex] = temp;
				signIndex = high;
				high--;
			}
			if (signIndex > low && b[low] > sign){
				temp = b[low];
				b[low] = sign;
				b[signIndex] = temp;
				signIndex = low;
				low++;
			}*/
		}

		if (start < signIndex-1) {
			quickSort2(b, start, signIndex - 1);
		}

		if(signIndex+1 < end){
			quickSort2(b, signIndex + 1, end);
		}
	}


	public static void quickSort1(int[] a, int start, int end){
		if (end - start > 1){
			int index = start;
			int i = index + 1, temp = a[index];
			for (; i<end; i++){
				if (a[i] < temp){
					a[index++] = a[i];
					for (int j=i;j>index;j--){
						a[j] = a[j-1];
					}
					a[index] = temp;
				}
			}
			//排序左边
			quickSort1(a, start, index);
			//排序右边
			if (index + 1 < end){
				quickSort1(a, index + 1, end);
			}
		}
	}

	//==================快速排序结束====================//

	//==================归并排序结束====================//

	/**
	 * 将待排序数组切分2组（再merge合并），递归将每一组再分
	 * @param a
	 */
	public static void mergeSort(int[] a){
		System.out.println("归并排序前：");
		printArray(a);
		sort(a, 0, a.length - 1);
		System.out.println("归并排序前：");
		printArray(a);

	}
	/**
	 *
	 * @param data 待排序数组
	 * @param left 数组起始位置
	 * @param right 数组结束位置
	 */
	public static void sort(int[] data, int left, int right) {
		if (left < right) {//表明可以继续拆分
			// 找出中间索引
			int center = (left + right) / 2;
			// 对左边数组进行递归
			sort(data, left, center);
			// 对右边数组进行递归
			sort(data, center + 1, right);
			// 合并
			merge(data, left, center, right);

		}
	}
	/**
	 *
	 * @param data 排序完的原数组
	 * @param left 起始位置
	 * @param center 中间位置
	 * @param right  结束位置
	 */
	public static void merge(int[] data, int left, int center, int right) {
		int[] tmpArr = new int[data.length];//中间临时数组
		int mid = center + 1, start = left;
		// temp记录中间数组的索引 -->就是合并这两个数组的大数组的索引
		int temp = left;
		while (left <= center && mid <= right) {
			// 从两个数组中取出最小的放入中间数组
			if (data[left] <= data[mid]) {
				tmpArr[temp] = data[left];
				left++;
				temp++;
			} else {
				tmpArr[temp] = data[mid];
				mid++;
				temp++;
			}
		}
		// 剩余部分依次放入中间数组(见上面的合并图解)
		while (mid <= right) {
			tmpArr[temp] = data[mid];
			mid++;
			temp++;

		}
		while (left <= center) {
			tmpArr[temp] = data[left];
			left++;
			temp++;
		}
		// 将中间数组中的内容复制回原数组
		for(int i=start;i<=right;i++){
			data[i]=tmpArr[i];
		}
	}
	//==================归并排序结束====================//

	private static void printArray(int[] a) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i] + ",");
		}
		System.out.println(sb.substring(0, sb.length() - 1));
	}

}
