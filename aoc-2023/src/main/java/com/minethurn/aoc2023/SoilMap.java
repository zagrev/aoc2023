/**
 *
 */
package com.minethurn.aoc2023;

import java.util.ArrayList;

/**
 *
 */
public class SoilMap
{

   /** the name of the map */
   private final String sourceType;

   /** the output type */
   private final String destType;
   /** the defined ranges */
   private final ArrayList<Range> ranges = new ArrayList<>();

   /**
    * @param sourceType
    * @param destType
    */
   public SoilMap(final String sourceType, final String destType)
   {
      this.sourceType = sourceType;
      this.destType = destType;
   }

   /**
    * @param range
    */
   public void addRange(final Range range)
   {
      ranges.add(range);
   }

   /**
    * @return the destType
    */
   public String getDestType()
   {
      return destType;
   }

   /**
    * @return the ranges
    */
   ArrayList<Range> getRanges()
   {
      return ranges;
   }

   /**
    * @return the sourceType
    */
   public String getSourceType()
   {
      return sourceType;
   }

   /**
    * map the value from all the ranges to the result
    *
    * @param source
    * @return the mapped value
    */
   public long map(final long source)
   {
      long value = source;
      for (final Range range : ranges)
      {
         // only map the value once
         if (value == source)
         {
            value = range.map(value);
         }
      }

      return value;
   }

   @Override
   public String toString()
   {
      return "SoilMap [ " + sourceType + "  -> " + destType + ", ranges=" + ranges + "]";
   }
}
