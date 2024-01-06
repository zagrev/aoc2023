/**
 *
 */
package com.minethurn.aoc2023;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

/**
 *
 */
class Aoc8bTest
{
   /** the unit test data */
   String data = """
         LR

         11A = (11B, XXX)
         11B = (XXX, 11Z)
         11Z = (11B, XXX)
         22A = (22B, XXX)
         22B = (22C, 22C)
         22C = (22Z, 22Z)
         22Z = (22B, 22B)
         XXX = (XXX, XXX)
         """;

   /**
    * @throws IOException
    */
   @Test
   void testParallelPathLengths() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc8b();
         app.readInput(input);

         assertEquals(6, app.getPathLength());
      }
   }

   /**
    * @throws IOException
    */
   @Test
   void testParallelPaths() throws IOException
   {
      try (var input = new BufferedReader(new StringReader(data)))
      {
         final var app = new Aoc8b();
         app.readInput(input);

         final var startNodes = app.getStartNodes();

         assertEquals(2, startNodes.size());

         assertEquals("11A", startNodes.get(0).getName());
         assertEquals("22A", startNodes.get(1).getName());
      }
   }
}
