/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Aoc3b
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc3b app = new Aoc3b();

      long total = 0;

      try (var resource = Aoc3b.class.getResourceAsStream("/aoc3-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         final List<Integer> ratios = app.getGearRatios(input);
         for (final Integer item : ratios)
         {
            total += item.intValue();
         }

         System.out.println("Aoc3 total = " + total);
      }
   }

   /**
    * @param line
    * @param col
    * @return the number found at the column, or -1 if no number found
    */
   private int check(final String line, final int col)
   {
      if (col >= 0 && col < line.length() && Character.isDigit(line.charAt(col)))
      {
         return getNumber(line, col);
      }
      return -1;
   }

   /**
    * @param input
    * @return a list of numbers that represent pairs of gear ratios
    * @throws IOException
    */
   public List<Integer> getGearRatios(final BufferedReader input) throws IOException
   {
      final ArrayList<String> dataArray = new ArrayList<>();
      final ArrayList<Integer> ratios = new ArrayList<>();

      String line;
      while ((line = input.readLine()) != null)
      {
         dataArray.add(line);
      }

      // now find the symbols, and for each, find all the numbers adjacent
      final ArrayList<Integer> gears = new ArrayList<>();

      for (int row = 0; row < dataArray.size(); row++)
      {
         line = dataArray.get(row);

         for (int col = 0; col < line.length(); col++)
         {
            // a symbol is not a digit, and not a dot
            if (line.charAt(col) == '*')
            {
               // found gear

               // first check left of the current character
               int value = 0;
               boolean columnHasNumber = false;

               if ((value = check(line, col - 1)) >= 0)
               {
                  gears.add(Integer.valueOf(value));
               }
               // first check left of the current character
               if ((value = check(line, col + 1)) >= 0)
               {
                  gears.add(Integer.valueOf(value));
               }

               // check above
               if (row > 0)
               {
                  final var above = dataArray.get(row - 1);
                  columnHasNumber = false;

                  if ((value = check(above, col - 1)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  if (!columnHasNumber && (value = check(above, col)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  else if (!Character.isDigit(above.charAt(col)))
                  {
                     columnHasNumber = false;
                  }
                  if (!columnHasNumber && (value = check(above, col + 1)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                  }
               }

               // check below
               if (row < dataArray.size() - 1)
               {
                  final var below = dataArray.get(row + 1);
                  columnHasNumber = false;

                  if ((value = check(below, col - 1)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  if (!columnHasNumber && (value = check(below, col)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  else if (!Character.isDigit(below.charAt(col)))
                  {
                     columnHasNumber = false;
                  }
                  if (!columnHasNumber && (value = check(below, col + 1)) >= 0)
                  {
                     gears.add(Integer.valueOf(value));
                  }
               }

               if (gears.size() == 2)
               {
                  ratios.add(Integer.valueOf(gears.get(0).intValue() * gears.get(1).intValue()));
               }
               gears.clear();
            } // found a gear
         }
      }

      return ratios;
   }

   /**
    * We will get the number that has a digit in the given column. We do this by walking back until there are no digits,
    * then copying the digits out until we hit a non-digit character.
    *
    * @param line
    * @param col
    * @return the number found at the given location, or -1 is the number could not be read
    */
   private int getNumber(final String line, final int col)
   {
      final StringBuilder digits = new StringBuilder();

      int column = col;
      while (column > 0 && Character.isDigit(line.charAt(column - 1)))
      {
         column--;
      }
      while (column < line.length() && Character.isDigit(line.charAt(column)))
      {
         digits.append(line.charAt(column));
         column++;
      }
      return Integer.parseInt(digits.toString());
   }

   /**
    * Find the part numbers in the input data array. A number will be adjacent to a symbol, which is a non-numeric,
    * non-period location the array. We must check all the locations around the symbol to find all the part numbers. The
    * numbers may be above, below, left, right, and in any of the 4 corners. It is easy to find them, but how to
    * determine if there is a number ending in an upper-left location and a number starting in the upper-right location?
    * <p>
    *
    * <pre>
    * ...4...456...
    * ...#....#....
    * ...5114......
    * </pre>
    *
    * @param input
    * @return the number translated from text to binary, or -1 if no number found
    * @throws IOException
    */
   public List<Integer> readPartNumbers(final BufferedReader input) throws IOException
   {
      final ArrayList<String> dataArray = new ArrayList<>();
      final ArrayList<Integer> partNumbers = new ArrayList<>();

      String line;
      while ((line = input.readLine()) != null)
      {
         dataArray.add(line);
      }

      // now find the symbols, and for each, find all the numbers adjacent
      for (int row = 0; row < dataArray.size(); row++)
      {
         line = dataArray.get(row);

         for (int col = 0; col < line.length(); col++)
         {
            // a symbol is not a digit, and not a dot
            if (line.charAt(col) != '.' && !Character.isDigit(line.charAt(col)))
            {
               // found a symbol, look around it to find a all the part numbers
               // first check left of the current character
               int value = 0;
               boolean columnHasNumber = false;

               if ((value = check(line, col - 1)) >= 0)
               {
                  partNumbers.add(Integer.valueOf(value));
               }
               // first check left of the current character
               if ((value = check(line, col + 1)) >= 0)
               {
                  partNumbers.add(Integer.valueOf(value));
               }

               // check above
               if (row > 0)
               {
                  final var above = dataArray.get(row - 1);
                  columnHasNumber = false;

                  if ((value = check(above, col - 1)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  if (!columnHasNumber && (value = check(above, col)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  else if (!Character.isDigit(above.charAt(col)))
                  {
                     columnHasNumber = false;
                  }
                  if (!columnHasNumber && (value = check(above, col + 1)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                  }
               }

               // check below
               if (row < dataArray.size() - 1)
               {
                  final var below = dataArray.get(row + 1);
                  columnHasNumber = false;

                  if ((value = check(below, col - 1)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  if (!columnHasNumber && (value = check(below, col)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                     columnHasNumber = true;
                  }
                  else if (!Character.isDigit(below.charAt(col)))
                  {
                     columnHasNumber = false;
                  }
                  if (!columnHasNumber && (value = check(below, col + 1)) >= 0)
                  {
                     partNumbers.add(Integer.valueOf(value));
                  }
               }
            }
         }
      }

      return partNumbers;
   }

   /**
    * @param input
    * @return the sum of the part numbers
    * @throws IOException
    */
   public long sumPartNumbers(final BufferedReader input) throws IOException
   {
      final List<Integer> partNumbers = readPartNumbers(input);
      long total = 0;

      for (final Integer n : partNumbers)
      {
         total += n.intValue();
      }
      return total;
   }

}
