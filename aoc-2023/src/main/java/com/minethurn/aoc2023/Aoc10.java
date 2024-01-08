/**
 *
 */
package com.minethurn.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class Aoc10
{

   /**
    * @param args
    * @throws IOException
    */
   public static void main(final String[] args) throws IOException
   {
      final Aoc10 app = new Aoc10();

      try (var resource = Aoc10.class.getResourceAsStream("/aoc9-input.txt");
            var input = new BufferedReader(new InputStreamReader(resource)))
      {
         app.readMaze(input);
         System.out.println("Aoc10 = " + app.findFarthestDistance());
      }
   }

   /** the maze read from the input */
   private String[] maze;

   /**
    * check if this cell connects to the cell above
    *
    * @param vals
    * @param curDistance
    * @param row
    * @param col
    */
   void calcDistanceD(final int[][] vals, final int curDistance, final int row, final int col)
   {
      if (vals[row][col] < curDistance)
      {
         return;
      }

      switch (maze[row].charAt(col))
      {
      case '|':
      case 'L':
      case 'J':
         vals[row][col] = curDistance;
         calcDistances(vals, curDistance, row, col);
         break;
      default:
         // do nothing
      }
   }

   /**
    * check if this cell connects to the right
    *
    * @param vals
    * @param curDistance
    * @param row
    * @param col
    */
   void calcDistanceL(final int[][] vals, final int curDistance, final int row, final int col)
   {
      {
         if (vals[row][col] < curDistance)
         {
            return;
         }
      }

      switch (maze[row].charAt(col))
      {
      case '-':
      case 'L':
      case 'F':
         vals[row][col] = curDistance;
         calcDistances(vals, curDistance, row, col);
         break;
      default:
         // do nothing
      }
   }

   /**
    * check if this pipe connects to the cell to the left
    *
    * @param vals
    * @param curDistance
    * @param row
    * @param col
    */
   void calcDistanceR(final int[][] vals, final int curDistance, final int row, final int col)
   {
      if (vals[row][col] < curDistance)
      {
         return;
      }

      switch (maze[row].charAt(col))
      {
      case '-':
      case 'J':
      case '7':
         vals[row][col] = curDistance;
         calcDistances(vals, curDistance, row, col);
         break;
      default:
         // do nothing
      }

   }

   /**
    * check all the cells around the given point to determine their distances
    *
    * @param vals
    *           the values array
    * @param curDistance
    *           the current distance
    * @param row
    * @param col
    */
   void calcDistances(final int[][] vals, final int curDistance, final int row, final int col)
   {
      // check the cell in the row above
      if (row > 0)
      {
         calcDistanceU(vals, curDistance + 1, row - 1, col);
      }

      // check the cell in the row below
      if (row < maze.length - 1)
      {
         calcDistanceD(vals, curDistance + 1, row + 1, col);
      }

      // check left of the current cell
      if (col > 0)
      {
         calcDistanceL(vals, curDistance + 1, row, col - 1);
      }
      // check right of the current cell
      if (col < vals[0].length - 1)
      {
         calcDistanceR(vals, curDistance + 1, row, col + 1);
      }
   }

   /**
    * check the current cell to see if it connects to the cell below. Any pipe that connects down should update this
    * value and continue. Otherwise, it should just return.
    *
    * @param vals
    * @param curDistance
    * @param row
    * @param col
    */
   void calcDistanceU(final int[][] vals, final int curDistance, final int row, final int col)
   {
      if (vals[row][col] < curDistance)
      {
         return;
      }

      switch (maze[row].charAt(col))
      {
      case '|':
      case 'F':
      case '7':
         // this cell is connected, so check around it too
         vals[row][col] = curDistance;
         calcDistances(vals, curDistance + 1, row, col);
         break;

      default:
         // we are not connected, so return
      }
      return;
   }

   /**
    * @return the farthest distance from the start location
    */
   int findFarthestDistance()
   {
      int startRow = 0;
      int startCol = 0;

      for (int row = 0; row < maze.length; row++)
      {
         final var curCol = maze[row].indexOf("S");
         if (curCol >= 0)
         {
            startRow = row;
            startCol = curCol;
            break;
         }
      }

      final int[][] vals = startCalcDistances(startRow, startCol);

      int maxValue = Integer.MIN_VALUE;

      // find the max value
      for (int row = 0; row < maze.length; row++)
      {
         for (int col = 0; col < maze[0].length(); col++)
         {
            if (vals[row][col] > maxValue)
            {
               maxValue = vals[row][col];
            }
         }
      }

      return maxValue;
   }

   /**
    * @return the maze
    */
   String[] getMaze()
   {
      return maze;
   }

   /**
    * @param input
    * @throws IOException
    */
   void readMaze(final BufferedReader input) throws IOException
   {
      String line;
      final ArrayList<String> lines = new ArrayList<>();

      while ((line = input.readLine()) != null)
      {
         lines.add(line);
      }

      this.maze = lines.toArray(new String[lines.size()]);
   }

   /**
    * @param maze
    *           the maze to set
    */
   void setMaze(final String[] maze)
   {
      this.maze = maze;
   }

   /**
    * This is the start of the distance calculation. So allocate the needed resources and recurse.
    *
    * @param row
    * @param col
    * @return the distance array for the maze
    */
   int[][] startCalcDistances(final int row, final int col)
   {
      final int[][] vals = new int[maze.length][maze[0].length()];
      for (final var v : vals)
      {
         Arrays.fill(v, Integer.MAX_VALUE);
      }

      if (col > 0)
      {
         vals[row][col] = 0;
         calcDistances(vals, 0, row, col);
      }

      return vals;
   }
}
