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
class Aoc1bTest
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
   { "two1nine, 29", "eightwothree, 83", "abcone2threexyz, 13", "xtwone3four, 24", "4nineeightseven2, 42",
         "zoneight234, 14", "7pqrstsixteen, 76" })
   void test(final String input, final int expected)
   {
      final Aoc1b app = new Aoc1b();
      final var nums = Aoc1b.initNumbers();

      final int actual = app.getNum(nums, input);
      assertEquals(expected, actual);
   }

}
