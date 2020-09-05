/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n)
 * and plenty of eggs. An egg breaks if it is dropped from floor T or higher and
 * does not break otherwise. Your goal is to devise a strategy to determine the
 * value of T given the following limitations on the number of eggs and tosses:
 * <p>
 * Version 0: 1 egg, <= T tosses.
 * <p>
 * Version 1: ∼1lgn eggs and ∼1lgn tosses.
 * <p>
 * Version 2: ∼lgT eggs and ∼2lgT tosses.
 * <p>
 * Version 3: 2 eggs and ∼2*(the square of n) tosses.
 * <p>
 * Version 4: 2 eggs and <= c*(the square of T) tosses for some fixed constant
 * c.
 */

import edu.princeton.cs.algs4.StdOut;

public class EggDrop {
    private int N; // number of floor
    private boolean[] willBreak; // The egg break info list of floor according to index
    public int eggBroken;
    public int tosses;
    public int currentStep;

    public EggDrop(int N, int breakPoint) {
        this.N = N;
        willBreak = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (i < breakPoint - 1) {
                willBreak[i] = false;
            } else {
                willBreak[i] = true;
            }
        }
    }

    private int lg(int x) {
        // return int number under or euqle to lg(x);
        return (int) (Math.log(x) / Math.log(2));
    }

    // 分析：只有一个鸡蛋，所以只可以碎一次，从第一个台阶从下往上扔，碎了为止
    public boolean version_0() {
        eggBroken = 0;
        tosses = 0;
        currentStep = 0;
        while (eggBroken < 1 && currentStep < N) {
            tosses++;
            if (!willBreak[++currentStep - 1])
                continue;
            else {
                eggBroken++;
                return true;
            }
        }
        return false;
    }

    // 用BinarySearch, 最坏情况为lgN
    public boolean version_1() {
        int lgN = lg(N);
        eggBroken = 0;
        tosses = 0;
        currentStep = 0;
        int le = 1;
        int ri = N;
        while (le <= ri && eggBroken < lgN && tosses <= lgN) {
            tosses++;
            currentStep = (le + ri) / 2;
            if (!willBreak[currentStep - 1])
                le = currentStep + 1;
            else {
                eggBroken++;
                ri = currentStep - 1;
            }
        }
        if (le > N || le < 1)
            return false;
        else {
            currentStep = le;
            return true;
        }
    }

    private static void printResult(boolean founded, EggDrop eg) {
        StdOut.println(!founded ? "Failed to find T !"
                : "T is at " + eg.currentStep + "th floor. Total tosses : " + eg.tosses + " times, eggs broken : "
                + eg.eggBroken);
    }

    public static void main(String[] args) {
        EggDrop eg = new EggDrop(50, 24);
        // test lg(N)
        // StdOut.println("lg 10 = " + eg.lg(50));

        // version_0 test
        // printResult(eg.version_0(), eg);
        // version_1 test
        printResult(eg.version_1(), eg);
    }
}
