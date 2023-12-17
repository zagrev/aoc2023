/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc1Test
{
   /**
    * test that aoc1 can find the first and last digit in a string and combine them into a 2 digit number
    *
    * @param input
    *           the string to process
    * @param expected
    *           the expected result
    */
   @ParameterizedTest
   @CsvSource(
   { "1abc2, 12", "pqr3stu8vwx, 38", "a1b2c3d4e5f, 15", "treb7uchet, 77" })
   void test(final String input, final int expected)
   {
      final Aoc1 app = new Aoc1();
      final int actual = app.getNum(input);
      assertEquals(expected, actual);
   }

}
