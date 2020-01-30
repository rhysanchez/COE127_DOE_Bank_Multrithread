import java.util.*;
import java.io.*;

public class Notice implements Runnable{

	int counter=0;
	int infinity=0;
	
	public void run()
	{
	{
	try {
	while(infinity!=70)
	{
		System.out.println("For your safety, please do not give your account details to anyone.\n"
				+ "ExBank does not ask for password from anyone.");
		while(counter!=60)
		{
			Thread.sleep(1000);
			counter++;
		}
		counter=0;
	}
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
}
