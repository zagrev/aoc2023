/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Aoc4
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc4 app = new Aoc4();

      try (var resource = Aoc4.class.getResourceAsStream("/aoc4-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         final List<ScratchCard> cards = app.readAllCards(input);
         final List<ScratchCard> allWithDups = app.processCards(cards);

         System.out.println("Aoc4b total = " + allWithDups.size());
      }
   }

   /**
    * process a scratch card. Each scratch card will:
    * <ul>
    * <li>add the card to the output list
    * <li>add 1 additional cards to the output to match the score
    * <li>process each additional card, adding all the card they win to the list as well.
    * </ul>
    *
    * @param originals
    * @param currentCard
    * @param currentOffset
    * @return the list of cards includes by the given card
    */
   public List<ScratchCard> processCard(final List<ScratchCard> originals, final ScratchCard currentCard,
         final int currentOffset)
   {
      final ArrayList<ScratchCard> newCards = new ArrayList<>();

      newCards.add(currentCard);
      final int count = currentCard.getScore().intValue();

      for (int i = currentOffset + 1; i < currentOffset + count + 1; i++)
      {
         newCards.addAll(processCard(originals, originals.get(i), i));
      }

      return newCards;
   }

   /**
    * @param originals
    * @return the list of original cards, plus all the cards that were won
    */
   public List<ScratchCard> processCards(final List<ScratchCard> originals)
   {
      final ArrayList<ScratchCard> dups = new ArrayList<>();

      for (int index = 0; index < originals.size(); index++)
      {
         dups.addAll(processCard(originals, originals.get(index), index));
      }
      return dups;
   }

   /**
    * @param input
    * @return the list of all the cards in the given input
    * @throws IOException
    */
   public List<ScratchCard> readAllCards(final BufferedReader input) throws IOException
   {
      final ArrayList<ScratchCard> list = new ArrayList<>();

      String line;
      while ((line = input.readLine()) != null)
      {
         list.add(readCard(line));
      }
      return list;
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
