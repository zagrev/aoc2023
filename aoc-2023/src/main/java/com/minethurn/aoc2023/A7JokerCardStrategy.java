/**
 *
 */
package com.minethurn.aoc2023;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class A7JokerCardStrategy implements A7Strategy
{
   /**
    *
    */
   class CardCount
   {
      /** the kind of card */
      public char face;

      /** the number of those cards */
      public int count;

      /**
       * @param face
       * @param count
       */
      public CardCount(final char face, final int count)
      {
         this.face = face;
         this.count = count;
      }

      @Override
      public String toString()
      {
         return "CardCount [face=" + face + ", count=" + count + "]";
      }
   }

   /**
    * @param counts
    * @param i
    * @return the number of faces that match the given cot
    */
   private int checkCount(final ArrayList<CardCount> counts, final int i)
   {
      int count = 0;
      for (final var cc : counts)
      {
         if (cc.count == i)
         {
            count++;
         }
      }
      return count;
   }

   /**
    * @param c1
    * @param c2
    * @return 1 if c1 is greater , -1 if c2 is greater, or 0 if c1 = c2
    */
   public int compareCards(final char c1, final char c2)
   {
      // deal with jokers before other face cards
      if (c1 == 'J')
      {
         if (c2 == 'J')
         {
            return 0;
         }
         return -1;
      }
      if (c2 == 'J')
      {
         return 1;
      }

      if (Character.isDigit(c1))
      {
         // if c2 is a number, return the difference between c2 and c1
         if (Character.isDigit(c2))
         {
            return c1 - c2;
         }
         // if c2 is J, c2 is greater
         else if (c2 == 'J')
         {
            return 1;
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
    * @param cards
    * @return a list of pairs, face + count, of the cards in the hand
    * @throws UnsupportedEncodingException
    */
   private ArrayList<CardCount> countCards(final String cards) throws UnsupportedEncodingException
   {
      final ArrayList<CardCount> counts = new ArrayList<>(5);

      final var sortedCards = sortHand(cards);

      CardCount curCount = new CardCount(sortedCards.charAt(0), 1);
      counts.add(curCount);

      // now count how many of each type
      for (int i = 1; i < sortedCards.length(); i++)
      {
         final var curCard = sortedCards.charAt(i);
         if (curCard != curCount.face)
         {
            curCount = new CardCount(curCard, 0);
            counts.add(curCount);
         }
         curCount.count++;
      }
      return counts;
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
      final ArrayList<CardCount> counts = countCards(cards);

      // getting the wild card count also remove the wild cards from the hand so the highest count is only natural cards
      final int wilds = getWildcards(counts);
      final int highCount = getHighestCount(counts);

      // check for the type of hand
      switch (wilds + highCount)
      {
      // either natural 5, 4 + 1 wild, 3 + 2 wild, or 1 + 4 wild
      case 5:
         return FIVE_OF_A_KIND;

      // either natural 4, 3 + 1 wild, 2 + 2 wild, or 1 + 3 wild
      case 4:
         return FOUR_OF_A_KIND;

      // either natural 3, or 2 + 1 wild, or 1 + 2 wild
      // if we have 2 pair + 1 wild, then this is really a full house
      case 3:
         if (checkCount(counts, 2) > 0)
         {
            return FULL_HOUSE;
         }
         return THREE_OF_A_KIND;

      // either natural 2, or 1 + 1 wild
      case 2:
         if (checkCount(counts, 2) > 1)
         {
            return TWO_PAIR;
         }
         return ONE_PAIR;

      // 1 natural, 1 wild
      case 1:
         return HIGH_CARD;

      default:
         System.err.println("Invalid count of cards");
         return -1;
      }
   }

   /**
    * @param counts
    * @return the highest number of the same card
    */
   private int getHighestCount(final ArrayList<CardCount> counts)
   {
      final Optional<CardCount> cc = counts.stream().max((a, b) -> Integer.compare(a.count, b.count));
      if (cc.isPresent())
      {
         return cc.get().count;
      }
      return 0;
   }

   /**
    * return the number of wildcards, and remove the wildcards from the hand
    *
    * @param counts
    * @return the count of wildcards in the hand
    */
   private int getWildcards(final ArrayList<CardCount> counts)
   {
      for (int i = 0; i < counts.size(); i++)
      {
         if (counts.get(i).face == 'J')
         {
            final int count = counts.get(i).count;
            counts.remove(i);
            return count;
         }
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
    * @param cards
    * @return the same hand but with the cards sorted by face
    * @throws UnsupportedEncodingException
    */
   private String sortHand(final String cards) throws UnsupportedEncodingException
   {
      final var bytes = cards.getBytes("utf-8");
      Arrays.sort(bytes);

      return new String(bytes, "utf-8");
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
