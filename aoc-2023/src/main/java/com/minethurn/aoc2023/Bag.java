/**
 *
 */
package com.minethurn.aoc2023;

import java.util.HashMap;

/**
 *
 */
public class Bag
{
   /** the count of colors and counts */
   private final HashMap<String, Integer> cubes = new HashMap<>();

   /**
    * blank constructor
    */
   public Bag()
   {
   }

   /**
    * @param red
    * @param green
    * @param blue
    */
   public Bag(final int red, final int green, final int blue)
   {
      cubes.put("red", Integer.valueOf(red));
      cubes.put("green", Integer.valueOf(green));
      cubes.put("blue", Integer.valueOf(blue));
   }

   /**
    * @param color
    * @return the count as an integer
    */
   public int get(final String color)
   {
      final Integer value = cubes.get(color);
      if (value != null)
      {
         return value.intValue();
      }
      return 0;
   }

   /**
    * @param color
    * @param count
    */
   public void set(final String color, final Integer count)
   {
      cubes.put(color, count);
   }

}
