package de.heinerkuecker.array_ringbuffer_queue;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link #IntRingBuffer}.
 *
 * @author Heiner K&uuml;cker
 */
public class IntRingBufferTest
{

	@Test( expected = IllegalArgumentException.class )
	public void testIntRingBuffer_0()
	{
		final int maxSize = 0;
		new IntRingBuffer( maxSize );
	}

	@Test
	public void testIntRingBuffer_1()
	{
		final int maxSize = 1;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		final int expected = maxSize;
		final int actual = intRingBuffer.maxSize();

		Assert.assertEquals( expected , actual );

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );
	}

	@Test
	public void test__Size1__Add_1()
	{
		final int maxSize = 1;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		final int valueToAdd = 1;

		Assert.assertTrue(
				intRingBuffer.add(
						valueToAdd ) );

		Assert.assertFalse( intRingBuffer.isEmpty() );
		Assert.assertTrue ( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intRingBuffer.get() );
	}

	@Test
	public void test__Size2__Add_1()
	{
		final int maxSize = 2;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		final int valueToAdd = 1;

		Assert.assertTrue(
				intRingBuffer.add(
						valueToAdd ) );

		Assert.assertFalse( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intRingBuffer.get() );
	}

	@Test
	public void test__Size2__Add_1__Add_2()
	{
		final int maxSize = 2;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		{
			Assert.assertTrue(
					intRingBuffer.add(
							1 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}

		{
			Assert.assertTrue(
					intRingBuffer.add(
							2 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertTrue ( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}
	}

	@Test
	public void test_Size2__Add_1__Add_2__Take_1__Take_2()
	{
		final int maxSize = 2;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		// Add 1
		{
			Assert.assertTrue(
					intRingBuffer.add(
							1 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}

		// Add 2
		{
			Assert.assertTrue(
					intRingBuffer.add(
							2 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertTrue ( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}

		// Take 1
		{
			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.take() );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.get() );
		}

		// Take 2
		{
			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.take() );

			Assert.assertTrue ( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					0 ,
					//actual
					intRingBuffer.currentSize() );
		}
	}

	@Test
	public void test_Size2__Add_1__Add_2__Take_1__Add_3__Take_2__Take_3()
	{
		final int maxSize = 2;
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		// Add 1
		{
			Assert.assertTrue(
					intRingBuffer.add(
							1 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}

		// Add 2
		{
			Assert.assertTrue(
					intRingBuffer.add(
							2 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertTrue ( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.get() );
		}

		// Take 1
		{
			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.take() );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.get() );
		}

		// Add 3
		{
			Assert.assertTrue(
					intRingBuffer.add(
							3 ) );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertTrue ( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.get() );
		}

		// Take 2
		{
			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intRingBuffer.take() );

			Assert.assertFalse( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					3 ,
					//actual
					intRingBuffer.get() );
		}

		// Take 3
		{
			Assert.assertEquals(
					//expected
					3 ,
					//actual
					intRingBuffer.take() );

			Assert.assertTrue ( intRingBuffer.isEmpty() );
			Assert.assertFalse( intRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					0 ,
					//actual
					intRingBuffer.currentSize() );
		}
	}

	@Test
	public void test_Size2__Add__Take__Randomly()
	{
		final int maxSize = 2;
		innerTest_Add_Full__Take_1__Add_1__capacitiy_times_2( maxSize );
		innerTest_Randomly( maxSize );
	}

	@Test
	public void test_Size3__Add__Take__Randomly()
	{
		final int maxSize = 3;
		innerTest_Add_Full__Take_1__Add_1__capacitiy_times_2( maxSize );
		innerTest_Randomly( maxSize );
	}

	@Test
	public void test_Size4__Add__Take__Randomly()
	{
		final int maxSize = 4;
		innerTest_Add_Full__Take_1__Add_1__capacitiy_times_2( maxSize );
		innerTest_Randomly( maxSize );
	}

	@Test
	public void test_Size5__Add__Take__Randomly()
	{
		final int maxSize = 5;
		innerTest_Add_Full__Take_1__Add_1__capacitiy_times_2( maxSize );
		innerTest_Randomly( maxSize );
	}

	private void innerTest_Add_Full__Take_1__Add_1__capacitiy_times_2(
			final int maxSize )
	{
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		int addValue = 0;
		int getValue = 0;
		int expectedSize = 0;

		/*
		 * Fill
		 */
		while ( ! intRingBuffer.isFull() )
		{
			Assert.assertTrue(
					intRingBuffer.add(
							++addValue ) );

			expectedSize++;

			randomTestAssertions(
					maxSize ,
					intRingBuffer ,
					getValue ,
					expectedSize );
		}

		Assert.assertEquals(
				//expected
				maxSize ,
				//actual
				addValue );

		Assert.assertEquals(
				//expected
				maxSize ,
				//actual
				intRingBuffer.currentSize() );

		/*
		 * Rotate
		 */
		while ( addValue < maxSize * 2 )
		{
			// Take
			{
				Assert.assertEquals(
						//expected
						++getValue ,
						//actual
						intRingBuffer.take() );

				expectedSize--;

				randomTestAssertions(
						maxSize ,
						intRingBuffer ,
						getValue ,
						expectedSize );
			}

			// Add
			{
				Assert.assertTrue(
						intRingBuffer.add(
								++addValue ) );

				expectedSize++;

				randomTestAssertions(
						maxSize ,
						intRingBuffer ,
						getValue ,
						expectedSize );
			}
		}

		/*
		 * Empty
		 */
		while ( expectedSize > 0 )
		{
			Assert.assertEquals(
					//expected
					++getValue ,
					//actual
					intRingBuffer.take() );

			expectedSize--;

			randomTestAssertions(
					maxSize ,
					intRingBuffer ,
					getValue ,
					expectedSize );
		}
	}

	private void innerTest_Randomly(
			final int maxSize )
	{
		final IntRingBuffer intRingBuffer = new IntRingBuffer( maxSize );

		{
			final int expected = maxSize;
			final int actual = intRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intRingBuffer.isEmpty() );
		Assert.assertFalse( intRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intRingBuffer.currentSize() );

		final Random random = new Random();

		int addValue = 0;
		int getValue = 0;
		int expectedSize = 0;

		while ( addValue < 100 )
		{
			final boolean isAdding;
			final boolean isTaking;
			if ( intRingBuffer.isEmpty() )
			{
				// Add
				isAdding = true;
				isTaking = false;
			}
			else if ( intRingBuffer.isFull() )
			{
				// Take
				isAdding = false;
				isTaking = true;
			}
			else
			{
				if ( random.nextBoolean() )
				{
					// Add
					isAdding = true;
					isTaking = false;
				}
				else
				{
					// Take
					isAdding = false;
					isTaking = true;
				}
			}

			// Add
			if ( isAdding )
			{
				Assert.assertTrue(
						intRingBuffer.add(
								++addValue ) );

				expectedSize++;
			}

			// Take
			if ( isTaking )
			{
				Assert.assertEquals(
						//expected
						++getValue ,
						//actual
						intRingBuffer.take() );

				expectedSize--;
			}

			randomTestAssertions(
					maxSize ,
					intRingBuffer ,
					getValue ,
					expectedSize );
		}

		while ( getValue < addValue )
		{
			Assert.assertEquals(
					//expected
					++getValue ,
					//actual
					intRingBuffer.take() );

			expectedSize--;

			randomTestAssertions(
					maxSize ,
					intRingBuffer ,
					getValue ,
					expectedSize );
		}

		randomTestAssertions(
				maxSize ,
				intRingBuffer ,
				getValue ,
				expectedSize );
	}

	private void randomTestAssertions(
			final int maxSize ,
			final IntRingBuffer intRingBuffer ,
			final int getValue ,
			final int expectedSize )
	{
		Assert.assertTrue(
				intRingBuffer.currentSize() >= 0 );

		Assert.assertTrue(
				intRingBuffer.currentSize() <= maxSize );

		Assert.assertEquals(
				//expected
				expectedSize ,
				//actual
				intRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				expectedSize == 0 ,
				//actual
				intRingBuffer.isEmpty() );

		Assert.assertEquals(
				//expected
				expectedSize == maxSize ,
				//actual
				intRingBuffer.isFull() );

		if ( expectedSize > 0 )
		{
			Assert.assertEquals(
					//expected
					getValue + 1 ,
					//actual
					intRingBuffer.get() );
		}
	}

}
