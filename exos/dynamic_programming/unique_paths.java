
// https://leetcode.com/problems/unique-paths/description/

// There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
// The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
// The robot can only move either down or right at any point in time.

// Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

// The test cases are generated so that the answer will be less than or equal to 2 * 109.

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class unique_paths {

  public static class Pair<K, V> {
    private K left;
    private V right;

    public Pair(K left, V right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Pair) {
        Pair<K, V> pair = (Pair<K, V>) obj;

        return this.left.equals(pair.left) && this.right.equals(pair.right);
      }

      return false;
    }

    @Override
    public int hashCode() {
      return this.left.hashCode() + this.right.hashCode();
    }

    @Override
    public String toString() {
      return this.left + "-" + this.right;
    }
  }

  public static void main(String[] args) {
    // int nPathsNaive = uniquePathsNaive(3, 2);
    // System.out.println(nPathsNaive);

    // need to work with BigIntegers for 100x100 grid
    Map<Pair<Integer, Integer>, BigInteger> map = new HashMap<>();
    BigInteger nPathsMemo = uniquePathsMemoization(3, 2, map);
    System.out.println(nPathsMemo); // expected 3

    nPathsMemo = uniquePathsMemoization(3, 7, map);
    System.out.println(map);
    System.out.println(nPathsMemo); // expected 28

    nPathsMemo = uniquePathsMemoization(100, 100, map);
    System.out.println(nPathsMemo); // expected 28

    // result for 100x100
    BigInteger big = new BigInteger("22750883079422934966181954039568885395604168260154104734000");
    System.out.println(big.bitLength());
    System.out.println(Integer.MAX_VALUE);
  }

  public static int uniquePathsNaive(int m, int n) {
    if (m == 1 || n == 1) return 1;

    return uniquePathsNaive(m - 1, n) + uniquePathsNaive(m, n - 1);
  }

  public static BigInteger uniquePathsMemoization(int m, int n, Map<Pair<Integer, Integer>, BigInteger> memo) {
    if (m == 1 || n == 1) return BigInteger.ONE;

    Pair<Integer, Integer> pair = new Pair<Integer,Integer>(m, n);
    if (memo.containsKey(pair)) return memo.get(pair);

    BigInteger res = uniquePathsMemoization(m - 1, n, memo).add(uniquePathsMemoization(m, n - 1, memo));
    memo.put(pair, res);

    return res;
  }
}