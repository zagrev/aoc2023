/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc9bTest
{

   /**
    * @param values
    * @param expected
    */
   @ParameterizedTest
   @CsvSource(
   { "0 3 6 9 12 15, 18", "1 3 6 10 15 21, 28", "10 13 16 21 30 45, 68",
         "1 -1 -3 -5 -7 -9 -11 -13 -15 -17 -19 -21 -23 -25 -27 -29 -31 -33 -35 -37 -39,3" })
   void testPredictValue(final String values, final Long expected)
   {
      final var app = new Aoc9();

      final var list = app.parseValues(values);
      final long actual = app.predictNextValue(list);

      assertEquals(expected, actual, "From values: " + values);
   }

   /**
    *
    */
   @Test
   void testReadValues()
   {
      final var app = new Aoc9();

      final var list = app.parseValues("0 3 6 9 12 15");

      assertNotNull(list);
      assertEquals(6, list.length);

      assertEquals(0L, list[0]);
      assertEquals(3L, list[1]);
      assertEquals(6L, list[2]);
      assertEquals(9L, list[3]);
      assertEquals(12L, list[4]);
      assertEquals(15L, list[5]);
   }

}
