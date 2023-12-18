/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class Aoc5
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc5 app = new Aoc5();

      try (var resource = Aoc5.class.getResourceAsStream("/aoc5-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         final long min = app.getMinLocation(input);
         System.out.println("Aoc5 total = " + min);
      }
   }

   /** the seeds we are to track */
   private ArrayList<Long> seeds;

   /** all the number maps */
   HashMap<String, SoilMap> allMaps = new HashMap<>();

   /**
    * @param input
    * @return the minimum value after all the translating is complete
    * @throws IOException
    */
   long getMinLocation(final BufferedReader input) throws IOException
   {
      long min = Long.MAX_VALUE;
      readInput(input);

      for (final long seed : getSeeds())
      {
         final long mappedValue = map(seed, "seed");
         if (mappedValue < min)
         {
            min = mappedValue;
         }
      }
      return min;
   }

   /**
    * @return the seeds
    */
   ArrayList<Long> getSeeds()
   {
      return seeds;
   }

   /**
    * @param value
    * @param type
    * @return the value mapped through all the different maps
    */
   public long map(final long value, final String type)
   {
      long currentValue = value;
      var map = allMaps.get(type);

      while (map != null)
      {
         currentValue = map.map(currentValue);
         map = allMaps.get(map.getDestType());
      }
      return currentValue;
   }

   /**
    * @param input
    * @throws IOException
    */
   public void readInput(final BufferedReader input) throws IOException
   {
      try
      {
         readSeeds(input);
         while (input.ready())
         {
            final var map = readMap(input);
            allMaps.put(map.getSourceType(), map);
         }
      }
      catch (final NullPointerException e)
      {
         // done reading. Ignore exception.
      }
   }

   /**
    * @param input
    * @return the range represented by the line of input
    * @throws IOException
    * @throws NumberFormatException
    */
   public SoilMap readMap(final BufferedReader input) throws NumberFormatException, IOException
   {
      String line;

      line = input.readLine();
      var bits = line.split(" +");
      bits = bits[0].split("-");

      final SoilMap map = new SoilMap(bits[0], bits[2]);

      while ((line = input.readLine()) != null && !line.isBlank())
      {
         final var nums = line.split(" +");

         final Range range = new Range(Long.parseLong(nums[1]), Long.parseLong(nums[0]), Long.parseLong(nums[2]));
         map.addRange(range);
      }
      return map;
   }

   /**
    * @param input
    * @return the list of seeds to be planted
    * @throws IOException
    */
   public List<Long> readSeeds(final BufferedReader input) throws IOException
   {
      seeds = new ArrayList<>();

      final String line = input.readLine();
      final var values = line.split("[: ]+");

      if (!values[0].equals("seeds"))
      {
         System.err.println("Invalid seed line");
      }
      input.readLine(); // skip the blank line

      for (int i = 1; i < values.length; i++)
      {
         final var value = Long.valueOf(values[i]);
         seeds.add(value);
      }
      return seeds;
   }
}
