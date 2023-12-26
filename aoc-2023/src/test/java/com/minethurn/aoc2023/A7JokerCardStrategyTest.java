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
    * the value 0 if x == y;a value less than 0 if x < y; and a value greater than 0 if x > y
    */
   @Test
   void testCompareCards()
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();
      strategy.setReverseSort(false);

      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('2', '2'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('2', '3'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('3', '2'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('3', '3'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('9', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('T', '9'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('T', 'T'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('T', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('Q', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('Q', 'Q'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('Q', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('K', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('K', 'K'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('K', 'A'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('A', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('A', 'A'));

      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('J', '2'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('J', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('J', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('J', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('J', 'A'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('2', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('T', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('Q', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('K', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('A', 'J'));

      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('J', 'J'));
   }

   /**
    * the value 0 if x == y;a value greater than 0 if x < y; and a value less than 0 if x > y
    */
   @Test
   void testCompareCardsReverse()
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();
      strategy.setReverseSort(true);

      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('2', '2'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('2', '3'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('3', '2'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('3', '3'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('9', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('T', '9'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('T', 'T'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('T', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('Q', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('Q', 'Q'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('Q', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('K', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('K', 'K'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('K', 'A'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('A', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('A', 'A'));

      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('J', '2'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('J', 'T'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('J', 'Q'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('J', 'K'));
      assertEquals(A7JokerCardStrategy.CARDS_GT, strategy.compareCards('J', 'A'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('2', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('T', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('Q', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('K', 'J'));
      assertEquals(A7JokerCardStrategy.CARDS_LT, strategy.compareCards('A', 'J'));

      assertEquals(A7JokerCardStrategy.CARDS_EQUAL, strategy.compareCards('J', 'J'));
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

         System.out.println("testCompareHands: " + actuals);
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
    * @throws IOException
    */
   @Test
   public void testCompareHandsReverse() throws IOException
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();
      strategy.setReverseSort(true);

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

         assertEquals("KTJJT", actuals.get(0).getCards());
         assertEquals("QQQJA", actuals.get(1).getCards());
         assertEquals("T55J5", actuals.get(2).getCards());
         assertEquals("KK677", actuals.get(3).getCards());
         assertEquals("32T3K", actuals.get(4).getCards());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   public void testCompareHandsWildCount() throws IOException
   {
      final A7JokerCardStrategy strategy = new A7JokerCardStrategy();

      final var data = """
            # high card
            12345
            12346
            12365
            12645
            16345

            # one pair
            2J456
            23J56
            234J6
            2345J

            26654
            26764
            26786

            # two pair
            34345
            34435
            34453

            # three of a kind
            46166
            461J6
            4616J
            46J61
            46J16

            # full house
            55666
            55J66
            556J6
            5566J

            # four of a kind
            66665
            6J665
            66J65
            666J5
            6665J

            66JJ5
            6JJ65

            6JJJ5
            JJJ65

            # five of  a kind
            QQQQQ
            QQQQJ
            QQQJJ
            QQJJJ
            QJJJJ
            JJJJJ
             """;
      try (var input = new BufferedReader(new StringReader(data)))
      {

         final ArrayList<CamelCardHand> cards = new ArrayList<>();
         String line;
         while ((line = input.readLine()) != null)
         {
            if (!line.isBlank() && !line.trim().startsWith("#"))
            {
               final var card = new CamelCardHand(line, 0);
               cards.add(card);
            }
         }

         final var actuals = strategy.rank(cards);

         System.out.println("testCompareHandsWildCount");
         System.out.println("  ranked = " + actuals);
         assertNotNull(actuals);
         assertEquals(cards.size(), actuals.size());

         // high card
         assertEquals("12345", actuals.remove(0).getCards());
         assertEquals("12346", actuals.remove(0).getCards());
         assertEquals("12365", actuals.remove(0).getCards());
         assertEquals("12645", actuals.remove(0).getCards());
         assertEquals("16345", actuals.remove(0).getCards());

         // one pair
         assertEquals("2J456", actuals.remove(0).getCards());
         assertEquals("23J56", actuals.remove(0).getCards());
         assertEquals("234J6", actuals.remove(0).getCards());
         assertEquals("2345J", actuals.remove(0).getCards());

         assertEquals("26654", actuals.remove(0).getCards());
         assertEquals("26764", actuals.remove(0).getCards());
         assertEquals("26786", actuals.remove(0).getCards());

         // two pair
         assertEquals("34345", actuals.remove(0).getCards());
         assertEquals("34435", actuals.remove(0).getCards());
         assertEquals("34453", actuals.remove(0).getCards());

         // 3 of a kind
         assertEquals("46J16", actuals.remove(0).getCards());
         assertEquals("46J61", actuals.remove(0).getCards());
         assertEquals("461J6", actuals.remove(0).getCards());
         assertEquals("4616J", actuals.remove(0).getCards());
         assertEquals("46166", actuals.remove(0).getCards());

         // full house
         assertEquals("55J66", actuals.remove(0).getCards());
         assertEquals("556J6", actuals.remove(0).getCards());
         assertEquals("5566J", actuals.remove(0).getCards());
         assertEquals("55666", actuals.remove(0).getCards());

         // 4 of a kind
         assertEquals("JJJ65", actuals.remove(0).getCards());
         assertEquals("6JJJ5", actuals.remove(0).getCards());
         assertEquals("6JJ65", actuals.remove(0).getCards());
         assertEquals("6J665", actuals.remove(0).getCards());
         assertEquals("66JJ5", actuals.remove(0).getCards());
         assertEquals("66J65", actuals.remove(0).getCards());
         assertEquals("666J5", actuals.remove(0).getCards());
         assertEquals("6665J", actuals.remove(0).getCards());

         assertEquals("66665", actuals.remove(0).getCards());

         // five of a kind
         assertEquals("JJJJJ", actuals.remove(0).getCards());
         assertEquals("QJJJJ", actuals.remove(0).getCards());
         assertEquals("QQJJJ", actuals.remove(0).getCards());
         assertEquals("QQQJJ", actuals.remove(0).getCards());
         assertEquals("QQQQJ", actuals.remove(0).getCards());
         assertEquals("QQQQQ", actuals.remove(0).getCards());
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
