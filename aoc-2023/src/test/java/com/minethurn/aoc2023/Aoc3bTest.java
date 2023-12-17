/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc3bTest
{
   /**
    * @throws IOException
    */
   @Test
   void testGearNumbers() throws IOException
   {
      final Aoc3b app = new Aoc3b();

      try (var resource = Aoc3.class.getResourceAsStream("/aoc3-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         final List<Integer> gears = app.getGearRatios(input);

         System.out.println("Gear ratios = " + gears);
         assertNotNull(gears);
         assertEquals(2, gears.size());

         assertEquals(16345, gears.get(0));
         assertEquals(451490, gears.get(1));
      }
   }

}
