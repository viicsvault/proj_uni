/**
* <
* Prompts the user with two different choices on how to evaluate the file.
* Determines their response and executes the program accordingly.
* >
*
* @author <Victor Akpan>
*
*/ 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class Prog01_Tokenization 
{
	static String userInput = "";
	public static Scanner scan ()
	{
		Scanner scan = new Scanner(System.in); // reads user input.
		return scan;
	}
	public static void main(String[] args) throws FileNotFoundException
	{
		File EvalFile = null;
		String Invalid = "Invalid response. Run the program again";
		System.out.println("To evaluate a Text document by itslef, Type <eval>\n\n"
						   + "OR\n\n" 
						   + "To evaluate a Text document WITH an IGNORE File. Type <eval with ignore>\n\n");
		userInput = scan().nextLine(); // Checks for user's response
		userInput = userInput.toLowerCase();
		if (userInput.equals("eval"))
		{
			JFileChooser chooser = new JFileChooser(); // Prompts chooser box
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) //user selected a valid file
			{
				EvalFile = chooser.getSelectedFile();
				Tokenizer tokenize = new Tokenizer();// declares object and systemically calls Tokenizer(EvalFile) constructor
				tokenize.EvalEval(EvalFile, false );
			}
		}
		else if (userInput.equals("eval with ignore"))
		{
			userInput = "";
			System.out.println("(You will be prompted for the IGNORE File after the Eval file is Selected).\n\n" 
								+ "Type OK to continue");
			userInput= scan().nextLine(); // Checks for user's response
			userInput = userInput.toLowerCase();
			if (userInput.equals("ok"))
			{
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					EvalFile = chooser.getSelectedFile();
					Tokenizer tokenize = new Tokenizer(); //declares object and systemically calls Tokenizer(EvalFile, IgnoreFile) constructor
					tokenize.EvalEval(EvalFile,true);
				}
			}
			else System.out.println(Invalid); 
		}
		else System.out.println(Invalid); 
	}	
}



