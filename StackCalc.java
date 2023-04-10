import java.util.List;		// used by expression evaluator

/**
 *	<Description goes here>
 *
 *	@author	
 *	@since	
 */
public class StackCalc 
{
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public StackCalc() 
	{
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) 
	{
		StackCalc sc = new StackCalc();
		sc.run();
	}
	
	public void run() 
	{
		System.out.println("\n\n\nWelcome to StackCalc!!!");
		runCalc();
		System.out.println("\nThanks for using StackCalc! Goodbye.\n\n\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() 
	{
		String str = Prompt.getString("\nExpression");
		List<String> tokens = utils.tokenizeExpression(str);
		if(tokens.get(0).equals("h"))
		{
			printHelp();
			runCalc();
		}
		else if(tokens.get(0).equals("q"))
			return;
		else
		{
			System.out.println(evaluateExpression(tokens));
			runCalc();
		}
	}
	
	/**	Print help */
	public void printHelp() 
	{
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) 
	{
		double value = 0;
		for(String token: tokens)
		{
			if(!operator(token))
				valueStack.push(Double.parseDouble(token));
			else
			{
				if(operatorStack.isEmpty()||token.equals("("))
					operatorStack.push(token);
				else if (!operatorStack.peek().equals("(")&&!token.equals(")"))
				{
					while(!operatorStack.isEmpty()&&hasPrecedence(token,operatorStack.peek()))
						pop();
					operatorStack.push(token);
				}
				else if(token.equals(")"))
				{
					while (!operatorStack.peek().equals("("))
						pop();
					operatorStack.pop();
				}
				
			}
		}
		while (!operatorStack.isEmpty())
			pop();
		value = valueStack.pop();
		return value;
	}
	
	public boolean operator(String token)
	{
		return token.equals("^") || token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-") || token.equals("(") || token.equals(")");
	}

	public void pop()
	{
		double t2 = valueStack.pop();
		double ans = evaluate(valueStack.pop(), operatorStack.pop(), t2);
		valueStack.push(ans);
	}
	public double evaluate(Double t1, String op1, Double t2)
	{
		if (op1.equals("^"))
			return Math.pow(t1,t2);
		if (op1.equals("*"))
			return t1*t2;
		if (op1.equals("/"))
			return t1/t2;
		if (op1.equals("+"))
			return t1+t2;
		return t1-t2;
	}
	/**
	 *	Precedence of operators
	 *	@param op1		operator 1
	 *	@param op2		operator 2
	 *	@return			true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) 
	{
		if (op1.equals("^")) 
			return false;
		if (op2.equals("(") || op2.equals(")")) 
			return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) && (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
}
