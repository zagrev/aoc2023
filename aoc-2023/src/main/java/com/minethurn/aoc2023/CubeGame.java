/**
 *
 */
package com.minethurn.aoc2023;

/**
 *
 */
public class CubeGame
{
   /** the game id */
   private int id;
   /** the max red visible during this game */
   private int maxRed;
   /** the number of green cubes in the bag */
   private int maxGreen;
   /** the number of blue cubes in the bag */
   private int maxBlue;

   /**
    * @return the id
    */
   public int getId()
   {
      return id;
   }

   /**
    * @return the maxBlue
    */
   public int getMaxBlue()
   {
      return maxBlue;
   }

   /**
    * @return the maxGreen
    */
   public int getMaxGreen()
   {
      return maxGreen;
   }

   /**
    * @return the maxRed
    */
   public int getMaxRed()
   {
      return maxRed;
   }

   /**
    * @param id
    *           the id to set
    */
   public void setId(final int id)
   {
      this.id = id;
   }

   /**
    * @param maxBlue
    *           the maxBlue to set
    */
   public void setMaxBlue(final int maxBlue)
   {
      this.maxBlue = maxBlue;
   }

   /**
    * @param maxGreen
    *           the maxGreen to set
    */
   public void setMaxGreen(final int maxGreen)
   {
      this.maxGreen = maxGreen;
   }

   /**
    * @param maxRed
    *           the maxRed to set
    */
   public void setMaxRed(final int maxRed)
   {
      this.maxRed = maxRed;
   }
}
