/*This class is to help the notepad class but i made the methods very general
 * So you can use them on your programs too
 * Author : PHilasande Ngubo
 * Date : 26 -August- 2023
*/
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import javax.swing.*;

public class NotepadHelper
{
 private NotepadHelper(){}
 public static String getTextFromFile(String filePath)
  {
   String data ="";
   try
   {
    File tfText = new File(filePath);
    Scanner myReader = new Scanner(tfText);
    
     while (myReader.hasNextLine())
      {
       data = data +myReader.nextLine()+"\n";
       
      } 
      myReader.close();    
  }
  catch (FileNotFoundException e)
  {
   JOptionPane.showMessageDialog(null,"An unexpected error occurred !!! \n Please restart.");
   System.exit(0);
  }
  return data;
 }

  public static String saveToFile(String Text,JFrame frame)
  {
   String sHolder = Text;
   JFileChooser fileChooser = new JFileChooser();
   fileChooser.setDialogTitle("Save");
   int userSelection = fileChooser.showSaveDialog(frame);
   String sTemp ="";
   if (userSelection == JFileChooser.APPROVE_OPTION) {
      sTemp = fileChooser.getSelectedFile().getName();
    }
     try
     {
     FileWriter myWriter = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath() );
     myWriter.write(sHolder);
     myWriter.close();
     }
     catch(IOException e)
     {
      JOptionPane.showMessageDialog(null,"Unexpected error occurred !!! \n please restart ");
     }
    sTemp = fileChooser.getSelectedFile().getName();
   return sTemp;
  }
}