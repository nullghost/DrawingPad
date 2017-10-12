
package Fainal_Project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
 


/**
* Component for drawing !
*
* @author lenovo
*
*/


public class DrawArea extends JComponent {
 
  // Image in which we're going to draw
  private BufferedImage image;
  private BufferedImage backupimage;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, oldX, oldY,x1,y1,x,y,h,w;
  public Color color,colorb; 
  private int stroke=0;
  private boolean rec=false;
  private boolean oval=false;
  private boolean line=false;
  private boolean foval=false;
  private boolean frec=false;
  private boolean rrec=false;
  private boolean eraser=false;
  private boolean open=false;
  private boolean clear=false;
  private boolean nefile=false;
 //------------------------------------------------------------------------------------------------------------------------------------------------------- 
 
//contructor...
  public DrawArea() {
     
    setDoubleBuffered(false);
    
    
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        // save coord x,y when mouse is pressed
        oldX = e.getX();
        oldY = e.getY();
      }
      @Override
       public void mouseReleased(MouseEvent e) {
        // save coord x,y when mouse is pressed
        x1 = e.getX();
        y1 = e.getY();
       if(g2!=null){
             x = Math.min(oldX, x1); 
            y = Math.min(oldY, y1); 
            w = Math.abs(oldX - x1) + 1; 
            h = Math.abs(oldY - y1) + 1; 
 //------------------------------------------------------------------------------------------------------------------------------------------------------- 
            //set stroke
            if(stroke==0){
          g2.setStroke(new BasicStroke(5));
         }else{
          g2.setStroke(new BasicStroke(stroke));
         }
 //------------------------------------------------------------------------------------------------------------------------------------------------------- 
       //Draw Rectangle....
        if(rec){ 
       g2.drawRect(x, y, w,h);
        }
 //-------------------------------------------------------------------------------------------------------------------------------------------------------         
       //Draw Oval.....
        else if(oval){
       g2.drawOval(x, y, w,h);}
 //-------------------------------------------------------------------------------------------------------------------------------------------------------        
      //Draw Fill Oval.....  
        else if(foval){
           g2.fillOval(x,y,w,h);
        }
  //-------------------------------------------------------------------------------------------------------------------------------------------------------        
     //Draw Round Retangle....  
        else if(rrec){
           g2.drawRoundRect(x,y,w,h,25,25);
        }
        
      }  
   // refresh draw area to repaint
       repaint();}
      
    });
    
    
    
    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        // coordinate x,y while mouse dragged
        currentX = e.getX();
        currentY = e.getY();
        
 //-------------------------------------------------------------------------------------------------------------------------------------------------------        
   //Start Drawing.......
      if (g2 != null) {
          // Calculate The X Y coordinates and hight ,width
            
            x = Math.min(oldX, currentX); 
            y = Math.min(oldY, currentY); 
            w = Math.abs(oldX - currentX) + 1; 
            h = Math.abs(oldY - currentY) + 1; 
 //-------------------------------------------------------------------------------------------------------------------------------------------------------          
     //Set Line Stroke        
         if(stroke==0){
          g2.setStroke(new BasicStroke(5));
         }else{
          g2.setStroke(new BasicStroke(stroke));
         }
         
         
  //----------------------------------------------------------------------------------------------------------------------------------------------------       
         
    //Draw Fill Rectangle....     
         if(frec){
          g2.fillRect(x, y, w,h);
         }
  //----------------------------------------------------------------------------------------------------------------------------------------------------      
   //Draw Line.......     
         else if(line){
             g2.drawLine(oldX, oldY, currentX, currentY);
            oldX = currentX;
            oldY = currentY;
         }
         
  //----------------------------------------------------------------------------------------------------------------------------------------------------       
    //Set Draw Line As Deafault
         else if(!rrec&&!frec&&!rec&&!oval&&!foval&&!line&&!eraser){
            g2.drawLine(oldX, oldY, currentX, currentY);
            oldX = currentX;
            oldY = currentY;
         }
 //----------------------------------------------------------------------------------------------------------------------------------------------------         
    //Eraser..............
         else if(eraser){
             if(colorb!=null){
             g2.setPaint(colorb);}
            else{g2.setPaint(Color.white);}
             g2.setStroke(new BasicStroke(15));
             g2.drawLine(oldX, oldY, currentX, currentY);
             oldX = currentX;
             oldY = currentY;
             if(color!=null){
             g2.setPaint(color);}
            else{g2.setPaint(Color.BLACK);}
         }
   }
       // refresh draw area to repaint
        repaint();
     }
    });
  }
 
  @Override
  
 //------------------------------------------------------------------------------------------------------------------------------------------------------- 
  protected void paintComponent(Graphics g) {
    if (image == null) {
      // image to draw null ==> we create
      image = new BufferedImage(1700, 700, BufferedImage.TYPE_INT_ARGB);
      g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // clear draw area
      clear();
    }else if(open){
    
     g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     g2.setPaint(Color.black);
     openfile();
    }else if(image!=null && (clear||nefile)){
    
     // image to draw null ==> we create
      image = new BufferedImage(1700, 700, BufferedImage.TYPE_INT_ARGB);
      g2 = (Graphics2D) image.getGraphics();
      // enable antialiasing
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      // clear draw area
     if(clear) {
     clear();
      clear=false;}
     else if(nefile){
     colorb=null;
     clear();
     clear=false;
     nefile=false;
     }
      
    }
    
    g.drawImage(image, 0, 0, null);
    
  } 
 
 

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// now we create exposed methods
  public void clear() {
     
     clear=true;
     
    if(colorb!=null){
    g2.setPaint(colorb);
    }else{
    g2.setPaint(Color.white);
    }
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, 1700, 700);
    g2.setPaint(Color.black);
    stroke=0;
    rec=false;
   line=false;
   oval=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=false;
    repaint();
     
     
    
  }
  
    public void newfile(){
        
     nefile=true; 
     backupimage=null;
    repaint();
  
  
  }
 
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void ChooseColor() {
    color = JColorChooser.showDialog( DrawArea.this, "Choose a color", color );
    g2.setPaint(color);
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
 //Set Stroke Size
  public void LS() {
    stroke=8;
  }
 
  public void SS() {
    stroke=3;
  }
 
  public void NS() {
   stroke=0;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void chooseBackgroundColor() {
     
     colorb = JColorChooser.showDialog( DrawArea.this, "Choose a color", colorb );
    // draw white on entire draw area to clear
     if(colorb != null){
     g2.setPaint(colorb);
     g2.fillRect(0, 0, 1700, 700);
     g2.setPaint(Color.black);}
      repaint();
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void exitb(){
  int result = JOptionPane.showConfirmDialog(null,
						 "Do you want to exit Scribble Pad?", 
						 "Exit Scribble Pad?",
						 JOptionPane.YES_NO_OPTION);
      if (result == JOptionPane.YES_OPTION) {
      try { 
          save();
      } catch (IOException ex) {
          Logger.getLogger(DrawArea.class.getName()).log(Level.SEVERE, null, ex);
      }
	System.exit(0); 
      }
  
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
 public void save() throws IOException{
    JFileChooser chooser=new JFileChooser();
chooser.setCurrentDirectory(new File("."));
int r = chooser.showSaveDialog(DrawArea.this);
if (r == JFileChooser.APPROVE_OPTION) {
   
   String filename = chooser.getSelectedFile().getAbsolutePath(); 
   
    ImageIO.write(image, "PNG" , new File(filename));
    }
  }
  //-------------------------------------------------------------------------------------------------------------------------------------------------------
 public void open() throws IOException{
     
        JFileChooser chooser=new JFileChooser();
chooser.setCurrentDirectory(new File("."));
chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files",
          new String[] { "png", "jpg", "jpeg", "gif" }));
int r = chooser.showOpenDialog(DrawArea.this);
if (r == JFileChooser.APPROVE_OPTION) {
    image = ImageIO.read(chooser.getSelectedFile());
    backupimage= ImageIO.read(chooser.getSelectedFile());
    colorb=null;
    open=true;
            repaint();
        }
  }
 
//-------------------------------------------------------------------------------------------------------------------------------------------------------

 public void About(){
 JOptionPane.showMessageDialog(null, 
				    "DrawingPad version 1.0\nCopyright (c) LU_32nd , 2015\n", "About", 
				    JOptionPane.INFORMATION_MESSAGE); 
 }
//-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawrec(){
           
            
   rec=true;
   line=false;
   oval=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=false;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawfrec(){
   frec=true;
   rec=false;
   line=false;
   oval=false;
   rrec=false;
   foval=false;
   eraser=false;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawoval(){
       
            
            
   oval=true;
   line=false;
   rec=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=false;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawfoval(){
   foval=true;
   oval=false;
   line=false;
   rec=false;
   rrec=false;
   frec=false;
   eraser=false;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawrrec(){
   rrec=true;
   line=false;
   rec=false;
   oval=false;
   frec=false;
   foval=false;
   eraser=false;
  }
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void drawline(){
   line=true;
   rec=false;
   oval=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=false;
  }
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
 public void Eraser(){
   line=false;
   rec=false;
   oval=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=true;
 
 }
  //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void openfile(){
  
     stroke=0;
   open=false;
   rec=false;
   line=false;
   oval=false;
   rrec=false;
   frec=false;
   foval=false;
   eraser=false;
    repaint();
  }
 
 
}