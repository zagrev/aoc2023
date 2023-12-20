/**
 *
 */
package com.minethurn.aoc2023;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 */
public interface A7Strategy
{
   /**
    * return an ordered list of the hands, ranked highest to lowest
    *
    * @param original
    * @return the ranked list
    * @throws UnsupportedEncodingException
    */
   List<CamelCardHand> rank(List<CamelCardHand> original) throws UnsupportedEncodingException;
}
