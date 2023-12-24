/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc4bTest
{
   /**
    * @param line
    * @param id
    * @param win1
    * @param win2
    * @param win3
    * @param win4
    * @param win5
    * @param num1
    * @param num2
    * @param num3
    * @param num4
    * @param num5
    * @param num6
    * @param num7
    * @param num8
    * @throws IOException
    */
   @ParameterizedTest
   @CsvSource(
   { "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53,  1, 41, 48, 83, 86, 17, 83, 86,  6, 31, 17,  9, 48, 53",
         "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19, 2, 13, 32, 20, 16, 61, 61, 30, 68, 82, 17, 32, 24, 19",
         "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1, 3,  1, 21, 53, 59, 44, 69, 82, 63, 72, 16, 21, 14,  1",
         "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83, 4, 41, 92, 73, 84, 69, 59, 84, 76, 51, 58,  5, 54, 83",
         "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36,5, 87, 83, 26, 28, 32, 88, 30, 70, 12, 93, 22, 82, 36",
         "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11, 6, 31, 18, 13, 56, 72, 74, 77, 10, 23, 35, 67, 36, 11" })
   void testReadCard(final String line, final int id, final int win1, final int win2, final int win3, final int win4,
         final int win5, final int num1, final int num2, final int num3, final int num4, final int num5, final int num6,
         final int num7, final int num8) throws IOException
   {
      final Aoc4b app = new Aoc4b();

      final var card = app.readCard(line);

      assertEquals(id, card.getId());

      final var winners = card.getWinners();
      assertEquals(win1, winners.get(0));
      assertEquals(win2, winners.get(1));
      assertEquals(win3, winners.get(2));
      assertEquals(win4, winners.get(3));
      assertEquals(win5, winners.get(4));

      final var numbers = card.getNumbers();
      assertEquals(num1, numbers.get(0));
      assertEquals(num2, numbers.get(1));
      assertEquals(num3, numbers.get(2));
      assertEquals(num4, numbers.get(3));
      assertEquals(num5, numbers.get(4));
      assertEquals(num6, numbers.get(5));
      assertEquals(num7, numbers.get(6));
      assertEquals(num8, numbers.get(7));
   }

   /**
    * @param line
    * @param score
    * @throws IOException
    */
   @ParameterizedTest
   @CsvSource(
   { "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53, 8", "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19, 2",
         "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1, 2", "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83, 1",
         "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36, 0", "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11, 0"

   })
   void testScoring(final String line, final int score) throws IOException
   {
      final Aoc4b app = new Aoc4b();

      final var card = app.readCard(line);

      assertEquals(score, card.getScore());
   }

}
