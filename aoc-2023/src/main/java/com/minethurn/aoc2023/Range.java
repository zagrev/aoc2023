/**
 *
 */
package com.minethurn.aoc2023;

/**
 *
 */
public class Range
{
   /** the input value */
   private final long source;

   /** the output value */
   private final long dest;
   /** the count of numbers in the range */
   private final long count;

   /**
    * @param source
    * @param dest
    * @param count
    */
   public Range(final long source, final long dest, final long count)
   {
      this.source = source;
      this.dest = dest;
      this.count = count;
   }

   /**
    * @return the count
    */
   long getCount()
   {
      return count;
   }

   /**
    * @return the dest
    */
   long getDest()
   {
      return dest;
   }

   /**
    * @return the source
    */
   long getSource()
   {
      return source;
   }

   /**
    * map the source value into the destination range
    *
    * @param value
    * @return the destination value if the value was within the range, the original value otherwise.
    */
   public long map(final long value)
   {
      if (value >= source && value < source + count)
      {
         return dest - source + value;
      }
      return value;
   }

   @Override
   public String toString()
   {
      return "Range [source=" + source + ", dest=" + dest + ", count=" + count + "]";
   }
}
