/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc2Test
{
   /**
    * @throws IOException
    */
   @Test
   void testCountPossible() throws IOException
   {
      final String games = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;
      final Aoc2 app = new Aoc2(12, 13, 14);

      final var input = new BufferedReader(new StringReader(games));
      final var g = app.readGames(input);
      assertEquals(5, g.size());

      final var possibles = app.sumPossible(g);

      assertNotNull(possibles);
      assertEquals(3, possibles.size());

      assertEquals(1, possibles.get(0).intValue());
      assertEquals(2, possibles.get(1).intValue());
      assertEquals(5, possibles.get(2).intValue());
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadGames() throws IOException
   {
      final String games = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;
      final Aoc2 app = new Aoc2(12, 13, 14);

      final var input = new BufferedReader(new StringReader(games));
      final var g = app.readGames(input);
      assertEquals(5, g.size());

      CubeGame game = g.get(0);
      assertEquals(1, game.getId());
      assertEquals(4, game.getMaxRed());
      assertEquals(2, game.getMaxGreen());
      assertEquals(6, game.getMaxBlue());

      game = g.get(4);
      assertEquals(5, game.getId());
      assertEquals(6, game.getMaxRed());
      assertEquals(3, game.getMaxGreen());
      assertEquals(2, game.getMaxBlue());
   }
}
