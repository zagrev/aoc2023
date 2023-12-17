/**
 *
 */
package com.minethurn.aoc2023;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ScratchCard
{
   /** the card number */
   private int id;
   /** the winning numbers */
   private final List<Integer> winners = new ArrayList<>();
   /** card numbers */
   private final List<Integer> numbers = new ArrayList<>();

   /**
    * add the given number to the card
    *
    * @param num
    */
   public void addNumber(final Integer num)
   {
      numbers.add(num);
   }

   /**
    * add the given number to the winners on the card
    *
    * @param num
    */
   public void addWinner(final Integer num)
   {
      winners.add(num);
   }

   /**
    * @return the id
    */
   public int getId()
   {
      return id;
   }

   /**
    * @return the numbers
    */
   List<Integer> getNumbers()
   {
      return numbers;
   }

   /**
    * calculates and returns the score of the card. the score is doubled for every winning number
    *
    * @return the score (1, 2, 4, 8, etc.)
    */
   public Integer getScore()
   {
      int score = 0;
      for (final Integer num : numbers)
      {
         if (winners.contains(num))
         {
            score += 1;
         }
      }
      return Integer.valueOf(score);
   }

   /**
    * @return the winners
    */
   List<Integer> getWinners()
   {
      return winners;
   }

   /**
    * @param id
    *           the id to set
    */
   public void setId(final int id)
   {
      this.id = id;
   }

   @Override
   public String toString()
   {
      return "ScratchCard [id=" + id + ", winners=" + winners + ", numbers=" + numbers + "]";
   }
}
