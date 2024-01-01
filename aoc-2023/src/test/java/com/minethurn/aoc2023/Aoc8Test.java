/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 */
class Aoc8Test
{
   /** the unit test data */
   String data = """
         RL

         AAA = (BBB, CCC)
         BBB = (DDD, EEE)
         CCC = (ZZZ, GGG)
         DDD = (DDD, DDD)
         EEE = (EEE, EEE)
         GGG = (GGG, GGG)
         ZZZ = (ZZZ, ZZZ)
         """;

   /**
    * @param end
    * @param path
    * @param expectedLength
    * @throws IOException
    */
   @ParameterizedTest
   @CsvSource(
   { "AAA, RL, 0", "BBB, L, 2", "ZZZ, RL, 2" })
   void testFollowRoute(final String end, final String path, final int expectedLength) throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc8();
         app.readInput(input);

         assertEquals(expectedLength, app.getPathLength(end));
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadInputs() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc8();
         app.readInput(input);

         assertNotNull(app);

         final HashMap<String, A8Node> nodes = app.getNodes();
         assertEquals(7, nodes.keySet().size());

         final A8Node a = nodes.get("AAA");
         assertEquals("AAA", a.getName());
         assertNotNull(a.getLeft());
         assertNotNull(a.getRight());

         final A8Node b = a.getLeft();
         assertEquals("BBB", b.getName());
         assertNotNull(b.getLeft());
         assertEquals("DDD", b.getLeft().getName());
         assertNotNull(b.getRight());
         assertEquals("EEE", b.getRight().getName());

         final A8Node c = a.getRight();
         assertEquals("CCC", c.getName());
         assertNotNull(c.getLeft());
         assertEquals("ZZZ", c.getLeft().getName());
         assertNotNull(c.getRight());
         assertEquals("GGG", c.getRight().getName());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testReadRoute() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc8();
         app.readInput(input);

         assertNotNull(app);
         assertNotNull(app.getPath());
         assertEquals("RL", app.getPath());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testSecondRoute() throws IOException
   {
      final var secondRoute = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
            """;
      try (var input = new BufferedReader(new StringReader(secondRoute)))
      {
         final var app = new Aoc8();
         app.readInput(input);

         assertEquals(6, app.getPathLength("ZZZ"));
      }

   }
}
