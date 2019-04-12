package de.heinerkuecker.array_ringbuffer_queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

// https://www.google.com/search?q=ringbuffer+array+java
// http://tutorials.jenkov.com/java-performance/ring-buffer.html

// https://www.programiz.com/dsa/circular-queue

/**
 * Version with array access over division rest (modulo).
 *
 *
 * Wenn die Schreibposition und die Leseposition die Feldgrenzen verlassen, werden beide auf den Feldbereich reduziert.
 *
 * When the writing position and the reading position leave the field boundaries, both are reduced to the field area.
 *
 * If the get and the set position leafs the array bound, both values will be reduced to values within array bound.
 *
 *
 * Deshalb koennen die Indexwerte das Doppelte der Groesse erreichen.
 *
 * Therefore, the index values can reach twice the size.
 *
 *
 * Deshalb muss die Groesse des Puffers kleiner als der halbe maximale Integer-Zahl sein.
 *
 * Therefore, the size of the buffer must be less than half the maximum integer number.
 *
 * <pre>
 * Example: maxSize 2
 *
 * +---+---+
 * |   |   |
 * +---+---+
 *   ^
 *   |
 *   +-- getIndex == 0, setIndex == 0
 *
 * 0123->number line
 * Set
 * Get
 *
 *
 * Add 1
 * +---+---+
 * | 1 |   |
 * +---+---+
 *   ^   ^
 *   |   |
 *   |   +-- setIndex == 1
 *   +--- getIndex == 0
 *
 * 0123->number line
 *  Set
 * Get
 *
 *
 * Add 2
 * +---+---+
 * | 1 | 2 |
 * +---+---+
 *   ^
 *   |
 *   +-- getIndex == 0, setIndex == 2 ( setIndex % 2 == 0 )
 *
 * 0123->number line
 *   Set
 * Get
 *
 *
 * Take 1
 * +---+---+
 * | 1 |   |
 * +---+---+
 *   ^   ^
 *   |   |
 *   |   +-- getIndex == 1
 *   +--- setIndex == 2 ( setIndex % 2 == 0 )
 *
 * 0123->number line
 *   Set
 *  Get
 *
 *
 * Add 3
 * +---+---+
 * | 3 | 2 |
 * +---+---+
 *       ^
 *       |
 *       +-- getIndex == 1, setIndex == 3 ( setIndex % 2 == 1 )
 *
 * 0123->number line
 *    Set
 *  Get
 *
 *
 * Take 2
 * +---+---+
 * | 3 |   |
 * +---+---+
 *   ^   ^
 *   |   |
 *   |   +-- setIndex == 3 ( setIndex % 2 == 1 )
 *   +-- getIndex == 2 ( getIndex % 2 == 0 )
 *
 * 0123->number line
 *    Set
 *   Get
 *
 * Reduce Index
 * +---+---+
 * | 3 |   |
 * +---+---+
 *   ^   ^
 *   |   |
 *   |   +-- setIndex == 1
 *   +-- getIndex == 0
 *
 * 0123->number line
 *  Set
 * Get
 *
 *
 * Take 3
 * +---+---+
 * |   |   |
 * +---+---+
 *       ^
 *       |
 *       +- getIndex == 1, setIndex == 1
 *
 * 0123->number line
 *  Set
 *  Get
 *
 * </pre>
 *
 * @author Heiner K&uuml;cker
 */
public class IntRingBuffer
{
    private int setIndex;
    private int getIndex;

    private final int[] innerArray;

    /**
     * Constructor.
     */
    public IntRingBuffer(
            final int maxSize )
    {
        if ( maxSize < 1 )
        {
            throw new IllegalArgumentException(
                    "maxSize " + maxSize );
        }

        if ( maxSize >= Integer.MAX_VALUE / 2 )
        {
            throw new IllegalArgumentException(
                    "maxSize " + maxSize );
        }

        this.innerArray = new int[ maxSize ];
    }

    /**
     * @return maximal amount of elements to save
     */
    public int maxSize()
    {
        return this.innerArray.length;
    }

    /**
     * @return current amount of saved elements
     */
    public int currentSize()
    {
        return this.setIndex - this.getIndex;
    }

    public int freeSize()
    {
        return maxSize() - currentSize();
    }

    public boolean isEmpty()
    {
        return currentSize() < 1;
    }

    public boolean isFull()
    {
        return this.currentSize() >= maxSize();
    }

    public boolean add(
            final int valueToAdd )
    {
        //System.out.println( "add " + valueToAdd );

        if ( isFull() )
        {
            return false;
        }

        innerArray[ this.setIndex % this.maxSize() ] = valueToAdd;

        this.setIndex++;

        //System.out.println( this );

        return true;
    }

    /**
     * @return the first element without deleting it
     */
    public int get()
    {
        if ( this.isEmpty() )
        {
            throw new NoSuchElementException();
        }

        return innerArray[ this.getIndex % this.maxSize() ];
    }

    /**
     * @return the first element with deleting it
     */
    public int take()
    {
        //System.out.println( "take" );

        if ( this.isEmpty() )
        {
            throw new NoSuchElementException();
        }

        final int result = innerArray[ this.getIndex % this.maxSize() ];

        this.getIndex++;

        // here is the trick
        if ( this.getIndex >= this.maxSize() )
        {
            this.getIndex %= this.maxSize();
            this.setIndex %= this.maxSize();
        }

        //System.out.println( "take " + result );

        return result;
    }

    @Override
    public String toString()
    {
        return
                "IntRingBuffer[" +
                "getIndex=" + getIndex + ", " +
                "setIndex=" + setIndex + ", " +
                "innerArray=" + Arrays.toString( innerArray ) +
                "]";
    }

}
