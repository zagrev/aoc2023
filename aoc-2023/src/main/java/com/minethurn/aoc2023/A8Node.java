/**
 *
 */
package com.minethurn.aoc2023;

/**
 * Basic binary tree node
 */
public class A8Node
{
   /** the name of this node */
   private String name;
   /** the node to the left */
   private A8Node left;
   /** the node to the right */
   private A8Node right;

   /**
    * @return the left
    */
   A8Node getLeft()
   {
      return left;
   }

   /**
    * @return the name
    */
   String getName()
   {
      return name;
   }

   /**
    * @return the right
    */
   A8Node getRight()
   {
      return right;
   }

   /**
    * @param left
    *           the left to set
    */
   void setLeft(final A8Node left)
   {
      this.left = left;
   }

   /**
    * @param name
    *           the name to set
    */
   void setName(final String name)
   {
      this.name = name;
   }

   /**
    * @param right
    *           the right to set
    */
   void setRight(final A8Node right)
   {
      this.right = right;
   }

   @Override
   public String toString()
   {
      return name + ", [" + (left == null ? "null" : left.getName()) + "" + " "
            + (right == null ? "null" : right.getName()) + "]";
   }
}
