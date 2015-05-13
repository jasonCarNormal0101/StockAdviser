package ui;
	import java.awt.*;
	import javax.swing.*;
	public class Dragstrip extends JApplet
	{
	 public void init(){
	  JToolBar bar = new JToolBar();
	  Container c = getContentPane();
	  JToggleButton jb;
	  for(int i=0;i<8;i++){
	   jb = new JToggleButton(""+i);
	   bar.add(jb);
	   if(i==5){
	    bar.addSeparator();
	   }
	  }
	  c.add(bar,BorderLayout.NORTH);
	 }
	}
}
