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
public class Aoc6
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc6 app = new Aoc6();

      try (var resource = Aoc6.class.getResourceAsStream("/aoc6-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         app.readInput(input);

         final long min = app.getWaysToWin();
         System.out.println("Aoc6 total = " + min);
      }
   }

   /** the record travel lengths of the toy boats */
   private final ArrayList<Long> distances = new ArrayList<>();

   /** the time of each race */
   private final ArrayList<Long> times = new ArrayList<>();

   /**
    * @param raceTime
    * @param recordDistance
    * @return the list of winning distances
    */
   public List<Long> calculateDistance(final long raceTime, final long recordDistance)
   {
      final long totalTime = raceTime;
      final ArrayList<Long> winningTimes = new ArrayList<>();

      for (long t = 1; t < totalTime; t++)
      {
         final long velocity = t;
         final long travelTime = totalTime - t;
         final long distance = velocity * travelTime;

         if (distance > recordDistance)
         {
            winningTimes.add(Long.valueOf(distance));
         }
      }
      return winningTimes;
   }

   /**
    * @return the distances
    */
   ArrayList<Long> getDistances()
   {
      return distances;
   }

   /**
    * @return the times
    */
   ArrayList<Long> getTimes()
   {
      return times;
   }

   /**
    * @return the number of ways to win over all the races
    */
   public long getWaysToWin()
   {
      final ArrayList<List<Long>> allWays = new ArrayList<>();
      for (int i = 0; i < times.size(); i++)
      {
         allWays.add(calculateDistance(times.get(i).longValue(), distances.get(i).longValue()));
      }
      long total = 1;
      for (final var list : allWays)
      {
         total = total * list.size();
      }
      return total;
   }

   /**
    * @param input
    * @throws IOException
    */
   public void readInput(final BufferedReader input) throws IOException
   {
      try
      {
         final var timeData = input.readLine();
         final var distanceData = input.readLine();

         var tmp = timeData.split(": +");
         final var timeStrings = tmp[1].split(" +");

         for (final String num : timeStrings)
         {
            times.add(Long.valueOf(num));
         }

         tmp = distanceData.split(": +");
         final var distanceStrings = tmp[1].split(" +");

         for (final String num : distanceStrings)
         {
            distances.add(Long.valueOf(num));
         }
      }
      catch (final NullPointerException e)
      {
         // done reading. Ignore exception.
      }
   }
}
