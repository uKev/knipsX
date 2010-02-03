package org.knipsX.studien.XML4.hello;

import java.io.*;
class FileWrite 
{
   public static void main(String args[])
  {
      try{
    // Create file 
    FileWriter fstream = new FileWriter("out.txt");
        BufferedWriter out = new BufferedWriter(fstream);
    out.write("Hello Java");
    //Close the output stream
    out.close();
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }
  }
}
