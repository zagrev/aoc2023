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
public class Aoc9
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc9 app = new Aoc9();
      var total = 0L;

      try (var resource = Aoc9.class.getResourceAsStream("/aoc9-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         String line;

         while ((line = input.readLine()) != null)
         {
            if (!line.isBlank())
            {
               line = line.strip();

               final long[] values = app.parseValues(line);
               final long next = app.predictNextValue(values);

               total += next;
            }
         }
         System.out.println("Aoc9 = " + total);

      }
   }

   /**
    * @param values
    * @return the array of differences between the given values
    */
   long getDiffs(final long[] values)
   {
      final long[] diffs = new long[values.length - 1];
      boolean allMatch = true;
      long lastDiff = 0;

      for (int i = 1; i < values.length; i++)
      {
         final long diff = values[i] - values[i - 1];
         diffs[i - 1] = diff;
         if (lastDiff != diff)
         {
            allMatch = false;
            lastDiff = diff;
         }
      }

      if (allMatch)
      {
         return lastDiff;
      }
      return lastDiff + getDiffs(diffs);
   }

   /**
    * @param line
    * @return list of numbers in the string
    */
   public long[] parseValues(final String line)
   {
      final var words = line.split("[ ]+");
      final long[] nums = new long[words.length];

      for (int i = 0; i < words.length; i++)
      {
         nums[i] = Long.parseLong(words[i]);
      }
      return nums;
   }

   /**
    * @param values
    * @return the next value predicted
    */
   public long predictNextValue(final long[] values)
   {
      if (values.length == 0)
      {
         throw new IllegalArgumentException("empty number list");
      }
      return values[values.length - 1] + getDiffs(values);
   }
}
