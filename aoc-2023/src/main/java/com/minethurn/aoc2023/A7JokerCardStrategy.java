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

   /** when a < b */
   public static final int CARDS_LT = -1;
   /** when a > b */
   public static final int CARDS_GT = 1;
   /** when cards a == b */
   public static final int CARDS_EQUAL = 0;

   /** by default, sort lowest to highest */
   private boolean reverseSort = false;

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
    * compare the given cards. By default it sorts low to high. If {@link #reverseSort} is {@code true} then it sorts
    * high to low.
    *
    * @param c1
    * @param c2
    * @return the value 0 if x == y;a value less than 0 if x < y; anda value greater than 0 if x > y. If
    *         {@link #reverseSort} is {@code true}, then the opposite
    */
   public int compareCards(final char c1, final char c2)
   {
      final var greaterThan = reverseSort ? CARDS_LT : CARDS_GT;
      final var lessThan = reverseSort ? CARDS_GT : CARDS_LT;

      // deal with Wild cards before other face cards
      if (c1 == 'J')
      {
         if (c2 == 'J')
         {
            return CARDS_EQUAL;
         }
         return lessThan;
      }
      if (c2 == 'J')
      {
         return greaterThan;
      }

      // if c1 is a number and not a face card
      if (Character.isDigit(c1))
      {
         // if c2 is a number, then do numeric compare
         if (Character.isDigit(c2))
         {
            return c1 > c2 ? greaterThan : c1 < c2 ? lessThan : CARDS_EQUAL;
         }

         // if c1 is a number, and c2 is a letter, then c2 is bigger
         return lessThan;
      }

      // c1 is not a number, so if c2 is, then c1 wins
      if (Character.isDigit(c2))
      {
         return greaterThan;
      }

      // ok, so c1 and c2 are face cards (Wild cards already handled above)
      var answer = greaterThan;
      switch (c1)
      {
      case 'A':
         // ace beats all cards except ace
         if (c2 == 'A')
         {
            answer = CARDS_EQUAL;
         }
         break;

      case 'K':
         // king < ace, king > all other
         if (c2 == 'A')
         {
            answer = lessThan;
         }
         if (c2 == 'K')
         {
            answer = CARDS_EQUAL;
         }
         break;

      case 'Q':
         if (c2 == 'A' || c2 == 'K')
         {
            answer = lessThan;
         }
         if (c2 == 'Q')
         {
            answer = CARDS_EQUAL;
         }
         break;

      case 'T':
         // all face cards other than T are greater than T
         if (c2 == 'T')
         {
            answer = CARDS_EQUAL;
         }
         else
         {
            answer = lessThan;
         }
         break;

      default:
         throw new IllegalArgumentException("Invalid card: :" + c1);
      }
      return answer;
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
         if (checkCount(counts, 3) > 0 && checkCount(counts, 2) > 0 || checkCount(counts, 2) > 1
               || wilds == 2 && checkCount(counts, 2) > 0)
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

      // all cards must be natural
      case 1:
         return HIGH_CARD;

      default:
         System.err.println("Invalid count of cards");
         return CARDS_GT;
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
    * @return the reverseSort
    */
   boolean isReverseSort()
   {
      return reverseSort;
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
    * @param reverseSort
    *           the reverseSort to set
    */
   void setReverseSort(final boolean reverseSort)
   {
      this.reverseSort = reverseSort;
   }

   /**
    * create a sorted version of the handd
    *
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
      final var greaterThan = reverseSort ? CARDS_LT : CARDS_GT;
      final var lessThan = reverseSort ? CARDS_GT : CARDS_LT;

      final var aCards = a.getCards();
      final var bCards = b.getCards();

      // check if the hands have some named type
      final var aType = getHandType(aCards);
      final var bType = getHandType(bCards);

      var rc = 0;

      if (aType > bType)
      {
         rc = greaterThan;
      }
      else if (bType > aType)
      {
         rc = lessThan;
      }

      for (int i = 0; i < aCards.length() && rc == 0; i++)
      {
         final char c1 = aCards.charAt(i);
         final char c2 = bCards.charAt(i);

         rc = compareCards(c1, c2); // compareCards handles sort order here
      }
// System.out.println("sortHands: " + aCards + " (" + typeToString(aType) + ") "
// + (rc == greaterThan ? ">" : rc == lessThan ? "<" : "=") + " " + bCards + " (" + typeToString(bType) + ")");
      return rc;
   }

   /**
    * @param type
    * @return a human readable string for the type
    */
   private String typeToString(final int type)
   {
      switch (type)
      {
      case 6:
         return "five";
      case 5:
         return "four";
      case 4:
         return "full";
      case 3:
         return "three";
      case 2:
         return "2pair";
      case 1:
         return "pair";
      case 0:
         return "high";
      default:
         return "unknown - " + type;
      }
   }

}
