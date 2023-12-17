/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc2bTest
{

   /**
    * @throws IOException
    */
   @Test
   void testPower() throws IOException
   {
      final String games = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;
      final Aoc2b app = new Aoc2b(12, 13, 14);

      final var input = new BufferedReader(new StringReader(games));
      final var g = app.readGames(input);
      assertEquals(5, g.size());

      assertEquals(48, Aoc2b.power(g.get(0)));
      assertEquals(12, Aoc2b.power(g.get(1)));
      assertEquals(1560, Aoc2b.power(g.get(2)));
      assertEquals(630, Aoc2b.power(g.get(3)));
      assertEquals(36, Aoc2b.power(g.get(4)));
   }
}
