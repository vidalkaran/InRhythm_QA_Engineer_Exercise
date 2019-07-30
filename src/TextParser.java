/*
 * 
 * Assumptions
 * - Output should be a list of all qualifying words along with their lengths in "word, length" format. 
 * - A word in this case is any sequence of consecutive unbroken characters. We donÅft check to see if the word is real or not. A word should not contain special characters or numbers.  
 * - The output should return at least one string with a value, length pair. If the output is empty, there were no words in the input and the function should throw an exception.
 * - Generic exception is thrown for now. In a production environment a specific IllegalWordFormatException class or similar would be written and thrown to handle this exception. 
 * - The exception is not handled in this class. Hypothetically this class would be instantiated and utilized by a larger logic class that would use it. In this case, that class would handle the exception. 
 *
 */

import java.util.LinkedList;

public class TextParser 
{
	//Receives an input string and outputs a list of the longest words in that string along with its length.
	public LinkedList<String> parse(String sentence) throws Exception
	{
		int highestValue = -1; //Set the value to -1 so empty strings do not match. 

		//opting for a linked list because the outcome size is unknown and it has more efficient add/remove than ArrayList
		LinkedList<String> output = new LinkedList<String>(); //The return list		
		LinkedList<String> formattedSentence = new LinkedList<String>(); //The input sentence with all numerals and punctuation removed. 
		
		//temporary var for storing formatted text before adding to formattedSentence 
		String tempWord = ""; 		
	
		char[] words = sentence.toCharArray();
		
		//Loop through each character in the input sentence and add extracted words to the formattedSentence array
		for(int i = 0 ; i < words.length; i++)
		{
			if(Character.isLetter(words[i]))
				tempWord+=words[i];
			
			else if(tempWord.length() != 0)
			{
				if(tempWord.length() > highestValue)
					highestValue = tempWord.length();

				formattedSentence.add(tempWord);					
				tempWord = "";
			}
			else
				continue;
		}
		
		//Add the largest words to the output array.
		for(String item : formattedSentence)
		{
			if(item.length() == highestValue)
				output.add(item + ", " + item.length());
		}		
				
		//If the output array is empty, throw format exception. This will only happen if there were no words in the sentence. 
		if(output.isEmpty())
			throw new Exception();
		
		return output;
	}	
}
