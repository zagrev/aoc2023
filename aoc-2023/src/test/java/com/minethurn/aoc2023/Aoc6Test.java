/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc6Test
{
   /** thye unit test data */
   String data = """
         Time:      7 15 30
         Distance:  9 40 200
         """;

   /**
    * @throws IOException
    */
   @Test
   void testReadInputs() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc6();
         app.readInput(input);

         final var times = app.getTimes();
         assertEquals(3, times.size());
         assertEquals(7, times.get(0).intValue());
         assertEquals(15, times.get(1).intValue());
         assertEquals(30, times.get(2).intValue());

         final var distances = app.getDistances();
         assertEquals(3, distances.size());
         assertEquals(9, distances.get(0).intValue());
         assertEquals(40, distances.get(1).intValue());
         assertEquals(200, distances.get(2).intValue());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testTotalWaysToWin() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc6();
         app.readInput(input);

         final long actualWays = app.getWaysToWin();
         assertEquals(288, actualWays);
      }
   }

   /**
    * @param raceTime
    * @param maxDistance
    * @param expectedWaysToWin
    */
   @ParameterizedTest
   @CsvSource(
   { "7, 9, 4", "15, 40, 8", "30, 200, 9" })
   void testWinningDistances(final long raceTime, final long maxDistance, final long expectedWaysToWin)
   {
      final var app = new Aoc6();

      final var actualWaysToWin = app.calculateDistance(raceTime, maxDistance);

      assertEquals(expectedWaysToWin, actualWaysToWin.size());
   }
}
