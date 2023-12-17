/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class Aoc1
{

   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc1 app = new Aoc1();
      int total = 0;

      try (var resource = Aoc1.class.getResourceAsStream("/aoc1-input.txt");
            final var input = new BufferedReader(new InputStreamReader(resource)))
      {
         String line;
         while ((line = input.readLine()) != null)
         {
            final var curValue = app.getNum(line);
            total += curValue;
         }
      }

      System.out.println("AoC1 total = " + total);
   }

   /**
    * @param input
    * @return the first character that represents a digit
    */
   private char getFirstDigit(final String input)
   {
      return (char) input.chars().filter((c) -> Character.isDigit(c)).findFirst().getAsInt();
   }

   /**
    * @param input
    * @return the number represented by the first and last numbers in the string
    */
   public int getNum(final String input)
   {
      final char first = getFirstDigit(input);
      final char last = getFirstDigit(new StringBuilder(input).reverse().toString());

      final var actual = Integer.valueOf("" + first + last);
      System.out.println(String.format("actual = %d", actual));

      return actual.intValue();
   }
}
