import java.util.*;
import java.io.*;

public class Time implements Runnable{

	
	
	public void run()
	{
	int count = 0;
	for(int i=0; i<10000000;i++)
	{
	try {
	Thread.sleep(1000);
	count ++;
	System.out.println("Elapsed Time: "+count+"s");
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
}
