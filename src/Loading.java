import java.util.*;
import java.io.*;

public class Loading implements Runnable{

	
	
	public void run()
	{
	{
	try {
	System.out.println("Loading");
	for(int i = 0;i<4; i++) {
		System.out.println(". ");
	}
	Thread.sleep(3000);
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
}
