package a5;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class ReviewAnalysisTest {

	@Test
	public void testScoreReview() {
		String review = "this is good";
		String[] words = {"this", "is", "good"};
		double[] scores = {1.0, 1.5, 3.5};
		int[] wordCount = {1, 1, 1};
		int numberOfWordsSoFar = 3;
		double score = ReviewAnalysis.scoreReview(review, words, scores, wordCount, numberOfWordsSoFar);
		assertEquals("failed normal test", 2.0, score, 1e-9);
	}

	@Test
	public void testIndexOfBestWord() {
		double[] scores = {4.0, 1.5, 3.5};
		int[] wordCount = {1, 5, 3};
		int numberOfWordsSoFar = 3;
		int index =  ReviewAnalysis.indexOfBestWord(scores, wordCount, 0, numberOfWordsSoFar);
		assertEquals("failed normal test", 0, index);
		index =  ReviewAnalysis.indexOfBestWord(scores, wordCount, 3, numberOfWordsSoFar);
		assertEquals("failed normal test with a cutoff count", 1, index);
	}

	@Test
	public void testIndexOfBestWordNoWordsAboveCount() {
		double[] scores = {4.0, 1.5, 3.5};
		int[] wordCount = {1, 5, 3};
		int numberOfWordsSoFar = 3;
		int index =  ReviewAnalysis.indexOfBestWord(scores, wordCount, 10, numberOfWordsSoFar);
		assertEquals("failed no words with high enough count test", -1, index);
	}

	@Test
	public void testIndexOfWordInArray() {
		String[] words = {"this", "is", "good"};
		int numberOfWordsSoFar = 3;
		int index =  ReviewAnalysis.indexOfWordInArray(words, "this", numberOfWordsSoFar);
		assertEquals("failed normal test", 0, index);
		index =  ReviewAnalysis.indexOfWordInArray(words, "good", numberOfWordsSoFar);
		assertEquals("failed normal test", 2, index);
		index =  ReviewAnalysis.indexOfWordInArray(words, "boy", numberOfWordsSoFar);
		assertEquals("failed normal test word not in array", -1, index);
	}

	@Test
	public void testProcessWords() {
		String[] words = {"this", "is", "good", ""};
		double[] scores = {1.0, 2.0, 3.0, 0.0};
		int[] wordCount = {1, 1, 1, 0};
		int numberOfWordsSoFar = 3;
		String more = "this a";
		Scanner s = new Scanner(more);
		int reviewScore = 3;
		numberOfWordsSoFar =  ReviewAnalysis.processWords(s, reviewScore, words, scores, wordCount, numberOfWordsSoFar);
		assertEquals("failed to add word to numberOfWordsSoFar", 4, numberOfWordsSoFar);
		assertEquals("failed to update scores", 4.0, scores[0], 1e-9);
		assertEquals("failed to update wordCount", 2, wordCount[0]);
		assertEquals("failed to update wordCount", 1, wordCount[3]);
	}

	@Test
	public void testConvertFileToStringArray() {
		String[] lines = ReviewAnalysis.convertFileToStringArray("testFile.txt");
		assertEquals("failed to add lines to array", 2, lines.length);
		assertEquals("failed to add make all text lower case", "this is a test", lines[0]);
	}

}
