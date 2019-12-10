import java.util.Iterator;

public class MyArrayList<T extends Object> implements Iterable<T> {
  private static final int DEFAULT_CAPACITY = 10;
  private int size = 0;
  private Object[] theItems = new Object[DEFAULT_CAPACITY];

  public static void main(String[] args) {
    MyArrayList<Integer> arr = new MyArrayList<Integer>();
    // isEmpty尝试
    if (arr.isEmpty()) {
      System.out.println("数组为空");
    }
    System.out.println("添加元素到末尾");
    arr.push(3);
    arr.push(4);
    arr.push(7);
    arr.printArray();

    System.out.println("插入元素到中间");
    arr.insert(0, 1);
    arr.insert(10, 1);
    arr.insert(2, 5);
    arr.printArray();

    System.out.println("获取某个元素");
    System.out.println(arr.at(5));
    System.out.println(arr.at(3));

    System.out.println("pop一个元素");
    System.out.println(arr.pop());
    arr.printArray();

    System.out.println("删除一个元素");
    arr.delete(2);
    arr.printArray();

    System.out.println("查找值为某个数的元素");
    System.out.println(arr.find(3));
    System.out.println(arr.find(20));

    System.out.println("删除值为3的元素");
    arr.push(3);
    arr.push(1);
    arr.push(101);
    arr.push(3);
    arr.printArray();
    arr.remove(3);
    arr.printArray();
  }

  private void printArray() {
    System.out.println("----------");
    for (int i = 0; i < size; i++) {
      System.out.print(this.theItems[i] + " ");
    }
    System.out.println("");
    System.out.println("----------");
  }

  private int reSize() {
    int currentSize = size() * 2 + 1;
    Object[] newItems = new Object[currentSize];
    for (int i = 0; i < theItems.length; i++) {
      newItems[i] = theItems[i];
    }
    this.theItems = newItems;
    return currentSize;
  }

  public int size() {
    return size;
  }

  public int setSize(int size) {
    this.size = size;
    return size;
  }

  public int capacity() {
    return theItems.length;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Object at(int index) {
    if (index >= size || index < 0) {
      return null;
    }
    return this.theItems[index];
  }

  public Object prepend(T item) {
    return this.insert(0, item);
  }

  public Object pop() {
    Object item = this.theItems[size - 1];
    this.theItems[size--] = null;
    return item;
  }

  public Object insert(int index, T item) {
    if (index > size - 1) {
      System.out.println("超出范围，会插在数组最后");
      index = size;
    }
    if (size + 1 > capacity()) {
      reSize();
    }
    this.doInsert(index, item);
    return item;
  }

  public Object doInsert(int index, T item) {
    for (int i = size; i > index; i--) {
      this.theItems[i] = this.theItems[i - 1];
    }
    this.theItems[index] = item;
    size++;
    return item;
  }

  public Object push(T item) {
    if (size + 1 > capacity()) {
      reSize();
    }
    return doPush(item);
  }

  public Object doPush(T item) {
    this.theItems[size++] = item;
    return item;
  }

  public void delete(int index) {
    if (index > size - 1 || index < 0) {
      return;
    }
    // 移动元素
    for (int i = index; i < size - 1; i++) {
      this.theItems[i] = this.theItems[i + 1];
    }
    // 清空最后一位
    this.theItems[size] = null;
    // 减小数组大小
    size--;
  }

  public int find(T item) {
    int itemIndex = -1;
    for (int i = 0; i < size - 1; i++) {
      if (this.theItems[i] == item) {
        itemIndex = i;
        break;
      }
    }
    return itemIndex;
  }

  public int[] remove(T item) {
    String removeIndexs = "";
    int removeCount = 0;
    for (int i = 0; i < size; i++) {
      if (this.theItems[i] == item) {
        removeIndexs += i + ",";
        removeCount++;
      } else {
        // 前移
        this.theItems[i - removeCount] = this.theItems[i];
      }
    }
    this.setSize(size - removeCount);
    // 清除末尾的元素
    for (int i = size; i < size + removeCount; i++) {
      this.theItems[i] = null;
    }
    removeIndexs = removeIndexs.substring(0, removeIndexs.length() - 1);
    return stringArryToIntArry(removeIndexs.split(","));
  }

  private int[] stringArryToIntArry(String[] array) {
    int[] intArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      intArray[i] = Integer.parseInt(array[i]);
    }
    return intArray;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Auto-generated method stub
    return new MyArrayListIterator();
  }

  private class MyArrayListIterator implements Iterator<T> {
    private int current = 0; // 迭代器从0开始

    @Override
    public boolean hasNext() {
      return current < size();
    }

    @Override
    public T next() {
      // TODO Auto-generated method stub
      return null;
    }

  }
}
