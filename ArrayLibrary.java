package a5;

import java.util.Arrays;


public class ArrayLibrary 
{
    
    public static void Banner()
    {
         System.out.println("-------------------------------------------------------------------------------------");
    }
    
	/**
	 * Add some simple test cases here for your own benefit. These
	 * will not be graded.
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    int test[] = new int[] {1, 3, 5, 6};
	    clear(test);
	    System.out.println(Arrays.toString(test));
	    
	    Banner();
	    
	    int test2[] = new int[] {1, 5, 6, 7};
	    System.out.println(arrayToString(test2));
	    
	    Banner();
	    
	    String test3[] = new String[] {"apple", "fan", "drum", "apple"};
	    System.out.println(containsDuplicate(test3));
	    
	    Banner();
	    
	    int test4[] = new int[] {1, 2, 4, 6};
	    System.out.println(averageArrayValues(test4));
	    
	    Banner();
	    
	    int test5[] = new int[] {1, 2, 3, 4};
	    frequencyCount(test5);
	    System.out.println(Arrays.toString(frequencyCount(test5)));
	}
	
	/**
	 * Put a zero in each element of the arr array.
	 * 
	 * @param arr: An int array. You may assume that this is not null. It may be zero length.
	 */
	public static void clear(int[] arr) 
	{
	    if(arr.length == 0)
            arr[0] = 0;
	    else
	    {
	       for(int i = 0; i < arr.length; i++)
	        arr[i] = 0;
	    } 
	}
	
	/**
	 * Make our own version to Arrays.toString for an int array. The format is curly braces
	 * instead of square brackets, with a comma and space between elements. So
	 * arrayToString([1,2,3]) -> "{1, 2, 3}"
	 * arrayToString([]) -> "{}"
	 * @param arr: an integer array. You may assume it is not null, but it may be length 0.
	 * @return a String as described above.
	 */
	public static String arrayToString(int[] arr ) 
	{
	  String newArray = "";  
	    if(arr.length == 0)
	        newArray = "{}";
	    else
	    {
	    newArray = "{";    
	      for(int i = 0; i < arr.length; i++)
	      {
	        newArray = newArray + arr[i]+"," + " ";
	      }
	    newArray = newArray + "}";
	    }
		return newArray; 
	}
	
	/**
	 * Searches to see if any element has a duplicate value in the array.
	 * @param arr: an Array of String. You may assume that arr is not null, and no element of arr is null.
	 * 			   arr may be zero length and the String elements may be zero length.
	 * @return true if there is a duplicate value, false otherwise.
	 * Note: You may not use convert the Array to another type and use methods like
	 * contains() to solve this problem.
	 */
	public static boolean containsDuplicate(String[] arr) 
	{
	    Boolean contains = false;
	    if(arr.length == 0)
	        contains = false;
	    else
	    {
	       for (int i = 0; i < arr.length; i++) 
            {
              for (int j = i + 1; j < arr.length; j++) 
               {
                 if (arr[i] == arr[j])
                     contains = true;
               }
            }
	    }
		return contains; 
	}

	/**
	 * Computes the average of all the elements in the integer array parameter.
	 * @param arr : An integer array. You may assume arr is not null and has at least 1 element.
	 * @return the average of all the element values.
	 */
	public static double averageArrayValues(int[] arr) 
	{
	    double sum = 0.0;
	    double average = 0.0;
	     for(int i = 0; i < arr.length; i++)
	     {
	         sum += arr[i];
	     }
	    average = sum/(arr.length);
	    return average; 
	}
	
	/**
	 * frequencyCount takes in an array of integer values that must be from 0 to 9. You can assume
	 * the values are in that range. The method produces a new array of 10 integers. The value at index
	 * 0 is the number of times 0 appears in the elements of the array parameter. The value at index 1 is
	 * the number of times 1 appears in the elements of the array parameter - and so on up to index 9.
	 * @param arr: An array of integers. The integers must only be valued 0-9. The array can be length 0.
	 * @return an array of size 10 holding the counts for each digit in the parameter array. If we call
	 * 			frequencyCount([0,0,1,1,1,7]), then it would return [2,3,0,0,0,0,0,1,0,0] since there are
	 * 		   2 zeroes and 3 ones and 1 seven, and no other digits.
	 */
	public static int[] frequencyCount(int[] arr) 
	{
	    int countOf0 = 0;
	    int countOf1 = 0;
	    int countOf2 = 0;
        int countOf3 = 0;
        int countOf4 = 0;
        int countOf5 = 0;
        int countOf6 = 0;
        int countOf7 = 0;
        int countOf8 = 0;
        int countOf9 = 0;
        
	    for(int i = 0; i < arr.length; i++)
	    {
	        if(arr[i] == 0)
	            countOf0++;
	        if(arr[i] == 1)
	            countOf1++;
	        if(arr[i] == 2)
	            countOf2++;
	        if(arr[i] == 3)
                countOf3++;
            if(arr[i] == 4)
                countOf4++;
            if(arr[i] == 5)
                countOf5++;
            if(arr[i] == 6)
                countOf6++;
            if(arr[i] == 7)
                countOf7++;
            if(arr[i] == 8)
                countOf8++;
            if(arr[i] == 9)
                countOf9++;
	    }
	    
	    int[] countOfNumbers = new int[] {countOf0, countOf1, countOf2, countOf3, countOf4, countOf5, countOf6, countOf7, countOf8, countOf9 };
		return countOfNumbers; 
	}
		
}
