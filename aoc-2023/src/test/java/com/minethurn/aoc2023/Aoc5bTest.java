/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc5bTest
{
   /** the seed data for testing */
   String data = """
         seeds: 79 14 55 13

         seed-to-soil map:
         50 98 2
         52 50 48

         soil-to-fertilizer map:
         0 15 37
         37 52 2
         39 0 15

         fertilizer-to-water map:
         49 53 8
         0 11 42
         42 0 7
         57 7 4

         water-to-light map:
         88 18 7
         18 25 70

         light-to-temperature map:
         45 77 23
         81 45 19
         68 64 13

         temperature-to-humidity map:
         0 69 1
         1 0 69

         humidity-to-location map:
         60 56 37
         56 93 4
         """;

   /**
    * @param start
    * @param expected
    * @throws IOException
    */
   @ParameterizedTest
   @CsvSource(
   { "79, 82", "14, 43", "55, 86", "13, 35" })
   void testFullMap(final long start, final long expected) throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc5b();
         app.readInput(input);

         final long actual = app.map(start, "seed");
         assertEquals(expected, actual);
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testMapValue() throws IOException
   {
      final var app = new Aoc5b();
      SoilMap map = null;

      try (var input = new BufferedReader(new StringReader(data)))
      {
         app.readSeeds(input);

         map = app.readMap(input);
         System.out.println("Read map: " + map);
      }

      // test range 1
      assertEquals(50, map.map(98));
      assertEquals(51, map.map(99));
      assertEquals(100, map.map(100));

      // test range 2
      assertEquals(49, map.map(49));
      assertEquals(52, map.map(50));
      assertEquals(53, map.map(51));
      assertEquals(98, map.map(96));
      assertEquals(99, map.map(97));
   }

   /**
    * @throws IOException
    * @throws ExecutionException
    */
   @Test
   void testMin() throws IOException, ExecutionException
   {
      final var app = new Aoc5b();

      try (var input = new BufferedReader(new StringReader(data)))
      {
         assertEquals(46, app.getMinLocation(input));
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadingMap() throws IOException
   {
      final var app = new Aoc5b();
      SoilMap map = null;

      try (var input = new BufferedReader(new StringReader(data)))
      {
         app.readSeeds(input);

         map = app.readMap(input);
         System.out.println("Read map: " + map);
      }

      assertEquals("seed", map.getSourceType());
      assertEquals("soil", map.getDestType());

      final var ranges = map.getRanges();
      assertNotNull(ranges);
      assertEquals(2, ranges.size());

      var range = ranges.get(0);
      assertEquals(50, range.getDest());
      assertEquals(98, range.getSource());
      assertEquals(2, range.getCount());

      range = ranges.get(1);

      assertEquals(52, range.getDest());
      assertEquals(50, range.getSource());
      assertEquals(48, range.getCount());
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadingSeeds() throws IOException
   {
      final var app = new Aoc5b();

      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var seeds = app.readSeeds(input);

         assertEquals(79, seeds.get(0));
         assertEquals(14, seeds.get(1));
         assertEquals(55, seeds.get(2));
         assertEquals(13, seeds.get(3));
      }
   }
}
