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
class Aoc6bTest
{
   /** thye unit test data */
   String data = """
         Time:      71530
         Distance:  940200
         """;

   /**
    * @throws IOException
    */
   @Test
   void testReadInputs() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc6b();
         app.readInput(input);

         final var times = app.getTimes();
         assertEquals(1, times.size());
         assertEquals(71530, times.get(0).intValue());

         final var distances = app.getDistances();
         assertEquals(1, distances.size());
         assertEquals(940200, distances.get(0).intValue());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadInputsBigNumbers() throws IOException
   {
      final var bigData = """
            Time:        35937366
            Distance:   212206012011044
            """;
      try (var input = new BufferedReader(new StringReader(bigData)))
      {
         final var app = new Aoc6b();
         app.readInput(input);

         final var times = app.getTimes();
         assertEquals(1, times.size());
         assertEquals(35937366L, times.get(0).intValue());

         final var distances = app.getDistances();
         assertEquals(1, distances.size());
         assertEquals(212206012011044L, distances.get(0).longValue());
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
         final var app = new Aoc6b();
         app.readInput(input);

         final long actualWays = app.getWaysToWin();
         assertEquals(71503, actualWays);
      }
   }

   /**
    * @param raceTime
    * @param maxDistance
    * @param expectedWaysToWin
    */
   @ParameterizedTest
   @CsvSource(
   { "71530,940200,71503" })
   void testWinningDistances(final long raceTime, final long maxDistance, final long expectedWaysToWin)
   {
      final var app = new Aoc6b();

      final var actualWaysToWin = app.calculateDistance(raceTime, maxDistance);

      assertEquals(expectedWaysToWin, actualWaysToWin.size());
   }

}
