/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc7bTest
{
   /** the unit test data */
   String data = """
         32T3K, 765
         T55J5, 684
         KK677, 28
         KTJJT, 220
         QQQJA, 483
         """;

   /**
    * @throws IOException
    */
   @Test
   void testRank() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc7b();
         app.readInput(input);

         final var ranked = app.rank();

         System.out.println("Ranked = " + ranked);

         assertEquals(765, ranked.get(0).getBid());
         assertEquals(28, ranked.get(1).getBid());
         assertEquals(684, ranked.get(2).getBid());
         assertEquals(483, ranked.get(3).getBid());
         assertEquals(220, ranked.get(4).getBid());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadInputs() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc7b();
         app.readInput(input);

         assertNotNull(app);

         final var hands = app.getHands();
         assertNotNull(hands);

         assertEquals(5, hands.size());

         var card = hands.remove(0);
         assertEquals("32T3K", card.getCards());
         assertEquals(765, card.getBid());

         card = hands.remove(0);
         assertEquals("T55J5", card.getCards());
         assertEquals(684, card.getBid());

         card = hands.remove(0);
         assertEquals("KK677", card.getCards());
         assertEquals(28, card.getBid());

         card = hands.remove(0);
         assertEquals("KTJJT", card.getCards());
         assertEquals(220, card.getBid());

         card = hands.remove(0);
         assertEquals("QQQJA", card.getCards());
         assertEquals(483, card.getBid());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testScore() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc7b();
         app.readInput(input);
         app.rank();

         final long score = app.calcScore();
         assertEquals(5905, score);
      }
   }
}
