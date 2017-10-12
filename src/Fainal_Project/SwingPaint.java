
package Fainal_Project;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

 
public class SwingPaint {
 
JButton clearBtn, LSBtn, NSBtn, chooseBtn, SSBtn,Rec,Cir,Rrec,dline;
DrawArea drawArea;
ActionListener actionListener;

  

 //-------------------------------------------------------------------------------------------------------------------------------------------------------  
JMenuBar menubar;
JMenu shape, color,file,line,tools,help;
JMenuItem circle,fcircle, rectangle,rrectangle,frectangle,Line,about;
JMenuItem open,save,nfile,clear,exit,eraser;
JMenuItem bold,normal,thin;
JMenuItem chcolor,chbcolor;

 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    new SwingPaint().show();
  }

    public SwingPaint() {
        
  
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
     
        //Action Listener For All Button And MenuItems
        this.actionListener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                    
            
                if (e.getSource() == clearBtn||e.getSource() == clear) {
                    drawArea.clear();
                } else if (e.getSource() == nfile) {
                    drawArea.newfile();
                }else if (e.getSource() == LSBtn||e.getSource() == bold) {
                    drawArea.LS();
                }else if (e.getSource() == NSBtn||e.getSource() ==normal ) {
                    drawArea.NS();
                } else if (e.getSource() == SSBtn||e.getSource() ==thin) {
                    drawArea.SS();
                }else if(e.getSource() == rectangle||e.getSource() == Rec){
                   drawArea.drawrec();
                }else if(e.getSource() == rrectangle){
                   drawArea.drawrrec();
                }else if(e.getSource() == frectangle){
                   drawArea.drawfrec();
                }else if(e.getSource() == circle||e.getSource() == Cir){
                   drawArea.drawoval();
                }else if(e.getSource() == fcircle){
                   drawArea.drawfoval();
                }else if(e.getSource() == Line||e.getSource() == dline){
                   drawArea.drawline();
                }else if (e.getSource() == exit) {
                   drawArea.exitb();
                }else if (e.getSource() == save) {
                    try {
                        drawArea.save();
                    } catch (IOException ex) {
                        Logger.getLogger(SwingPaint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getSource() == open) {
                    try {
                        drawArea.open();
                    } catch (IOException ex) {
                        Logger.getLogger(SwingPaint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if (e.getSource() == eraser) {
                    drawArea.Eraser();
                }else if (e.getSource() == chooseBtn||e.getSource() == chcolor) {
                    drawArea.ChooseColor();
                }else if(e.getSource() == chbcolor){
                    drawArea.chooseBackgroundColor();
                }else if(e.getSource() == about){
                    drawArea.About();
                }
              
            }
        };
    
    }
 
 //-------------------------------------------------------------------------------------------------------------------------------------------------------
  public void show() {
    // create main frame
    JFrame frame = new JFrame("Draw Pad");
   
    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    drawArea = new DrawArea();
 
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
  
 
 //-------------------------------------------------------------------------------------------------------------------------------------------------------   
    JPanel controls = new JPanel();
    controls.setBackground(Color.GRAY);
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    LSBtn = new JButton("B");
    LSBtn.setToolTipText("Bold Stroke");
    LSBtn.addActionListener(actionListener);
    NSBtn = new JButton("N");
    NSBtn.setToolTipText("Normal Stroke");
    NSBtn.addActionListener(actionListener);
    chooseBtn = new JButton("Choose Color");
    chooseBtn.addActionListener(actionListener);
    SSBtn = new JButton("T");
    SSBtn.setToolTipText("Thin Stroke");
    SSBtn.addActionListener(actionListener);
    Rec= new JButton("Rec");
    Rec.setToolTipText("Rectangle");
    Rec.addActionListener(actionListener);
    Cir= new JButton("Cir");
    Cir.setToolTipText("Circle");
    Cir.addActionListener(actionListener);
    dline = new JButton("Line");
    dline.addActionListener(actionListener);
    // add to panel
    
    controls.add(chooseBtn);
    controls.add(LSBtn);
    controls.add(SSBtn);
    controls.add(NSBtn);
    controls.add(Rec);
    controls.add(Cir);
    controls.add(dline);
    controls.add(clearBtn);
    
    //Menu Bar
menubar=new JMenuBar ();
file= new JMenu("File");
tools=new JMenu("Tools");
line=new JMenu("Line");
shape=new JMenu ("Shape");
color=new JMenu ("Color");
help=new JMenu("Help");

//File Menu Item...........
open=new JMenuItem ("Open");
open.addActionListener(actionListener);
nfile=new JMenuItem ("New File");
nfile.addActionListener(actionListener);
save=new JMenuItem ("Save");
save.addActionListener(actionListener);
clear=new JMenuItem ("Clear");
clear.addActionListener(actionListener);
exit=new JMenuItem ("Exit");
exit.addActionListener(actionListener);

//Shape Menu Item.........
circle=new JMenuItem ("Circle");
circle.addActionListener(actionListener);
fcircle=new JMenuItem ("Fill Circle");
fcircle.addActionListener(actionListener);
rectangle=new JMenuItem ("Rectangle");
rectangle.addActionListener(actionListener);
rrectangle=new JMenuItem ("Round Rectangle");
rrectangle.addActionListener(actionListener);
frectangle=new JMenuItem ("Fill Rectangle");
frectangle.addActionListener(actionListener);
Line=new JMenuItem ("Line");
Line.addActionListener(actionListener);


//Line Menu Item...........
bold=new JMenuItem("Bold");
bold.addActionListener(actionListener);
normal=new JMenuItem("Normal");
normal.addActionListener(actionListener);
thin=new JMenuItem("Thin");
thin.addActionListener(actionListener);
//Tools Menu Item Eraser
eraser=new JMenuItem ("Eraser");
eraser.addActionListener(actionListener);
//Color Menu Item..........
chcolor=new JMenuItem("Choose Color");
chcolor.addActionListener(actionListener);
chbcolor=new JMenuItem("Background Color");
chbcolor.addActionListener(actionListener);

//Help Menu Item............
about=new JMenuItem("About");
about.addActionListener(actionListener);

 //-------------------------------------------------------------------------------------------------------------------------------------------------------
//Add To Menu Bar

file.add(open);
file.add(nfile);
file.add(save);
file.add(clear);
file.add(exit);



tools.add (line);
tools.add (shape);
tools.add(eraser);

line.add(bold);
line.add(normal);
line.add(thin);

shape.add (circle);
shape.add (rectangle);
shape.add(rrectangle);
shape.add(frectangle);
shape.add (fcircle);
shape.add(Line);

color.add(chcolor);
color.add(chbcolor);

help.add(about);



menubar.add (file);
menubar.add(tools);
menubar.add (color);
menubar.add(help);


 //-------------------------------------------------------------------------------------------------------------------------------------------------------
 
    
    // add to content pane
    
    content.add(controls, BorderLayout.SOUTH);
    //add to Menubar
    content.add(menubar,BorderLayout.NORTH);
    //content.add(tb,BorderLayout.EAST);
    int height=700;
    int width=700;
    frame.setSize(700, 700);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width/2 - width/2,
		      screenSize.height/2 - height/2);
    // show the swing paint result
    frame.setVisible(true);
  }
  
 
}