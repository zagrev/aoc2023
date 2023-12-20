/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Aoc7
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc7 app = new Aoc7();

      try (var resource = Aoc7.class.getResourceAsStream("/aoc7-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         app.readInput(input);

      }
   }

   /** the strategy to use for this card game */
   private A7Strategy strategy = new A7CardStrategy();

   /** the hands in this game */
   private List<CamelCardHand> hands = new ArrayList<>();

   /**
    * @return the hands
    */
   List<CamelCardHand> getHands()
   {
      return hands;
   }

   /**
    * @return the strategy
    */
   public A7Strategy getStrategy()
   {
      return strategy;
   }

   /**
    * @return ranked list of hands
    * @throws UnsupportedEncodingException
    */
   public List<CamelCardHand> rank() throws UnsupportedEncodingException
   {
      return strategy.rank(hands);
   }

   /**
    * @param input
    * @throws IOException
    */
   public void readInput(final BufferedReader input) throws IOException
   {
      String line;

      while ((line = input.readLine()) != null)
      {
         final var parts = line.split(", +");
         final CamelCardHand hand = new CamelCardHand(parts[0], Integer.parseInt(parts[1]));
         hands.add(hand);
      }
   }

   /**
    * @param hands
    *           the hands to set
    */
   void setHands(final List<CamelCardHand> hands)
   {
      this.hands = hands;
   }

   /**
    * @param strategy
    *           the strategy to set
    */
   public void setStrategy(final A7Strategy strategy)
   {
      this.strategy = strategy;
   }
}
