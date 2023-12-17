/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 */
public class Aoc1b
{

   /**
    * @return the hash map of number names to number values
    */
   static HashMap<String, Character> initNumbers()
   {
      final var nums = new HashMap<String, Character>();

      nums.put("one", Character.valueOf('1'));
      nums.put("two", Character.valueOf('2'));
      nums.put("three", Character.valueOf('3'));
      nums.put("four", Character.valueOf('4'));
      nums.put("five", Character.valueOf('5'));
      nums.put("six", Character.valueOf('6'));
      nums.put("seven", Character.valueOf('7'));
      nums.put("eight", Character.valueOf('8'));
      nums.put("nine", Character.valueOf('9'));

      return nums;
   }

   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc1b app = new Aoc1b();
      int total = 0;
      final var nums = initNumbers();

      try (var resource = Aoc1b.class.getResourceAsStream("/aoc1-input.txt");
            final var input = new BufferedReader(new InputStreamReader(resource)))
      {
         String line;
         while ((line = input.readLine()) != null)
         {
            final var curValue = app.getNum(nums, line);
            total += curValue;
         }
      }

      System.out.println("AoC1 total = " + total);
   }

   /**
    * @param nums
    *           the mapping from words to characters
    * @param input
    *           the string to process
    * @return the first character that represents a digit
    */
   private char getFirstDigit(final HashMap<String, Character> nums, final String input)
   {
      for (int i = 0; i < input.length(); i++)
      {
         final char c = input.charAt(i);
         if (Character.isDigit(c))
         {
            return c;
         }

         final var substring = input.substring(i);
         for (final var key : nums.keySet())
         {
            if (substring.startsWith(key))
            {
               return nums.get(key).charValue();
            }
         }
      }
      return ' ';
   }

   /**
    * @param nums
    *           the mapping from words to characters
    * @param input
    *           the string to process
    * @return the first character that represents a digit
    */
   private char getLastDigit(final HashMap<String, Character> nums, final String input)
   {
      for (int i = input.length() - 1; i >= 0; i--)
      {
         final char c = input.charAt(i);
         if (Character.isDigit(c))
         {
            return c;
         }

         final var substring = input.substring(i);
         for (final var key : nums.keySet())
         {
            if (substring.startsWith(key))
            {
               return nums.get(key).charValue();
            }
         }
      }
      return ' ';
   }

   /**
    * get the first and last number from the given string. This matches "one" and "1" equally.
    *
    * @param nums
    *           the mapping from number strings to values
    * @param input
    *           the string to process
    * @return the integer value of the 2 digits
    */
   public int getNum(final HashMap<String, Character> nums, final String input)
   {
      final char first = getFirstDigit(nums, input);
      final char last = getLastDigit(nums, input);

      final var actual = Integer.valueOf("" + first + last);
      System.out.println(String.format("actual = %d", actual));

      return actual.intValue();
   }
}
