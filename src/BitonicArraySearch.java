/**
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers. Write a program
 * that, given a bitonic array of nn distinct integer values, determines whether
 * a given integer is in the array.
 * 分析：如果不要求返回数组的话，就直接先把数组排序，然后就用BinarySearch进行搜索即可。
 * 但是这个显然过于简单，题目应该是默认保存这个Bitonic Array的双调结构。 所以本方法也是基于BitonicArray的顺序不变的情况下进行搜索。
 */

import edu.princeton.cs.algs4.StdOut;

public class BitonicArraySearch {
    private int[] id;
    private int bitonicPoint;

    public BitonicArraySearch(int[] list) {
        id = list;
        findBitonicPoint();
    }

    public void findBitonicPoint() {
        int le = 0;
        int ri = id.length - 1;
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (mid >= id.length - 1 || mid == 0) {
                bitonicPoint = mid;
                return;
            }
            if (id[mid - 1] < id[mid] && id[mid] < id[mid + 1])
                le = mid + 1;
            else if (id[mid - 1] > id[mid] && id[mid] > id[mid + 1])
                ri = mid - 1;
            else {
                bitonicPoint = mid;
                break;
            }
        }
    }

    public int searchAscSeq(int x) {
        int le = 0;
        int ri = bitonicPoint;
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (id[mid] > x)
                ri = mid - 1;
            else if (id[mid] < x)
                le = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public int searchDescSeq(int x) {
        int le = bitonicPoint + 1;
        int ri = id.length - 1;
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (id[mid] > x)
                le = mid + 1;
            else if (id[mid] < x)
                ri = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public int search(int x) {
        int searchAscResult = searchAscSeq(x);
        int searchDescResult = searchDescSeq(x);
        if (searchAscResult != -1)
            return searchAscResult;
        else
            return searchDescResult;
    }

    public static void main(String[] args) {
        int[] list = {2, 5, 6, 8, 9, 12, 43, 87, 95, 654, 877, 955, 1444, 654, 568, 42, 8, 5, 2, 0};
        BitonicArraySearch ba = new BitonicArraySearch(list);
        int result = ba.search(568);
        StdOut.println("Search result : " + result);
    }
}
