/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class A7CardStrategyTest
{

   /**
    *
    */
   @Test
   void testCompareCards()
   {
      final A7CardStrategy strategy = new A7CardStrategy();

      assertEquals(0, strategy.compareCards('2', '2'));

      assertEquals(-1, strategy.compareCards('2', '3'));
      assertEquals(1, strategy.compareCards('3', '2'));
      assertEquals(0, strategy.compareCards('3', '3'));

      assertEquals(-1, strategy.compareCards('9', 'T'));
      assertEquals(1, strategy.compareCards('T', '9'));
      assertEquals(0, strategy.compareCards('T', 'T'));

      assertEquals(-1, strategy.compareCards('T', 'Q'));
      assertEquals(1, strategy.compareCards('Q', 'T'));
      assertEquals(0, strategy.compareCards('Q', 'Q'));

      assertEquals(-1, strategy.compareCards('Q', 'K'));
      assertEquals(1, strategy.compareCards('K', 'Q'));
      assertEquals(0, strategy.compareCards('K', 'K'));

      assertEquals(-1, strategy.compareCards('K', 'A'));
      assertEquals(1, strategy.compareCards('A', 'K'));
      assertEquals(0, strategy.compareCards('A', 'A'));
   }

   /**
    * @throws IOException
    */
   @Test
   public void testCompareHands() throws IOException
   {
      final A7CardStrategy strategy = new A7CardStrategy();

      final var data = """
            32T3K
            T55J5
            KK677
            KTJJT
            QQQJA
            """;
      try (var input = new BufferedReader(new StringReader(data)))
      {

         final ArrayList<CamelCardHand> cards = new ArrayList<>();
         String line;
         while ((line = input.readLine()) != null)
         {
            final var card = new CamelCardHand(line, 0);
            cards.add(card);
         }

         final var actuals = strategy.rank(cards);

         assertNotNull(actuals);
         assertEquals(5, actuals.size());

         assertEquals("32T3K", actuals.get(0).getCards());
         assertEquals("KTJJT", actuals.get(1).getCards());
         assertEquals("KK677", actuals.get(2).getCards());
         assertEquals("T55J5", actuals.get(3).getCards());
         assertEquals("QQQJA", actuals.get(4).getCards());
      }
   }

   /**
    * @param cards
    * @param expectedType
    * @throws UnsupportedEncodingException
    */
   @ParameterizedTest
   @CsvSource(
   { "AAAAA, 6", "AA8AA, 5", "23332, 4", "TTT98, 3", "23432,2", "A23A4,1", "23456, 0" })
   public void testHandType(final String cards, final int expectedType) throws UnsupportedEncodingException
   {
      final A7CardStrategy strategy = new A7CardStrategy();

      assertEquals(expectedType, strategy.getHandType(cards), cards);
   }
}
