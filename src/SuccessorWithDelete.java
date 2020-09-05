import edu.princeton.cs.algs4.StdOut;

public class SuccessorWithDelete {
    private boolean[] flag;
    private FindLargest fl;
    private int N;

    public SuccessorWithDelete(int N) {
        this.N = N;
        flag = new boolean[N];
        for (int i = 0; i < N; i++)
            flag[i] = true;
        fl = new FindLargest(N);
    }

    public void remove(int x) {
        if (flag[x]) {
            flag[x] = false;
            if (x > 0 && !flag[x - 1]) {
                fl.union(x, x - 1);
            }
            if (x < N - 1 && !flag[x + 1]) {
                fl.union(x, x + 1);
            }
        }
    }

    public int findSuccessor(int x) {
        if (flag[x])
            return x;
        else {
            int successor = fl.find(x) + 1;
            if (successor >= N) {
                StdOut.println("No successor for " + x + " !");
                return -1;
            } else {
                return successor;
            }
        }
    }

    public static void main(String[] args) {
        int arrayLength = 10;
        SuccessorWithDelete sd = new SuccessorWithDelete(arrayLength);
        int[] removeList = {3, 6, 7, 4, 9, 5, 2};
        for (int i = 0; i < removeList.length; i++) {
            sd.remove(removeList[i]);
            StdOut.print("In array : ");
            for (int j = 0; j < arrayLength; j++) {
                if (sd.flag[j])
                    StdOut.print(" " + j + "   ");
            }
            int successor = sd.findSuccessor(removeList[i]);
            if (successor != -1)
                StdOut.println("Successor for " + removeList[i] + " is : " + successor);
        }
    }
}
