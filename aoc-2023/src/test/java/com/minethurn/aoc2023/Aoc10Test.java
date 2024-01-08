/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
class Aoc10Test
{

   /**
    * @throws IOException
    */
   @Test
   void testCalcDistances() throws IOException
   {
      final Aoc10 app = new Aoc10();

      final String data = """
            .....
            .F-7.
            .|.|.
            .L-J.
            .....
            """;

      final int[][] expected =
      {
            { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
            { Integer.MAX_VALUE, 0, 1, 2, Integer.MAX_VALUE },
            { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 3, Integer.MAX_VALUE },
            { Integer.MAX_VALUE, 2, 3, 4, Integer.MAX_VALUE },
            { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE } };

      try (var input = new BufferedReader(new StringReader(data)))
      {
         app.readMaze(input);
         final var actuals = app.startCalcDistances(1, 1);

         for (int row = 0; row < expected.length; row++)
         {
            assertArrayEquals(expected[row], actuals[row], "Failed on row: " + app.getMaze()[row]);
         }
      }
   }

   /**
    * @param input
    * @param expected
    */
   @ParameterizedTest
   @CsvSource(
   { "-, 10", "|, 1", "L,1", "J,1", "7,10", "F,10", ".,10", "S,10" })
   void testCalcDown(final String input, final int expected)
   {
      final var app = new Aoc10();
      final int[][] vals =
      {
            { 10 } };
      final String[] maze =
      { input };
      app.setMaze(maze);

      app.calcDistanceD(vals, 1, 0, 0);
      assertEquals(expected, vals[0][0]);
   }

   /**
    * @param input
    * @param expected
    */
   @ParameterizedTest
   @CsvSource(
   { "-, 1", "|,10", "L,1", "J,10", "7,10", "F,1", ".,10", "S,10" })
   void testCalcLeft(final String input, final int expected)
   {
      final var app = new Aoc10();
      final int[][] vals =
      {
            { 10 } };
      final String[] maze =
      { input };
      app.setMaze(maze);

      app.calcDistanceL(vals, 1, 0, 0);
      assertEquals(expected, vals[0][0]);
   }

   /**
    * @param input
    * @param expected
    */
   @ParameterizedTest
   @CsvSource(
   { "-, 1", "|,10", "L,10", "J,1", "7,1", "F,10", ".,10", "S,10" })
   void testCalcRight(final String input, final int expected)
   {
      final var app = new Aoc10();
      final int[][] vals =
      {
            { 10 } };
      final String[] maze =
      { input };
      app.setMaze(maze);

      app.calcDistanceR(vals, 1, 0, 0);
      assertEquals(expected, vals[0][0]);
   }

   /**
    * @param input
    * @param expected
    */
   @ParameterizedTest
   @CsvSource(
   { "-,10", "|, 1", "L,10", "J,10", "7,1", "F,1", ".,10", "S,10" })
   void testCalcU(final String input, final int expected)
   {
      final var app = new Aoc10();
      final int[][] vals =
      {
            { 10 } };
      final String[] maze =
      { input };
      app.setMaze(maze);

      app.calcDistanceU(vals, 1, 0, 0);
      assertEquals(expected, vals[0][0]);
   }

}
