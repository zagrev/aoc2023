/**
 *
 */
package com.minethurn.aoc2023;

/**
 *
 */
public class CamelCardHand
{
   /** the cards in this hand */
   private String cards = "";
   /** the bid for this hand */
   private int bid = 0;

   /**
    * @param cards
    * @param bid
    */
   public CamelCardHand(final String cards, final int bid)
   {
      this.setCards(cards);
      this.setBid(bid);
   }

   /**
    * @return the bid
    */
   public int getBid()
   {
      return bid;
   }

   /**
    * @return the cards
    */
   public String getCards()
   {
      return cards;
   }

   /**
    * @param bid
    *           the bid to set
    */
   public void setBid(final int bid)
   {
      this.bid = bid;
   }

   /**
    * @param cards
    *           the cards to set
    */
   public void setCards(final String cards)
   {
      this.cards = cards;
   }

   @Override
   public String toString()
   {
      return "CamelCardHand [cards=" + cards + ", bid=" + bid + "]";
   }
}
