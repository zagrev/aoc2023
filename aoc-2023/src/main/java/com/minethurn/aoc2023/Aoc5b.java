/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Aoc5b
{
   /**
    * @param args
    * @throws IOException
    * @throws ExecutionException
    */
   public static void main(final String[] args) throws IOException, ExecutionException
   {
      final Aoc5b app = new Aoc5b();

      try (var resource = Aoc5b.class.getResourceAsStream("/aoc5-input.txt");
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
    * @throws ExecutionException
    */
   long getMinLocation(final BufferedReader input) throws IOException, ExecutionException
   {
      long min = Long.MAX_VALUE;
      final long BATCH_SIZE = 1_000_000;

      readInput(input);

      final long start = System.nanoTime();

      final ArrayList<Future<Long>> results = new ArrayList<>();
      final var pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

      while (seeds.size() > 1)
      {
         final Long base = seeds.remove(0);
         final Long count = seeds.remove(0);

         final long originalEnd = base.longValue() + count.longValue();

         for (long batch = base.longValue(); batch < originalEnd; batch += BATCH_SIZE)
         {
            final long localBase = batch;
            final long localCount = Math.min(BATCH_SIZE, originalEnd - localBase);

            final var future = pool.submit(() -> {
               long localMin = Long.MAX_VALUE;
               for (long index = localBase; index < localBase + localCount; index++)
               {
                  final long mappedValue = map(index, "seed");
                  if (mappedValue < localMin)
                  {
                     localMin = mappedValue;
                  }
               }
               return Long.valueOf(localMin);
            });
            results.add(future);
         }
      }

      // ok, wait for the results
      pool.shutdown();
      try
      {
         pool.awaitTermination(1L, TimeUnit.DAYS);
         for (final var future : results)
         {
            final var localMin = future.get().longValue();
            if (localMin < min)
            {
               min = localMin;
            }

         }
      }
      catch (final InterruptedException e)
      {
         System.err.println("Interrupted!");
      }
      final long end = System.nanoTime();

      final var duration = Duration.ofNanos(end - start);
      System.out.println("Computed in " + duration);
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
