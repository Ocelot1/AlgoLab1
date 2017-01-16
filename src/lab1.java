/*
 * Framework file for the first lab of CS 2860: Algorithms and Complexity I. Complete the functions
 * below (or remove the parts you don't want to test).
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class lab1 {

  // Complete these classes

  // 1a. Iterative factorial function
  static int factorialIterative(int n) {
    int fact = 1;
    for (int i = n; i > 0; i--) {
      fact *= i;
    }
    return fact;
  }

  // 1b. Recursive factorial function
  static int factorialRecursive(int n) {
    if (n == 1)
      return n;
    else {
      return n * factorialRecursive(n - 1);
    }
  }

  // 2. Merge and mergesort

  // Merge: Exactly as shown in class.
  //
  // The part array[low] ... array[end1] is sorted
  // The part array[end1+1] ... array[end2] is sorted
  // The task is to make all of array[low] ... array[end2] sorted
  //
  // Example:
  // merge on array [0,1, 3,6,8, 2,4,7, 11,9] with low=2, end1=4, end2=7
  // should leave [0,1, 2,3,4, 6,7,8, 11,9] in the array when it returns.
  // Note that 11 and 9 are not moved since they come after end2.
  static void merge(int[] array, int low, int end1, int end2) {
    int i = low, j = end1 + 1;
    int temp[] = new int[(end2 + 1) - low];

    for (int x = 0; x <= temp.length - 1; x++) {
      if (i == end1 + 1) {
        temp[x] = array[j];
        j++;
      } else if (j == end2 + 1) {
        temp[x] = array[i];
        i++;
      } else if (array[i] <= array[j]) {
        temp[x] = array[i];
        i++;
      } else if (array[i] > array[j]) {
        temp[x] = array[j];
        j++;
      }
    }
    for (int x = 0; x < temp.length; x++) {
      array[low + x] = temp[x];
    }
    for (int x = 0; x < array.length; x++) {
      // System.out.println(array[x]);
    }
  }


  static int a = 0;

  // Mergesort, using merge.

  static void mergesort(int[] array, int low, int high) {

    if (low < high) {
      int mid = (high + low) / 2;
      mergesort(array, low, mid);
      mergesort(array, mid + 1, high);
      merge(array, low, mid, high);
      }
  }

  // 3a. "Normal" (slow) twoSum algorithm.
  // Should test whether there are two numbers a, b int
  // the list of numbers such that a+b=target.
  // If so, it should print them to the screen and return true.
  // If not, it should return false.

  static boolean twoSum(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        if (numbers[i] + numbers[j] == target) {
          System.out.println(numbers[i] + " + " + numbers[j] + " = " + target);
          return true;
        }
      }
    }
    return false;
  }

  // 3b. Faster twoSum algorithm. Same question as 3a, except the
  // solution should be faster (O(n log n) or better).

  static boolean fastTwoSum(int[] numbers, int target) {
    HashSet<Integer> hSet = new HashSet<Integer>();
    for (int i : numbers){
      hSet.add(i);
    }
    for (int i : hSet){
      if (hSet.contains(target - i)){
        System.out.println(i + " + " + (target - i) + " = " + target);
        return true;
      }
    }
    return false;
  }

  // Framework code below here.

  static void generateSorted(int[] array, int n) {
    int prev = 0;
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      // generate only even numbers (for testing)
      prev = prev + 2 * rand.nextInt(10);
      array[i] = prev;
    }
  }

  static void generateRandom(int[] array, int n) {
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      // generate only even numbers (for testing)
      array[i] = 2 * rand.nextInt(n * n);
    }
  }

  public static void main(String[] args) {
    int n = 10;
    int[] array = new int[n];
    Random rand = new Random();
    int index, index2;
    int f, target;

    // int[] testarray = {11,10,4,5,6,9,2,3,7,8,99,88};
    // merge(testarray, 2, 5, 9);
    // Factorial function:
    f = factorialIterative(5);
    System.out.println("The factorial of 5 is " + f + " (iterative).");
    f = factorialRecursive(5);
    System.out.println("The factorial of 5 is " + f + " (recursive).");

    System.out.println();

    // Sorting:
    generateRandom(array, n);
    System.out.println("Testing mergesort.");
    System.out.println("The array contains:");
    System.out.println(Arrays.toString(array));
    mergesort(array, 0, n - 1);
    System.out.println("After sorting, the array contains:");
    System.out.println(Arrays.toString(array));

    // Twosum: Small tests.
    generateRandom(array, n);
    index = rand.nextInt(n);
    index2 = rand.nextInt(n);
    while (index == index2) {
      index2 = rand.nextInt(n);
    }
    target = array[index] + array[index2];
    System.out.println("Calling twoSum.");
    System.out.println(twoSum(array, target));
    System.out.println("Calling fastTwoSum.");
    System.out.println(fastTwoSum(array, target));

    // Twosum: Timing test.
    int n_large = 35000;
    int[] large_array = new int[n_large];
    long startTime, endTime;
    generateRandom(large_array, n_large);
    index = rand.nextInt(n_large);
    index2 = rand.nextInt(n_large);
    while (index == index2) {
      index2 = rand.nextInt(n_large);
    }
    target = large_array[index] + large_array[index2];
    System.out.println("Calling twoSum on " + n_large + " elements.");
    startTime = System.nanoTime();
    twoSum(large_array, target);
    endTime = System.nanoTime();
    System.out.println("Time: " + ((1e-9) * (endTime - startTime)) + " seconds.");
    System.out.println("Calling fastTwoSum on " + n_large + " elements.");
    startTime = System.nanoTime();
    fastTwoSum(large_array, target);
    endTime = System.nanoTime();
    System.out.println("Time: " + ((1e-9) * (endTime - startTime)) + " seconds.");


  }
}
