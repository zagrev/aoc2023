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
class A7JokerCardStrategyTest
{

   /**
    *
    */
   @Test
   void testCompareCards()
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();

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

      assertEquals(-1, strategy.compareCards('J', '2'));
      assertEquals(-1, strategy.compareCards('J', 'T'));
      assertEquals(-1, strategy.compareCards('J', 'Q'));
      assertEquals(-1, strategy.compareCards('J', 'K'));
      assertEquals(-1, strategy.compareCards('J', 'A'));
      assertEquals(1, strategy.compareCards('2', 'J'));
      assertEquals(1, strategy.compareCards('T', 'J'));
      assertEquals(1, strategy.compareCards('Q', 'J'));
      assertEquals(1, strategy.compareCards('K', 'J'));
      assertEquals(1, strategy.compareCards('A', 'J'));

      assertEquals(0, strategy.compareCards('J', 'J'));
   }

   /**
    * @throws IOException
    */
   @Test
   public void testCompareHands() throws IOException
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();

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
         assertEquals("KK677", actuals.get(1).getCards());
         assertEquals("T55J5", actuals.get(2).getCards());
         assertEquals("QQQJA", actuals.get(3).getCards());
         assertEquals("KTJJT", actuals.get(4).getCards());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   public void testCompareHandsLeadingWild() throws IOException
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();

      final var data = """
            KTJJT
            JJ278
            JJ4A6
            J2A34
            JJJ23
            JJJ45
            JJJJA
            JJJJK
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
         assertEquals(cards.size(), actuals.size());

         assertEquals("J2A34", actuals.get(0).getCards());
         assertEquals("JJ278", actuals.get(1).getCards());
         assertEquals("JJ4A6", actuals.get(2).getCards());
         assertEquals("JJJ23", actuals.get(3).getCards());
         assertEquals("JJJ45", actuals.get(4).getCards());
         assertEquals("KTJJT", actuals.get(5).getCards());
         assertEquals("JJJJK", actuals.get(6).getCards());
         assertEquals("JJJJA", actuals.get(7).getCards());
      }
   }

   /**
    * @param cards
    * @param expectedType
    * @throws UnsupportedEncodingException
    */
   @ParameterizedTest
   @CsvSource(
   { "AAAAA, 6", "AA8AA, 5", "23332, 4", "TTT98, 3", "23432,2", "A23A4,1", "23456, 0", "32T3K, 1", "KK677, 2",
         "T55J5, 5", "QQQJA, 5", "KTJJT, 5", "JJ278, 3", "JJ4A6, 3", "J2A34, 1" })
   public void testHandType(final String cards, final int expectedType) throws UnsupportedEncodingException
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();

      assertEquals(expectedType, strategy.getHandType(cards), cards);
   }
}
