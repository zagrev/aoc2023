/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class Aoc4b
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc4b app = new Aoc4b();

      long total = 0;

      try (var resource = Aoc4b.class.getResourceAsStream("/aoc4-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         total = app.totalPoints(input);
         System.out.println("Aoc4 total = " + total);
      }
   }

   /**
    * @param line
    * @return a new scratch card
    */
   public ScratchCard readCard(final String line)
   {
      final ScratchCard card = new ScratchCard();

      final var sections = line.trim().split("[:|]");

      if (sections[0].startsWith("Card "))
      {
         card.setId(Integer.parseInt(sections[0].substring(5).trim()));
      }
      else
      {
         System.err.println("Cannot read line: " + line);
      }

      var numbers = sections[1].trim().split(" +");
      for (final var item : numbers)
      {
         card.addWinner(Integer.valueOf(item));
      }
      numbers = sections[2].trim().split(" +");
      for (final var item : numbers)
      {
         card.addNumber(Integer.valueOf(item));
      }

      return card;
   }

   /**
    * @param input
    * @return the total value of the card
    * @throws IOException
    */
   public long totalPoints(final BufferedReader input) throws IOException
   {
      long total = 0;
      String line;

      while ((line = input.readLine()) != null)
      {
         final ScratchCard card = readCard(line);
         total += card.getScore().intValue();
      }
      return total;
   }
}
