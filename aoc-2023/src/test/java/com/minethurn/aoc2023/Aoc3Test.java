/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc3Test
{
   /**
    * @throws IOException
    */
   @Test
   void testCountPossible() throws IOException
   {
      final Aoc3 app = new Aoc3();

      try (var resource = Aoc3.class.getResourceAsStream("/aoc3-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         final List<Integer> parts = app.readPartNumbers(input);

         assertNotNull(parts);
         assertEquals(8, parts.size());

         assertEquals(467, parts.get(0));
         assertEquals(35, parts.get(1));
         assertEquals(633, parts.get(2));
         assertEquals(617, parts.get(3));
         assertEquals(592, parts.get(4));
         assertEquals(664, parts.get(5));
         assertEquals(755, parts.get(6));
         assertEquals(598, parts.get(7));
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testCountPossibleEdgeCases() throws IOException
   {
      final Aoc3 app = new Aoc3();

      final String data = """
            .610..821...918...
            ..&...............
            ........$..693....
            .+.....343.../....
            .217..............
            ......*....945....
            .......491.+...662
            ................*.
            92..*86......456..
            ...8....876.......
            ........*....+788.
            """;
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final List<Integer> parts = app.readPartNumbers(input);

         System.out.println("parts = " + parts);
         assertNotNull(parts);
         assertEquals(12, parts.size());

         assertEquals(610, parts.get(0));
         assertEquals(343, parts.get(1));
         assertEquals(217, parts.get(2));
         assertEquals(693, parts.get(3));
         assertEquals(491, parts.get(4));
         assertEquals(945, parts.get(5));
         assertEquals(662, parts.get(6));
         assertEquals(456, parts.get(7));
         assertEquals(86, parts.get(8));
         assertEquals(8, parts.get(9));
         assertEquals(876, parts.get(10));
         assertEquals(788, parts.get(11));
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testCountSum() throws IOException
   {
      final Aoc3 app = new Aoc3();

      try (var resource = Aoc3.class.getResourceAsStream("/aoc3-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         assertEquals(4361, app.sumPartNumbers(input));
      }
   }

}
