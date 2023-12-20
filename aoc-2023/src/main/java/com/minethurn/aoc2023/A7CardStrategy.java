/**
 *
 */
package com.minethurn.aoc2023;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class A7CardStrategy implements A7Strategy
{

   /**
    * @param c1
    * @param c2
    * @return 1 if c1 is greater , -1 if c2 is greater, or 0 if c1 = c2
    */
   public int compareCards(final char c1, final char c2)
   {

      if (Character.isDigit(c1))
      {
         // if c2 is a number, return the difference between c2 and c1
         if (Character.isDigit(c2))
         {
            return c1 - c2;
         }
         // if c1 is a number, and c2 is a letter, then c2 is bigger
         return -1;
      }

      // c1 is not a number, so if c2 is, then c1 wins
      if (Character.isDigit(c2))
      {
         return 1;
      }

      // ok, so c1 and c2 are face cards
      switch (c1)
      {
      case 'A':
         if (c2 == 'A')
         {
            return 0;
         }
         break;

      case 'K':
         if (c2 == 'A')
         {
            return -1;
         }
         if (c2 == 'K')
         {
            return 0;
         }
         break;

      case 'Q':
         if (c2 == 'A' || c2 == 'K')
         {
            return -1;
         }
         if (c2 == 'Q')
         {
            return 0;
         }
         break;

      case 'J':
         if (c2 == 'T')
         {
            return 1;
         }
         if (c2 == 'J')
         {
            return 0;
         }
         return -1;

      case 'T':
         if (c2 == 'T')
         {
            return 0;
         }
         return -1;

      default:
         throw new IllegalArgumentException("Invalid card: :" + c1);
      }
      return 1;
   }

   /**
    * determine if the cards have a names solution as follows:
    *
    * <pre>
    * Value Name
    * 6 five of a kind
    * 5 four of a kind
    * 4 full house
    * 3 three of a kind
    * 2 two pair
    * 1 one pair
    * 0 high card
    * </pre>
    *
    * @param cards
    * @return the type of the hand
    * @throws UnsupportedEncodingException
    */
   public int getHandType(final String cards) throws UnsupportedEncodingException
   {
      final var bytes = cards.getBytes("utf-8");
      Arrays.sort(bytes);

      final var hand = new String(bytes, "utf-8");

      // of course the first character is the start of a run
      var inARow = 1;
      var lastChar = hand.charAt(0);

      // for N of a kind
      var longestRun = 1;

      // flags for groupings
      var hasPair = false;
      var hasTriple = false;
      var hasTwoPair = false;

      for (int i = 1; i < hand.length(); i++)
      {
         final char curChar = hand.charAt(i);

         // count up the identical cards
         if (curChar == lastChar)
         {
            inARow++;
            if (inARow > longestRun)
            {
               longestRun = inARow;
            }
         }

         // this all happens at the end of a streak
         else
         {
            hasTriple = hasTriple || inARow == 3;
            hasTwoPair = hasTwoPair || hasPair && inARow == 2;
            hasPair = hasPair || inARow == 2;

            inARow = 1;
         }
         lastChar = curChar;
      }
      hasTriple = hasTriple || inARow == 3;
      hasTwoPair = hasTwoPair || hasPair && inARow == 2;
      hasPair = hasPair || inARow == 2;

      if (longestRun == 5)
      {
         return 6;
      }
      if (longestRun == 4)
      {
         return 5;
      }
      if (hasTriple && hasPair)
      {
         return 4;
      }
      if (hasTriple)
      {
         return 3;
      }
      if (hasTwoPair)
      {
         return 2;
      }
      if (hasPair)
      {
         return 1;
      }
      return 0;
   }

   /**
    * @throws UnsupportedEncodingException
    */
   @Override
   public List<CamelCardHand> rank(final List<CamelCardHand> original) throws UnsupportedEncodingException
   {
      final ArrayList<CamelCardHand> copy = new ArrayList<>(original);

      Collections.sort(copy, (a, b) -> {
         try
         {
            return sortHands(a, b);
         }
         catch (final UnsupportedEncodingException e)
         {
            return 0;
         }
      });
      return copy;
   }

   /**
    * @param a
    * @param b
    * @return 1 if a is higher, -1 if b is higher, or 0 if a==b
    * @throws UnsupportedEncodingException
    */
   public int sortHands(final CamelCardHand a, final CamelCardHand b) throws UnsupportedEncodingException
   {
      final var cards1 = a.getCards();
      final var cards2 = b.getCards();

      // check if the hands have some named type
      final var aType = getHandType(cards1);
      final var bType = getHandType(cards2);

      if (aType > bType)
      {
         return 1;
      }
      else if (bType > aType)
      {
         return -1;
      }

      var rc = 0;

      for (int i = 0; i < cards1.length(); i++)
      {
         final char c1 = cards1.charAt(i);
         final char c2 = cards2.charAt(i);

         rc = compareCards(c1, c2);
         if (rc != 0)
         {
            return rc;
         }
      }
      return rc;
   }

}
