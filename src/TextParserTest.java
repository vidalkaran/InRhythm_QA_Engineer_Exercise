import java.util.LinkedList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextParserTest 
{
	TextParser parser = new TextParser();
		
	@Test
	public void testSimpleParse() throws Exception
	{	
		LinkedList<String> expectedOutput = new LinkedList<String>();
		expectedOutput.add("jumped, 6");

		LinkedList<String> actualOutput = parser.parse("The cow jumped over the moon.");
			
		Assert.assertEquals(actualOutput, expectedOutput);
	}
	
	
	@Test
	public void testMultipleParse() throws Exception
	{	
		LinkedList<String> expectedOutput = new LinkedList<String>();
		expectedOutput.add("Lorem, 5");
		expectedOutput.add("ipsum, 5");
		expectedOutput.add("dolor, 5");

		LinkedList<String> actualOutput = parser.parse("Lorem ipsum dolor sit amet.");
		
		Assert.assertEquals(actualOutput, expectedOutput);
	}
	
	@Test
	public void testComplexSpecialCharacterInput() throws Exception
	{	
		LinkedList<String> expectedOutput = new LinkedList<String>();
		expectedOutput.add("dogs, 4");
		expectedOutput.add("Woof, 4");
		expectedOutput.add("woof, 4");

		LinkedList<String> actualOutput = parser.parse("Who let the dogs out??? Woof, w00f,woof.");
				
		Assert.assertEquals(actualOutput, expectedOutput);
	}
	
	@Test
	public void testComplexNumericInput() throws Exception
	{
		LinkedList<String> expectedOutput = new LinkedList<String>();
		expectedOutput.add("hello, 5");
		expectedOutput.add("world, 5");
		expectedOutput.add("dsads, 5");
		expectedOutput.add("hello, 5");
		expectedOutput.add("again, 5");
		expectedOutput.add("jddds, 5");

		LinkedList<String> actualOutput = parser.parse("214,0320 hello world 3i1904dsads8d hello 18#*@(again e-21jddds.");
		
		Assert.assertEquals(actualOutput, expectedOutput);
	}
		
	@Test
	public void testOutputLengths() throws Exception
	{
		LinkedList<String> actualOutput = parser.parse("Lorem ipsum dolor sit amet.");
		String[] toTest1 = actualOutput.get(0).split(", ");
		String[] toTest2 = actualOutput.get(1).split(", ");
		String[] toTest3 = actualOutput.get(2).split(", ");

		assert(Integer.parseInt(toTest1[1]) == toTest1[0].length());
		assert(Integer.parseInt(toTest2[1]) == toTest2[0].length());
		assert(Integer.parseInt(toTest3[1]) == toTest3[0].length());
	}
	
	@Test(expectedExceptions = Exception.class)
	public void testBlankInput() throws Exception
	{
		parser.parse("                                                       ");
	}
	
	@Test(expectedExceptions = Exception.class)
	public void testPlainNumericInput() throws Exception
	{
		parser.parse("42");
	}
	

	@Test(expectedExceptions = Exception.class)
	public void testPlainSpecialCharacterInput() throws Exception
	{
		parser.parse("??@?!@! @!#*()$*)");
	}

}
