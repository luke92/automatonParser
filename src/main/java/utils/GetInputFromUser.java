package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetInputFromUser
{
    public static String Get()
    {
    	String data = "";
    	//Enter data using BufferReader
    	try {
    		BufferedReader reader =  
                    new BufferedReader(new InputStreamReader(System.in)); 
          
         // Reading data using readLine 
         data = reader.readLine(); 
    	}
    	catch (Exception e){
    		
    	}
    	return data;
        
    }
}