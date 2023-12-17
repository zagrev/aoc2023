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
public class Aoc2b
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc2b app = new Aoc2b(12, 13, 14);

      try (var resource = Aoc2b.class.getResourceAsStream("/aoc2-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         long total = 0;
         final var g = app.readGames(input);

         for (final CubeGame value : g)
         {
            total += Aoc2b.power(value);
         }
         System.out.println("Ao2b total = " + total);
      }
   }

   /**
    * get the power of th game
    *
    * @param game
    * @return the max red * max green * max blue
    */
   public static int power(final CubeGame game)
   {
      return game.getMaxRed() * game.getMaxGreen() * game.getMaxBlue();
   }

   /** the number of red cubes in the bag */
   private final Bag bag;

   /**
    * @param red
    *           the number of red cubes in the bag
    * @param green
    *           the number of green cubes in the bag
    * @param blue
    *           the number of blue cubes in the bag
    */
   Aoc2b(final int red, final int green, final int blue)
   {
      bag = new Bag(red, green, blue);
   }

   /**
    * @param input
    * @throws IOException
    * @return the set of games
    */
   public List<CubeGame> readGames(final BufferedReader input) throws IOException
   {
      final ArrayList<CubeGame> games = new ArrayList<>();

      String definition;
      while ((definition = input.readLine()) != null)
      {
         final var names = definition.split(":");
         if (!names[0].startsWith("Game "))
         {
            System.err.println("Invalid line: " + definition);
            throw new IOException("Invalid game definition: " + definition);
         }

         final var id = Integer.valueOf(names[0].substring(5).trim());
         final var newGame = new CubeGame();
         newGame.setId(id.intValue());

         final var grabs = names[1].split(";");
         for (final var grab : grabs)
         {
            final var cubes = grab.split(",");
            for (final var type : cubes)
            {
               final var each = type.trim().split(" ");

               final var count = Integer.parseInt(each[0]);
               switch (each[1])
               {
               case "red":
                  if (count > newGame.getMaxRed())
                  {
                     newGame.setMaxRed(count);
                  }
                  break;
               case "green":
                  if (count > newGame.getMaxGreen())
                  {
                     newGame.setMaxGreen(count);
                  }
                  break;
               case "blue":
                  if (count > newGame.getMaxBlue())
                  {
                     newGame.setMaxBlue(count);
                  }
                  break;
               default:
                  throw new IOException("Invalid color: " + each[1]);
               }
            } // for type in cube
         } // for grab in game
         games.add(newGame);
      } // while definition
      return games;
   }

   /**
    * @param games
    * @return the sub of the game numbers that are possible given the red,green,blue in the bag
    */
   public List<Integer> sumPossible(final List<CubeGame> games)
   {
      final ArrayList<Integer> possibleGames = new ArrayList<>();

      for (final var game : games)
      {
         if (bag.get("red") >= game.getMaxRed() && bag.get("green") >= game.getMaxGreen()
               && bag.get("blue") >= game.getMaxBlue())
         {
            possibleGames.add(Integer.valueOf(game.getId()));
         }
      }
      return possibleGames;
   }

}
