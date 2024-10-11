package fr.istic.vv;

/**
 * A simple example class for testing purposes before moving on to a full project.
 */

public class Example {

	public void Method1() //CC=1
	{
	    System.out.println("M1");
	}
	
	void Method2(Boolean condition) //CC=2
	{
	    if (condition)
	    {
		    System.out.println("M2");
	    }
	}
	
	public void Method3(Boolean condition1, Boolean condition2) //CC=2
	{
	    if (condition1 && condition2)
	    {
		    System.out.println("M3");
	    }
	}
	    
	
	public void Method4(int day)//CC=8
	{
	    switch (day)
	    {
	        case 1:
			    System.out.println("lundi");
	            break;
	        case 2:
			    System.out.println("mardi");
	            break;
	        case 3:
			    System.out.println("mercredi");
	            break;
	        case 4:
			    System.out.println("jeudi");
	            break;
	        case 5:
			    System.out.println("vendredi");
	            break;
	        case 6:
			    System.out.println("samedi");
	            break;
	        default:
			    System.out.println("dimanche");
	            break;
	    }
	}

	
	void Method5(int condition) //CC=3
	{
	    if (condition==1){
		    System.out.println("test1");
	    }
	    else if (condition==2){
		    System.out.println("test2");
	    }
	    else {
		    System.out.println("test3");
	    }
	}
	
	void Method6(int condition) //CC=3
	{
	    if (condition==1){
		    System.out.println("test1");
	    }
	    else {
		    System.out.println("test3");
	    }
	    
	    if (condition==1){
		    System.out.println("test1");
	    }
	    else {
		    System.out.println("test3");
	    }
	}
	
	  

	  
	}
