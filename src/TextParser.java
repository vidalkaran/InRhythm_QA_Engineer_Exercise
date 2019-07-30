import java.util.LinkedList;

public class TextParser 
{
	/* 
	 * Receives an input string and outputs a list of the longest words in that string along with its length.
	 * @param sentence The input sentence should consist of more than one word. It should not include single letters, numbers, or special characters. 
	 * @return		return a LinkedList<String> containing a list of the longest words, in the format of "word, length"
	 */
	public LinkedList<String> parse(String sentence) throws Exception
	{
		int highestValue = -1; //Set the value to -1 so empty strings do not match. 

		//opting for a linked list because the outcome size is unknown and it has more efficient add/remove than ArrayList
		LinkedList<String> output = new LinkedList<String>(); 
		
		//Temporary array 
		LinkedList<String> formattedSentence = new LinkedList<String>(); //The input sentence with all numerals and punctuation removed. 
		
		//temporary var for storing formatted text before adding to formattedSentence 
		String tempWord = ""; 		
		
		//Split the input sentence into an array of its words
		char[] words = sentence.toCharArray();
		
		//Loop through each character in the input sentence...
		for(int i = 0 ; i < words.length; i++)
		{
			//and add it to a temporary string if it is a letter..
			if(Character.isLetter(words[i]))
				tempWord+=words[i];
			
			//... or truncate the text and add it to the formattedSentence array...
			else if(tempWord.length() != 0)
			{
				//updating the highest value var
				if(tempWord.length() > highestValue)
					highestValue = tempWord.length();

				formattedSentence.add(tempWord);					
				tempWord = "";
			}
			//... or ignore it.
			else
				continue;
		}
		
		//Add all applicable strings to the output array
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
