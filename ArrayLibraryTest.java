/*package a5;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayLibraryTest {

	@Test
	public void testClearNormalCase() {
		int[] testData = new int[] {1,2,3};
		ArrayLibrary.clear(testData);
		assertTrue("Failed normal case: all elements should be 0" , ArrayLibrary.clear(testData), 0 ,   
		        testData[0] == 0 && testData[1] == 0 && testData[2] == 0); 		
	}

	@Test
	public void testClearEmptyCase() {
		int[] testData = new int[0];
		ArrayLibrary.clear(testData);
		assertTrue("Failed empty case: there should be no elements", testData.length == 0);
	}

	@Test
	public void testArrayToStringNormalCase() {
		int[] testData = new int[] {1,2,3};		
		assertEquals("Failed normal case", "{1, 2, 3}", ArrayLibrary.arrayToString(testData));
	}
	
	@Test
	public void testContainsDuplicateNormalCase() {
		String[] testData = new String[] {"a","b","a"};
		assertTrue("Failed normal positive case", ArrayLibrary.containsDuplicate(testData));
		String[] testData2 = new String[] {"a","b","c"};
		assertFalse("Failed normal negative case", ArrayLibrary.containsDuplicate(testData2));
	}

	@Test
	public void testAverageArrayValuesNormalCase() {
		int[] testData = new int[] {1,2,3};		
		assertEquals("Failed normal case", 2.0, ArrayLibrary.averageArrayValues(testData), 1e-9);
	}

	@Test
	public void testFrequencyCountNormalCase() {
		int[] testData = new int[] {0,0,1,1,1,7};
		int[] expectedResult = new int[] {2,3,0,0,0,0,0,1,0,0};
		assertArrayEquals("Failed normal case", expectedResult, ArrayLibrary.frequencyCount(testData));
	}

}
*/