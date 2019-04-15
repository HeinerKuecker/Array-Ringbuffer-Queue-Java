package de.heinerkuecker.array_ringbuffer_queue;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit4 test for {@link #ObjRingBuffer}.
 *
 * @author Heiner K&uuml;cker
 */
public class ObjRingBufferTest
{

	@Test( expected = IllegalArgumentException.class )
	public void testIntRingBuffer_0()
	{
		final int maxSize = 0;
		new ObjRingBuffer<Object>( maxSize );
	}

	@Test
	public void testIntRingBuffer_1()
	{
		final int maxSize = 1;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		final int expected = maxSize;
		final int actual = intgrRingBuffer.maxSize();

		Assert.assertEquals( expected , actual );

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );
	}

	@Test
	public void test__Size1__Add_1()
	{
		final int maxSize = 1;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		final int valueToAdd = 1;

		Assert.assertTrue(
				intgrRingBuffer.add(
						valueToAdd ) );

		Assert.assertFalse( intgrRingBuffer.isEmpty() );
		Assert.assertTrue ( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intgrRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intgrRingBuffer.get().intValue() );
	}

	@Test
	public void test__Size2__Add_1()
	{
		final int maxSize = 2;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		final int valueToAdd = 1;

		Assert.assertTrue(
				intgrRingBuffer.add(
						valueToAdd ) );

		Assert.assertFalse( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intgrRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				1 ,
				//actual
				intgrRingBuffer.get().intValue() );
	}

	@Test
	public void test__Size2__Add_1__Add_2()
	{
		final int maxSize = 2;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							1 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							2 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertTrue ( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}
	}

	@Test
	public void test_Size2__Add_1__Add_2__Take_1__Take_2()
	{
		final int maxSize = 2;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		// Add 1
		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							1 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Add 2
		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							2 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertTrue ( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Take 1
		{
			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.take().intValue() );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Take 2
		{
			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.take().intValue() );

			Assert.assertTrue ( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					0 ,
					//actual
					intgrRingBuffer.currentSize() );
		}
	}

	@Test
	public void test_Size2__Add_1__Add_2__Take_1__Add_3__Take_2__Take_3()
	{
		final int maxSize = 2;
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		// Add 1
		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							1 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Add 2
		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							2 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertTrue ( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Take 1
		{
			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.take().intValue() );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Add 3
		{
			Assert.assertTrue(
					intgrRingBuffer.add(
							3 ) );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertTrue ( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Take 2
		{
			Assert.assertEquals(
					//expected
					2 ,
					//actual
					intgrRingBuffer.take().intValue() );

			Assert.assertFalse( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					1 ,
					//actual
					intgrRingBuffer.currentSize() );

			Assert.assertEquals(
					//expected
					3 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}

		// Take 3
		{
			Assert.assertEquals(
					//expected
					3 ,
					//actual
					intgrRingBuffer.take().intValue() );

			Assert.assertTrue ( intgrRingBuffer.isEmpty() );
			Assert.assertFalse( intgrRingBuffer.isFull() );

			Assert.assertEquals(
					//expected
					0 ,
					//actual
					intgrRingBuffer.currentSize() );
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
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		int expectedSize = 0;
		for ( int repeatCount = 0 ; repeatCount < 2; repeatCount++ )
		{
			int addValue = 0;
			int getValue = 0;

			/*
			 * Fill
			 */
			while ( ! intgrRingBuffer.isFull() )
			{
				Assert.assertTrue(
						intgrRingBuffer.add(
								++addValue ) );

				expectedSize++;

				randomTestAssertions(
						maxSize ,
						intgrRingBuffer ,
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
					intgrRingBuffer.currentSize() );

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
							intgrRingBuffer.take().intValue() );

					expectedSize--;

					randomTestAssertions(
							maxSize ,
							intgrRingBuffer ,
							getValue ,
							expectedSize );
				}

				// Add
				{
					Assert.assertTrue(
							intgrRingBuffer.add(
									++addValue ) );

					expectedSize++;

					randomTestAssertions(
							maxSize ,
							intgrRingBuffer ,
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
						intgrRingBuffer.take().intValue() );

				expectedSize--;

				randomTestAssertions(
						maxSize ,
						intgrRingBuffer ,
						getValue ,
						expectedSize );
			}
		}
	}

	private void innerTest_Randomly(
			final int maxSize )
	{
		final ObjRingBuffer<Integer> intgrRingBuffer = new ObjRingBuffer<Integer>( maxSize );

		{
			final int expected = maxSize;
			final int actual = intgrRingBuffer.maxSize();

			Assert.assertEquals(
					expected ,
					actual );
		}

		Assert.assertTrue ( intgrRingBuffer.isEmpty() );
		Assert.assertFalse( intgrRingBuffer.isFull() );

		Assert.assertEquals(
				//expected
				0 ,
				//actual
				intgrRingBuffer.currentSize() );

		final Random random = new Random();

		int addValue = 0;
		int getValue = 0;
		int expectedSize = 0;

		while ( addValue < 100 )
		{
			final boolean isAdding;
			final boolean isTaking;
			if ( intgrRingBuffer.isEmpty() )
			{
				// Add
				isAdding = true;
				isTaking = false;
			}
			else if ( intgrRingBuffer.isFull() )
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
						intgrRingBuffer.add(
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
						intgrRingBuffer.take().intValue() );

				expectedSize--;
			}

			randomTestAssertions(
					maxSize ,
					intgrRingBuffer ,
					getValue ,
					expectedSize );
		}

		while ( getValue < addValue )
		{
			Assert.assertEquals(
					//expected
					++getValue ,
					//actual
					intgrRingBuffer.take().intValue() );

			expectedSize--;

			randomTestAssertions(
					maxSize ,
					intgrRingBuffer ,
					getValue ,
					expectedSize );
		}

		randomTestAssertions(
				maxSize ,
				intgrRingBuffer ,
				getValue ,
				expectedSize );
	}

	private void randomTestAssertions(
			final int maxSize ,
			final ObjRingBuffer<Integer> intgrRingBuffer ,
			final int getValue ,
			final int expectedSize )
	{
		Assert.assertTrue(
				intgrRingBuffer.currentSize() >= 0 );

		Assert.assertTrue(
				intgrRingBuffer.currentSize() <= maxSize );

		Assert.assertEquals(
				//expected
				expectedSize ,
				//actual
				intgrRingBuffer.currentSize() );

		Assert.assertEquals(
				//expected
				expectedSize == 0 ,
				//actual
				intgrRingBuffer.isEmpty() );

		Assert.assertEquals(
				//expected
				expectedSize == maxSize ,
				//actual
				intgrRingBuffer.isFull() );

		if ( expectedSize > 0 )
		{
			Assert.assertEquals(
					//expected
					getValue + 1 ,
					//actual
					intgrRingBuffer.get().intValue() );
		}
	}

}
