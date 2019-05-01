import java.util.*;
public class MyHeap{
  private static void pushDown(int[] data, int size, int idx){
    //System.out.println("pushing down " + data[idx] + " at index " + idx);
    //System.out.println(Arrays.toString(data));
    int c1 = idx * 2 + 1;
    if (c1 >= size) return;
    if (data[c1] > data[idx]){
      //System.out.println("child1 " + c1 + " is larger");
      swap(data, c1, idx);
      pushDown(data, size, c1);
      pushUp(data, size, idx);
      return;
    }
    int c2 = idx * 2 + 2;
    if (c2 >= size) return;
    if (data[c2] > data[idx]){
      //System.out.println("child2 " + c2 + " is larger");
      swap(data, c2, idx);
      pushDown(data, size, c2);
      pushUp(data, size, idx);
    }
  }

  private static void pushUp(int[] data, int size, int idx){
    //System.out.println("pushing up " + data[idx] + " at index " + idx);
    //System.out.println(Arrays.toString(data));
    int p = (idx % 2 == 0) ? idx / 2 - 1 : idx / 2;
    if (idx == 0){
      pushDown(data, size, idx);
    }
    if (p < 0) return;
    if (data[p] < data[idx]){
      swap(data, p, idx);
      pushUp(data, size, p);
      pushDown(data, size, idx);
    }
  }

  private static void heapify(int[] data){
    //System.out.println("heapifying");
    for (int i = 0; i < data.length; i++){
      //System.out.println("index " + i + "\n");
      pushUp(data, data.length, i);
    }
  }

  private static void heapsort(int[] data){
    heapify(data);
    int size = data.length - 1;
    while (size > 0){
      //System.out.println("\n" + size + " " + Arrays.toString(data));
      swap(data, 0, size);
      //System.out.println("swapping " + data[0] + " with " + data[size]);
      //System.out.println("" + Arrays.toString(data));
      //System.out.println("pushing down " + data[0] + "\n");
      pushDown(data, size--, 0);
    }
  }

  private static void swap(int[] data, int idx1,int idx2){
    //System.out.println("swapping " + data[idx1] + " with " + data[idx2]);
    int temp = data[idx1];
    data[idx1] = data[idx2];
    data[idx2] = temp;
  }

  public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tmerge /builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        MyHeap.heapsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
  // int[] d = new int[]{};
  // heapsort(d);
  // System.out.println(Arrays.toString(d));
}
}
