/* Author : Philasande Ngubo
 * Date :  23- August-2023
 *
 * A Simple calculator for beginners 
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class Notepad implements ActionListener{
 //instances
 public JFrame frmNote = new JFrame("Notepad");
 private JMenuBar Options = new JMenuBar();
 
 private JMenu mnFile = new JMenu("File");
 private JMenu mnEdit = new JMenu("Edit"); 
 private JMenu mnThemes = new JMenu("Themes"); 
 private JMenu mnView = new JMenu("View");
 private JMenu mnAbout = new JMenu("About"); 
 private JTextArea redText = new JTextArea();
 
 private JMenuItem New = new JMenuItem("New               ");
 private JMenuItem Open = new JMenuItem("Open...           ");
 private JMenuItem Save = new JMenuItem("Save              ");
 private JMenuItem Exit = new JMenuItem("Exit");
 private Font mainFont = new Font("consolas",Font.PLAIN,17);
 
 
 private JMenuItem Undo = new JMenuItem("Undo          Crtl+Z");
 private JMenuItem Cut = new JMenuItem("Cut           Crtl+X");
 private JMenuItem Copy = new JMenuItem("Copy          Crtl+C");
 private JMenuItem Paste = new JMenuItem("Paste         Crtl+V");
 
 private JMenuItem tClassyBlack = new JMenuItem("Classy Black");
 private JMenuItem tRedRose = new JMenuItem("Red Rosé");
 private JMenuItem tHacker = new JMenuItem("Hacker");
 private JMenuItem tGrey = new JMenuItem("Grey");
 private JMenuItem tDefault = new JMenuItem("Restore Default...");
 
 private JMenuItem ZoomIn = new JMenuItem("Zoom In      ");
 private JMenuItem ZoomOut = new JMenuItem("Zoom Out    ");
 private JMenuItem Restore   = new JMenuItem("Restore default");
 private JMenuItem mAbout   = new JMenuItem("About...");  
 private ImageIcon icon;
 public Notepad()
 {
  frmNote.setVisible(true);
  frmNote.setSize(790,530);
  frmNote.setLocationRelativeTo(null);
  frmNote.setJMenuBar(Options);
  frmNote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frmNote.add(redText);
  try 
  {
   icon = new ImageIcon(getClass().getResource("picc.png"));
   frmNote.setIconImage(icon.getImage());
  }
  catch(Exception e)
  {
  
  }
  redText.setFont(mainFont );
  Options.setBackground(Color.WHITE);
  //This sets padding of text inside
  redText.setMargin(new Insets(10,10,10,10));
  //Scroll bars
  JScrollPane scrollBar = new JScrollPane( redText, 
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
  frmNote.getContentPane().add(scrollBar);  
  Options.add(mnFile);
  Options.add(mnEdit);
  Options.add(mnThemes);
  Options.add(mnView);
  Options.add(mnAbout);
  
  mnFile.add(New);
  mnFile.add(Open);
  mnFile.add(Save);
  mnFile.add(Exit);
  
  mnEdit.add(Undo);
  mnEdit.add(Cut);
  mnEdit.add(Copy);
  mnEdit.add(Paste);
  
  mnThemes.add(tClassyBlack);
  mnThemes.add(tRedRose);
  mnThemes.add(tHacker);
  mnThemes.add(tGrey);
  mnThemes.add(tDefault);
  
  mnView.add(ZoomIn);
  mnView.add(ZoomOut);
  mnView.add(Restore);
  
  mnAbout.add(mAbout);
  
  //adding action Listener the items
  
  New.addActionListener(this);
  Open.addActionListener(this);
  Save.addActionListener(this);
  Exit.addActionListener(this);
  tClassyBlack.addActionListener(this);
  tRedRose.addActionListener(this);
  tGrey.addActionListener(this);
  tHacker.addActionListener(this);
  tDefault.addActionListener(this);
  ZoomIn.addActionListener(this);
  ZoomOut.addActionListener(this);
  Restore.addActionListener(this);
  mAbout.addActionListener(this);
  
   //text on change event
 redText.getDocument().addDocumentListener(new DocumentListener() {

        @Override
        public void removeUpdate(DocumentEvent e) {
        changed();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
        changed();
        }

        @Override
        public void changedUpdate(DocumentEvent arg0) {
          changed();
        }
    });
  
 }
 private void changed()
 {
  if (frmNote.getTitle().equals("Notepad"))
  {
   frmNote.setTitle("new *");
  }
  boolean notEdited = ! (frmNote.getTitle().charAt(frmNote.getTitle().length()-1) =='*');
  if ((!frmNote.getTitle().equals("new *")) && notEdited )
  {
   frmNote.setTitle(frmNote.getTitle()+" *");
  }
 }
 
 private void setTheme(Color Banner,Color optionFont ,Color Field , Color fieldFont)
 {
  Options.setBackground(Banner);
  Options.setForeground(optionFont);
  redText.setBackground(Field);
  redText.setForeground(fieldFont);
 }
 public void actionPerformed(ActionEvent e)
 {
 
  if (e.getSource() == New)
  {
    redText.setText("");
    frmNote.setTitle("new *");
  }
  
  if (e.getSource() == Open)
  {
    JFileChooser flFile= new JFileChooser();
    int rOption = flFile.showSaveDialog(null);
    if (rOption == JFileChooser.APPROVE_OPTION)
    {
    redText.setText(NotepadHelper.getTextFromFile(flFile.getSelectedFile().getAbsolutePath()));
    frmNote.setTitle(flFile.getSelectedFile().getName() );
    
    }
    
  }
  if (e.getSource() == Save)
  {
   frmNote.setTitle(NotepadHelper.saveToFile(redText.getText(),frmNote)); 
  }
  
  if (e.getSource() == Exit)
  {
    System.exit(0);
  }
  
  if (e.getSource() == mAbout)
  {
    new About();
  }
  
  if (e.getSource() == tRedRose)
  {
   setTheme(new Color(237,41,57),Color.WHITE, new Color(205,92,92),Color.WHITE ); 
  }
  
  if (e.getSource() == tHacker)
  {
   setTheme(Color.BLACK,Color.YELLOW, Color.BLACK,new Color(0, 255, 0) ); 
  }
  
  if (e.getSource() == tGrey)
  {
   setTheme(new Color(54,69,79),Color.WHITE, new Color(152,129,123),Color.WHITE ); 
  }
  
    if (e.getSource() == tClassyBlack)
  {
   setTheme(Color.BLACK,Color.WHITE, Color.BLACK,Color.WHITE ); 
  }
  if (e.getSource() == tDefault)
  {
   setTheme(Color.WHITE,Color.BLACK, Color.WHITE,Color.BLACK ); 
  }
  
  if (e.getSource() == ZoomIn)
  {
   int iTemp = mainFont.getSize();
   
   mainFont = new Font("consolas",Font.PLAIN,iTemp+2); 
   redText.setFont(mainFont);
  }
  
  if (e.getSource() == ZoomOut)
  {
   int iTemp = mainFont.getSize();
   
   mainFont = new Font("consolas",Font.PLAIN,iTemp-2); 
   redText.setFont(mainFont); 
  }
  
  if (e.getSource() == Restore)
  {
   mainFont = new Font("consolas",Font.PLAIN,17); 
   redText.setFont(mainFont); 
  }

 }
}