import java.util.*;
public class MyHeap{
  private static void pushDown(int[] data, int size, int idx){
    int c1 = idx * 2 + 1;
    int c2 = idx * 2 + 2;
    if (c1 >= size) return;
    int larger = (c2 < size && data[c2] > data[c1]) ? c2 : c1;
    if (data[larger] > data[idx]){
      swap(data, larger, idx);
      pushDown(data, size, larger);
    }
  }

  private static void pushUp(int[] data, int size, int idx){
    int p = (idx % 2 == 0) ? idx / 2 - 1 : idx / 2;
    if (idx == 0) return;
    if (data[p] < data[idx]){
      swap(data, p, idx);
      pushUp(data, size, p);
      return;
    }
  }

  private static void heapify(int[] data){
    for (int i = 0; i < data.length; i++){
      pushUp(data, data.length, i);
    }
  }


  public static void heapsort(int[] data){
    heapify(data);
    for (int i = data.length - 1; i >= 0; i--){
      swap(data,0,i);
      pushDown(data, i, 0);
    }
  }

  private static void swap(int[] data, int idx1,int idx2){
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
  }
}
