import java.util.*;
import java.io.*;

public class Ads implements Runnable{

	int counter=0;
	
	public void run()
	{
	{
	try {
	while(counter!=60)
	{
	Thread.sleep(10000);
	System.out.println("AD: Mapua University is now accepting applicants. ENROLL NOW!");
	Thread.sleep(10000);
	System.out.println("AD: EECE Department Tala Dance, Out NOW! on youtube");
	counter++;
	counter=0;
	}
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
}
