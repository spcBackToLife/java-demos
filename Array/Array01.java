import java.util.Arrays;

class Student {
    public String name;

    Student(String name) {
        this.name = name;
    }
}

public class Array01 {

    public static void main(String[] args) {
        Array01 al = new Array01();
        int[] a = {1,2};
        System.out.println(Arrays.copyOfRange(a, 0, 1)[0]);
        System.out.println(Arrays.copyOfRange(a, 1, a.length)[0]);
    }

    public static void printArray(int[][] array) {
        System.out.println("----------");
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
          System.out.print("[" + array[i][0] + ", " + array[i][1] + "]");
        }
        System.out.println("]");
        System.out.println("----------");
      }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length < 2) {
            return intervals;
         }

         int [][] outIntervals = new int[intervals.length + 1][2];
         if (intervals.length == 0) {
            outIntervals[0] = newInterval;
            return outIntervals;
         }
         int intervalsLength = intervals.length;
         int currentOutIndex = 0;
         int minPoint = newInterval[0];
         int maxPoint = newInterval[1];
         int i, j;
         for(i = 0; i < intervalsLength; i ++) {
             // 找到最终定位地址
             if (intervals[i][0] > maxPoint) {
                 // 开始执行添加插入的数组
                 outIntervals[currentOutIndex++] = new int[] {minPoint, maxPoint};
                 for(j = i; j < intervalsLength; j ++) {
                     outIntervals[currentOutIndex++] = intervals[j];
                 }
                 break;
             }
 
             // 最后一个
             if((i == intervalsLength - 1) && intervals[i][1] < minPoint) {
                 // 把最后一个先放进去，在放入插入的。
                 outIntervals[currentOutIndex++] = intervals[i];
                 outIntervals[currentOutIndex++] = new int[] {minPoint, maxPoint};
                 break;
             }
             
             
             // 有交集，处理交集，并返回当前newInterval
             if (intervals[i][0] <= maxPoint && intervals[i][1] >= minPoint) {
                 if (minPoint > intervals[i][0]) {
                     minPoint = intervals[i][0];
                 }
                 
                 if(maxPoint < intervals[i][1]) {
                     maxPoint = intervals[i][1];
                 }
                 
                 if (i == intervalsLength - 1) {
                     // 如果是最后一个，且有交集，则需要加入
                     outIntervals[currentOutIndex++] = new int[] {minPoint, maxPoint};
                 }
                 
             } else {
                 // 没有交集，也没到终点，直接放入.
                 System.out.println("intervals[i]:" + intervals[i]);
                 System.out.println("currentOutIndex++:" + currentOutIndex + ":");
                 outIntervals[currentOutIndex++] = intervals[i];
 
             }
         }
        //  int[][] out = new int[currentOutIndex + 1][2];
        //  for(i = 0; i < currentOutIndex + 1; i ++) {
        //      out[i] = outIntervals[i];
        //  }
         return outIntervals;
    }
    
}