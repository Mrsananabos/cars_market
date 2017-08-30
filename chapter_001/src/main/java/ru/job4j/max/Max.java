
package ru.job4j.max;

public class Max {

  public int max(int first, int second, int third) {
   int max = Math.max(first, second);
   max = Math.max(max, third);
   return max;
  }
}