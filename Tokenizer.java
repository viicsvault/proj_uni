/**
* <
* Collects the user's input, tokenize's it and prints the output to a destination file.
* >
*
* @author <Victor Akpan>
*
*/ 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.lang.Character;
public class Tokenizer extends Prog01_Tokenization 
{
	public int numElements = 0; 
	public int numIgnoreElements = 0;
	public int [] wordCountArray; // Array for counting number of occurrences
	public  String[]  wordArray;	// Token Array 
	String  errMessage =  "File not found. Try again"; // File not found error message 
	File OutFile;
	/**
	* <
	* Evaluates the .txt file then places it into a wordArray.  
	* Then counts the occurrence of each token in the wordArray then places it into a wordCountArray.
	* Lastly, sorts the array and outputs it. Calls "EvalIgnore" if needed.
	* >
	* @author <Victor Akpan>
	*/
	public void EvalEval (File EvalFile, Boolean Action) throws FileNotFoundException
	{
		int i = 0;
		int j = 0;
		wordArray = new String [2000];	
		wordCountArray = new int [wordArray.length];
		try
		{
			String evalFileContent = "";
			Scanner input = new Scanner(EvalFile);
			while(input.hasNext())
			{
				evalFileContent = input.next();
				evalFileContent = evalFileContent.toLowerCase();
				evalFileContent = evalFileContent.replace("\"", "");
				wordArray[i] = IgnorePunctuation(evalFileContent);
				numElements++;
				i++;
			}
			input.close();	// closes file
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(errMessage);
		}
		for (i = 0; i < numElements; i++ )
			 wordCountArray[i] = 1; 
		for (i = 0; i < numElements - 1; i++)
		{
			for (j = i + 1; j < numElements - 1; j++) 
				{
					if (wordArray[i].compareTo(wordArray[j]) == 0)	
					{
						wordCountArray[i]++;
						wordCountArray[j]= -1000;
					}
				}
		}
		SortArray(wordArray); // calls sort array method
		if (Action) // executes if there is a need to evaluate an ignore file.
			EvalIgnore ();
		OutputTokens(OutFile);
	}
	/**
	* <
	* Evaluates the ignore .txt file then searches the wordArray for tokens to ignore.
	* Updates the wordCount Array if words are ignored.
	* >
	*
	* @author <Victor Akpan>
	*/
	public void EvalIgnore ()
	{
		int i = 0;
		int j = 0;
		File IgnoreFile = null;
		String[] ignoreWordArray = new String [2000] ;
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
			IgnoreFile = chooser.getSelectedFile();
		try
		{
			String ignoreFileContent;
			Scanner input = new Scanner(IgnoreFile);
			while(input.hasNext())
			{
				ignoreFileContent = input.next();
				ignoreFileContent = ignoreFileContent.toLowerCase();
				ignoreWordArray[i] = ignoreFileContent;
				numIgnoreElements++;
				i++;
			}
		input.close();	
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(errMessage);
		}
		for (i = 0; i < numIgnoreElements; i++)
		{
			for (j = 0; j < numElements; j++) 
			{
				if (ignoreWordArray[i].compareTo(wordArray[j]) == 0)	
				{
					wordArray[j] = "ignore";
				}
			}
		}
	}
	/**
	* <
	* Creates a character array of the Eval file, 
	* then checks if the first and last index of each array is a Letter, Number or a "$" sign. 
	* If so, the content of the index is added to the "contentNoPunctuation" String.
	* >
	*
	* @author <Victor Akpan>
	*/
	public String IgnorePunctuation (String content)
	{
		String contentNoPunctuation = "";
		int l;
		char[]contentCharArray = content.toCharArray();
		int k = contentCharArray.length-1;
		if (Character.isLetterOrDigit(contentCharArray[0]))
			contentNoPunctuation += contentCharArray[0];
		else if (Character.isJavaIdentifierStart(contentCharArray[0]))
				 contentNoPunctuation += contentCharArray[0];
		if ( contentCharArray.length > 1)
			{
			for (l = 1; l <= contentCharArray.length - 2; l++)
				 contentNoPunctuation += contentCharArray[l];
				 if (Character.isLetterOrDigit(contentCharArray[k]))
					 contentNoPunctuation += contentCharArray[k];
			}	
		return contentNoPunctuation;
	}
	/**
	* <
	* Swaps the index of "wordArray."
	* >
	* @author <Victor Akpan>
	*
	*/
	public void WordSwap(String[] array, int i, int j)
	{
		String Temp = wordArray[i];
	    wordArray[i] = wordArray[j];
	    wordArray[j] = Temp;
	} 
	/**
	* <
	* Swaps the index of "wordCountArray."
	* >
	*
	* @author <Victor Akpan>
	*/
	public void WordCountSwap(int [] array, int i, int j) 
	{ 
		int Temp = wordCountArray[i];
	    wordCountArray[i] = wordCountArray[j];
	    wordCountArray[j] = Temp;
	}
	/**
	* <
	* Sorts "wordArray" in alphabetical order. 
	* >
	*
	* @author <Victor Akpan>
	*/
	public void SortArray(String[] array) 
	{
		for (int i = 0; i < numElements; i++)
		{
			for (int j = i + 1; j < numElements; j++) 
				{
					if (wordArray[i].compareTo(wordArray[j]) >  0)	
					{
						WordSwap(wordArray, i, j);
						WordCountSwap(wordCountArray, i, j);
					}
				}
		}
	} 
	/**
	* <
	* Prompts the JFileChooser save dialogue so the user can pick a destination file for the sorted and tokenized content.
	* Then checks if the indexes of the array pass "if" test, if so it prints the final array content to the destination file.
	* >
	*
	* @author <Victor Akpan>
	*/
	public void OutputTokens(File OutFile) throws FileNotFoundException
	{
		userInput = "";
		System.out.println("Your file will now be outputed.\n\n" 
							+ "Type OK to continue");
		userInput= scan().nextLine(); // Checks for user's response
		userInput = userInput.toLowerCase();
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			OutFile = chooser.getSelectedFile();
			OutFile.getPath();
		}
	    PrintWriter write = new PrintWriter(OutFile); 
		String Ignore = "ignore";
		for (int i = 0; i < numElements; i++)
		{
			if( (wordArray[i].compareTo(Ignore) != 0) && (wordCountArray[i] >= 1))
				{
					write.println( wordArray[i] + "," + wordCountArray[i] + "\n");
				}
		}
		write.close();
		System.out.println("File has been Tokenized and saved. Thank you for using  our services.");// Successful evaluation validator.
	}
}