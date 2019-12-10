import java.util.Arrays;

public class MySort {
  
  public static void main(String[] args) {
    MySort sort = new MySort();
    int[] array = {1,5,11,2,3,5,1,2,345,234,1,1,5,41,32};

    System.out.println("冒泡开始排序");
    sort.bubleSort(array);
    sort.printArray(array);

    System.out.println("选择排序");
    sort.selectSort(array);
    sort.printArray(array);
    
    System.out.println("插入排序");
    sort.insertSort(array);
    sort.printArray(array);

    System.out.println("shell排序");
    sort.shellSort(array);
    sort.printArray(array);

    System.out.println("归并排序");
    sort.mergeSort(array);
    sort.printArray(array);

    System.out.println("快速排序");
    sort.quickSort(array, 0, array.length - 1);
    sort.printArray(array);
  }

public int[] quickSort(int[] array, int lowIndex, int highIndex) {
  if (lowIndex < highIndex) {
    int i = lowIndex;
    int j = highIndex;
    int temp = array[i];
    while (i < j) {
      while(i < j && array[j] > temp)
        j--; // 一直比他大，就往前走，直到第一个不大了就开始交换。
      if (i < j)
        array[i++] = array[j];
      while(i < j && array[i] < temp)
        i++; // 直到第一个不小了，就交换到尾巴上去。
      if (i < j)
        array[j--] = array[i];
    }
    array[i] = temp;
    quickSort(array, lowIndex, i - 1);
    quickSort(array, i + 1, highIndex);
  }
  return array;
}


  public int[] mergeSort(int[] array) {
    int length = array.length;
    if (array.length < 2) {
      return array;
    }
    int mid = length / 2;
    return merge(mergeSort(Arrays.copyOfRange(array, 0, mid)), mergeSort(Arrays.copyOfRange(array, mid, length)));
  }

  public int[] merge(int[] leftArray, int[] rightArray) {
    int leftLength = leftArray.length;
    int rightLength = rightArray.length;
    int outLength = leftLength + rightLength;
    int out[] = new int[outLength];
    int i = 0;
    int j = 0;
    int k = 0;
    while(k < outLength) {
      if (i >= leftLength) {
        out[k++] = rightArray[j++];
      } else if (j >= rightLength) {
        out[k++] = leftArray[i++];
      } else if (leftArray[i] > rightArray[j]) {
        out[k++] = rightArray[j++];
      } else {
        out[k++] = leftArray[i++];
      }
    }
    return out;
  }

  // 插入排序
  public int[] insertSort(int[] array) {
    int arrLength = array.length;
    int temp;
    int j;
    for (int i = 1; i < arrLength; i ++) {
      // 把i与已排序数组
      if(array[i] < array[i - 1]) {
        temp = array[i];
        for (j = i - 1; j >= 0 && temp < array[j]; j --) {
          array[j + 1] = array[j];
        }
        array[j]  = temp;
      }
    }
    return array;
  }

  // 选择排序
  public int[] selectSort(int[] array) {
    int arrLength = array.length;
    int temp;
    int minIndex;
    for (int i = 0; i < arrLength - 1; i ++) {
      minIndex = i;
      for (int j = i + 1; j < arrLength - 1; j++) {
        if (array[minIndex] > array[j]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        temp = array[minIndex];
        array[minIndex] = array[i];
        array[i] = temp;
      }
    }
    return array;
  }

  // 冒泡排序
  public int[] bubleSort(int[] array) {
    int arrLength = array.length;
    int temp;
    // 从第一个开始冒泡，从小到大
    for (int i = 0; i < arrLength - 1; i ++) 
      for (int j = 0; j < arrLength - 1 - i; j ++) {
        if (array[j] > array[j + 1]) {
          temp = array[j + 1];
          array[j + 1] = array[j];
          array[j] = temp;
        }
      }
    return array;
  }

  // 希尔排序
  public int[] shellSort(int[] array) {
    int arrLength = array.length;
    int i, j;
    int temp;
    // 设定shell公式.
    int h = 1;
    // System.out.println("(int)(Math.floor(Math.sqrt(arrLength))):" + (int)(Math.floor(Math.sqrt(arrLength))));
    // while(h < (int)(Math.floor(Math.sqrt(arrLength)))){
    //   h = (int)Math.pow(2, h) - 1;
    // }
    while(h < arrLength / 3) {
      h = h * 3 + 1;
    }
    System.out.println("h=" + h);
    // 步长至少为1
    while(h >= 1) {
      for (i = h; i < arrLength; i += h) {
        temp = array[i];
        if (array[i] < array[i - h]) {
          for (j = i - h; j >= 0 && temp < array[j]; j -=h) {
            array[j + h] = array[j];
          }
          array[j] = temp;
        }
      }
      // h = (int)(Math.floor(Math.sqrt((h + 1))));
      h = h / 3;
      System.out.println("h:" + h);
    }
    return array;
  }

  private void printArray(int[] array) {
    System.out.println("----------");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("");
    System.out.println("----------");
  }
}