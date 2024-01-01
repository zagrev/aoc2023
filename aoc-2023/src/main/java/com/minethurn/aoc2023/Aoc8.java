/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 */
/**
 *
 */
/**
 *
 */
public class Aoc8
{
   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc8 app = new Aoc8();

      try (var resource = Aoc8.class.getResourceAsStream("/aoc8-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         app.readInput(input);
         System.out.println("Aoc8 = " + app.getPathLength("ZZZ"));
      }
   }

   /** the path we should take */
   private String path;

   /** the nodes of the tree */
   private HashMap<String, A8Node> nodes = new HashMap<>();

   /**
    * @param name
    * @return the node with a given name, this will create the node if it does not already exist
    */
   private A8Node getNode(final String name)
   {
      A8Node node = nodes.get(name);
      if (node == null)
      {
         node = new A8Node();
         node.setName(name);
         nodes.put(name, node);
      }
      return node;
   }

   /**
    * @return the nodes
    */
   HashMap<String, A8Node> getNodes()
   {
      return nodes;
   }

   /**
    * @param name
    * @param left
    * @param right
    * @return the existing node with the given name, or a new node with the name
    */
   A8Node getOrCreateNode(final String name, final String left, final String right)
   {

      final var node = getNode(name);
      final var leftNode = getNode(left);
      final var rightNode = getNode(right);

      node.setLeft(leftNode);
      node.setRight(rightNode);

      return node;
   }

   /**
    * @return the path
    */
   String getPath()
   {
      return path;
   }

   /**
    * @param dest
    * @return the number of times we had to traverse the path to get to the end node
    */
   public int getPathLength(final String dest)
   {
      int pathLength = 0;
      var curNode = getNode("AAA");

      while (!curNode.getName().equals(dest))
      {
         final var offset = pathLength++ % path.length();
         final var turn = path.substring(offset, offset + 1);

         switch (turn)
         {
         case "R":
            curNode = curNode.getRight();
            break;

         case "L":
            curNode = curNode.getLeft();
            break;

         default:
            System.err.println("What kinda turn is " + turn);
         }
      }
      return pathLength;
   }

   /**
    * @param input
    * @throws IOException
    */
   public void readInput(final BufferedReader input) throws IOException
   {
      String line;

      path = input.readLine();
      if (path == null || path.isBlank())
      {
         throw new IOException("No data found");
      }
      path = path.strip();

      while ((line = input.readLine()) != null)
      {
         if (line.isBlank())
         {
            continue;
         }

         final var fields = line.split("[ =(){,]+");
         final var node = getOrCreateNode(fields[0], fields[1], fields[2]);
      }
   }

   /**
    * @param nodes
    *           the nodes to set
    */
   void setNodes(final HashMap<String, A8Node> nodes)
   {
      this.nodes = nodes;
   }

   /**
    * @param path
    *           the path to set
    */
   void setPath(final String path)
   {
      this.path = path;
   }
}
