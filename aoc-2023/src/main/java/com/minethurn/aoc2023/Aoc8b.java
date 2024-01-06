/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
/**
 *
 */
/**
 *
 */
public class Aoc8b
{
   /**
    * @param numbers
    * @return the greatest common denominator of the array of numbers
    */
   public static Long gcd(final Long... numbers)
   {
      return Arrays.stream(numbers).reduce(Long.valueOf(0), (x, y) -> gcd(x, y));
   }

   /**
    * @param x
    * @param y
    * @return the gcd, if any, of the 2 values
    */
   private static Long gcd(final Long x, final Long y)
   {
      if (y.longValue() != 0)
      {
         final long div = x.longValue() % y.longValue();
         return gcd(y, Long.valueOf(div));
      }
      return x;
   }

   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc8b app = new Aoc8b();

      try (var resource = Aoc8b.class.getResourceAsStream("/aoc8-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         app.readInput(input);
         System.out.println(String.format("Aoc8b = %,d", app.getLcm()));
      }
   }

   /** the path we should take */
   private String path;

   /** the nodes of the tree */
   private HashMap<String, A8Node> nodes = new HashMap<>();

   public long getLcm()
   {
      final List<A8Node> curNodes = getStartNodes();
      final ArrayList<Long> lengths = new ArrayList<>();
      System.out.println("Starting with " + curNodes.size() + " items");

      for (final var node : curNodes)
      {
         final var pathLength = getPathLength(node);
         lengths.add(Long.valueOf(pathLength));
      }

      return lcm(lengths).longValue();
   }

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
    * @return the number of times we had to traverse the path to get to the end node
    */
   public long getPathLength()
   {
      long pathLength = 0;
      List<A8Node> curNodes = getStartNodes();
      System.out.println("Starting with " + curNodes.size() + " items");
      boolean done = false;

      while (!done)
      {
         for (int offset = 0; !done && offset < path.length(); offset++)
         {
            final var turn = path.substring(offset, offset + 1);
            pathLength++;

            switch (turn)
            {
            case "R":
               curNodes = curNodes.stream().map((node) -> node.getRight()).toList();
               break;

            case "L":
               curNodes = curNodes.stream().map((node) -> node.getLeft()).toList();
               break;

            default:
               System.err.println("What kinda turn is " + turn);
            }

            if (pathLength % 1_000_000_000 == 0)
            {
               System.out.println(String.format("tried %,d steps", Long.valueOf(pathLength)));
            }
            if (curNodes.stream().filter((node) -> node.getName().endsWith("Z")).count() > curNodes.size() / 2)
            {
               System.out.println("  " + curNodes);
            }
            done = curNodes.stream().allMatch((node) -> node.getName().endsWith("Z"));
         }
      }
      return pathLength;
   }

   /**
    * @param dest
    * @return the number of times we had to traverse the path to get to the end node
    */
   public int getPathLength(final A8Node start)
   {
      int pathLength = 0;
      var curNode = start;

      while (!curNode.getName().endsWith("Z"))
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
    * @return the nodes that end with "A"
    */
   List<A8Node> getStartNodes()
   {
      return nodes.keySet().stream().filter((node) -> node.endsWith("A")).map((name) -> nodes.get(name)).toList();
   }

   /**
    * @param numbers
    * @return the lcm of the given list
    */
   public Long lcm(final List<Long> numbers)
   {
      return numbers.stream().reduce(Long.valueOf(1), (x, y) -> {
         final long div = y.longValue() / gcd(x, y).longValue();
         return Long.valueOf(x.longValue() * div);
      });
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
         getOrCreateNode(fields[0], fields[1], fields[2]);
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
