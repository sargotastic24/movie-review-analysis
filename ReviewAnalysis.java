package a5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReviewAnalysis 
{

	/**
	 * Goes through the process of 
	 *   - reading the file of movie reviews
	 *   - getting a score for each word
	 *   - finding the best scoring word with a count greater than some threshold
	 *   - scoring a few reviews and comparing the computer-generated score with the actual rating.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		// Read the file and put each line in a String array.
		String[] lines = convertFileToStringArray("MovieReviews.txt");
		// Report and quit if the file wasn't found.
		if (lines == null) 
		{
			System.out.println("File was not found");
			return;
        }
		
		// Create three arrays with our known maximum size of 16444 elements.
		//  1. words: All the words in the reviews
		//  2. word_score: A word at index x has a total point value at word_score[x]
		//  3. word_count: A word at index x has a total number of appearances at word_count[x]
		String[] words = new String[16444];
		double[] word_score = new double[16444];
		int[] word_count = new int[16444];
		
		// We need to track how many words have been added to the arrays.
		int numberOfWordsSoFar = 0;
		
		// Go through each review. For each review
		// 1. Pull off the movie score from the front of the review.
		// 2. Go through the text of the review. Process each word by adding to its score and count.
		for (int i = 0; i < lines.length; i++)
		{
			// Turn each line into a Scanner
			Scanner s = new Scanner(lines[i]);
			// Pull off the movie score - we can assume it is there.
			int lineScore = s.nextInt();
			// Get each word from the review and add to its score or initialize it.
			numberOfWordsSoFar = processWords(s, lineScore, words, word_score, word_count, numberOfWordsSoFar);
		}
		
		// We are not that interested in words that do not appear multiple times.
		int word_count_filter = 15;		
		
		// Search for the best scoring word with a word_count greater than word_count_filter.
		int best_index = indexOfBestWord(word_score, word_count, word_count_filter, numberOfWordsSoFar);
		if (best_index != -1) 
		{
			System.out.println("The best scoring word is " + words[best_index] + " with a count of " + word_count[best_index] + " and average score of: " + word_score[best_index]/word_count[best_index]);
		}
		else 
		{
		    // If the filter is too high, no word may match.
			System.out.println("No word found with a word count above " + word_count_filter);
		}
		
		// For a sample of the reviews, estimate its score from the words
		// and compare the estimate with the actual movie score.
		for (int index = 500; index < 520; index++) 
		{
			String test_review = lines[index];
			Scanner test_scanner = new Scanner(test_review);
			// Get the score and then the rest of the line.
			int actual_score = test_scanner.nextInt();
			String review_text = test_scanner.nextLine();
			double estimated_score = scoreReview(review_text, words, word_score, word_count, numberOfWordsSoFar);
			String formattedEstimate = String.format("%.1f", estimated_score);
			System.out.println("estimated score: " + formattedEstimate + " actual score: " + actual_score + " Review: " + review_text );
			test_scanner.close();
		}
	}
	
	/**
	 * For a review sentence, estimate the movie rating based on the words
	 * in the review.
	 * For each word, find its index in the words list, then compute 
	 * its average score (scores[index]/wordCount[index]) and add it to a 
	 * cumulative review score.
	 * Count up the number of words in the review and use the count
	 * and the cumulative review score to get an averaged movie score.
	 * @param review: The text of the review.
	 * @param words: The array of words found in all reviews.
	 * @param scores: The cumulative score for each word in words.
	 * @param wordCount: The number of times each word in words appears in all the reviews.
	 * @param numberOfWordsSoFar: The number of elements in the arrays to be used.
	 * @return the average score for the words in review.
	 */
	public static double scoreReview(String review, String[] words, double[] scores, int[] wordCount, int numberOfWordsSoFar) 
	{
	    int i = 0;
	    for(i = 0; i < words.length; i++)
	    {
	    scores[i] = scores[i] + (scores[numberOfWordsSoFar]/wordCount[numberOfWordsSoFar]);
	    }
		return scores[i];
	}
	
	/**
	 * Search through numberOfWordsSoFar elements of the scores array. 
	 * Following an optimization loop pattern, find the index of the 
	 * highest average scoring word. With this index the actual word can be found later. 
	 * Ignore words whose counts are not greater than the countAbove value.
	 * 
	 * @param scores: An array of cumulative scores for a word. 
	 * @param counts: An array of times the word appeared in the reviews.
	 * @param countAbove: Words with counts below or equal to countAbove are ignored.
	 * @param numberOfWordsSoFar: Specifies the number of valid elements in the arrays.
	 * @return the index of the best average score or -1 if none satisfy the countAbove threshold.
	 */
	public static int indexOfBestWord(double[] scores, int[] counts, int countAbove, int numberOfWordsSoFar) 
	{
	     for(int i = numberOfWordsSoFar ; i < scores.length; i++)
	     {
	      if(counts[i] > countAbove)        
	      {
	       return i;
	      }
	    else
	        return -1;
	     }
	     return 0;
	}
	
	/**
	 * Looks for word in the words array in the first numberOfWordsSoFar elements.
	 * 
	 * @param words: An array of String values
	 * @param word: The search word
	 * @param numberOfWordsSoFar: the number of elements used in words
	 * @return the index of the search word in words, or -1 if not found.
	 */
	public static int indexOfWordInArray(String[] words, String word, int numberOfWordsSoFar) 
	{
	    for(int i = numberOfWordsSoFar; i < words.length; i++)
	    {
	        if(words[i].equals(word))
	            return i;
	        else 
	            return -1;
	    }
		return 0; 
	}
	
	/**
	 * Process the words in the scanner s. If a token in s is already in words, then
	 * add the lineScore to the word_score location for that word and add 1 to the 
	 * word_count for that location. If the token is not is words, then add the token
	 * to the next available spot in words and add the lineScore to word_score at that
	 * location and put a count of 1 in word_count at that location. Adjust numberOfWordsSoFar
	 * by adding 1 when a new spot is used up. 
	 * @param s: A Scanner with the text part of a movie review
	 * @param lineScore: the integer movie rating for the review
	 * @param words: an array to add new words into
	 * @param word_score: an array holding the cumulative score for that word.
	 * @param word_count: an array holding the number of times a word has been seen in reviews
	 * @param numberOfWordsSoFar: the number of elements used in the arrays
	 * @return the new numberOfWordsSoFar. If no new words are found in s, then it is the same 
	 *         value as the input numberOfWordSoFar.
	 */
	public static int processWords(Scanner s, int lineScore, String[] words, double[] word_score, int[] word_count, int numberOfWordsSoFar) 
	{
	    while(s.hasNext())
	    {
          for(int i = 0; i < words.length; i++)
          {
	         if(words[i].equals(s.next()))
	         {
	            word_score[i] = i + lineScore;
	            word_count[i] = i + 1;
	         }
	         else
	         {
	          words[words.length + 1] = s.next();
	          word_score[i] = i + lineScore;        
	          numberOfWordsSoFar++;        
	         }
	      }
	    }
		return numberOfWordsSoFar; 
	}

	/**
	 * Given a filename, open the file and read lines from the file
	 * (a line is defined as what a scanner nextLine() method produces).
	 * Convert each line to lower-case. (Use the toLowerCase() method).
	 * Store the lines in a String[]. 
	 * 
	 * Since we need to know how big the 
	 * String array should be, first go through the file and count the 
	 * number of lines. Then, make a String[] of that size, and make
	 * a new Scanner from the file and go through the file again, placing 
	 * each line in the array.
	 * 
	 * @param filename
	 * @return An array of strings with each line of the file an element
	 *         of the array. Return null if the file is not found.
	 */
	public static String[] convertFileToStringArray(String filename) 
	{
	    String [] movieLine = new String[8529];
        int i = 0;
        try 
        {
            File file = new File(filename);
            Scanner fs = new Scanner(file);
            while(fs.hasNext()) 
            {
                movieLine[i] = fs.nextLine();
                movieLine[i].toLowerCase();
                i++;
                //System.out.println(movieLine[i]);
            }
            fs.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + e.getMessage()); 
        } 
		return movieLine; 
	}

}