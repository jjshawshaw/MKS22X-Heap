public class MyHeap{
  int[] data;
  private static void pushDown(int[] data, int size, int idx){
    int child1 = idx * 2 + 1;
    if (child1 > size) return;
    if (data[idx] < data[child1]){
      swap(data, idx, child1);
      pushDown(data, size, child1);
    }
    int child2 = idx * 2 + 2;
    if (data[idx] < data[child2]){
      swap(data, idx, child2);
      pushDown(data, size, child2);
    }
  }

  private static void pushUp(int[]data,int idx){
    if (idx == 0) return;
    int parent = (idx % 2 == 0) ? (idx / 2) - 1 : idx / 2;
    if (data[parent] < data[idx]){
      swap(data, idx, parent);
      pushUp(data, parent);
    }
  }


  public static void swap(int[] data, int idx1, int idx2){
    int temp = data[idx1];
    data[idx1] = data[idx2];
    data[idx2] = temp;
  }
}
