/**
 *
 */
package com.minethurn.aoc2023;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 */
public interface A7Strategy
{
   /** 5 of the same card */
   static final int FIVE_OF_A_KIND = 6;
   /** 4 of the same card */
   static final int FOUR_OF_A_KIND = 5;
   /** 3 of one card, 2 of the other */
   static final int FULL_HOUSE = 4;
   /** 3 of the same card */
   static final int THREE_OF_A_KIND = 3;
   /** a pair of one kind, and another pair of a different kind */
   static final int TWO_PAIR = 2;
   /** 2 of the same card */
   static final int ONE_PAIR = 1;
   /** no duplicates at all */
   static final int HIGH_CARD = 0;

   /**
    * return an ordered list of the hands, ranked highest to lowest
    *
    * @param original
    * @return the ranked list
    * @throws UnsupportedEncodingException
    */
   List<CamelCardHand> rank(List<CamelCardHand> original) throws UnsupportedEncodingException;
}
