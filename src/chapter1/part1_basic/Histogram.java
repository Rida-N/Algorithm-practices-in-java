package chapter1.part1_basic;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class Histogram {
    private int[] a;
    private int m_size;
    private int[] m;
    Random random = new Random();

    public Histogram(int a_size, int m_size_input) {
        m_size = m_size_input;
        a = new int[a_size];
        StdOut.println("array a is : ");
        for (int i = 0; i < a_size; i++) {
            a[i] = random.nextInt(m_size + 1);
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public void createHistogram() {
        StdOut.println("array m is : ");
        int sum_all = 0;
        m = new int[m_size];
        for (int i = 0; i < m_size; i++) {
            int sum = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    sum++;
                }
            }
            m[i] = sum;
            sum_all += m[i];
            StdOut.print(m[i] + " ");
        }
        StdOut.println();
        StdOut.print("sum of m elements is : " + sum_all);
    }

    public static void main(String[] args) {
        Histogram ht = new Histogram(6, 9);
        ht.createHistogram();
    }
}
