package entity;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class test2 extends Composite {
	 public Composite  c1;
	 Color blue = getDisplay().getSystemColor(SWT.COLOR_BLUE);
	public test2(Composite parent, int style){
		super(parent,style);
		this.setLayout(new FillLayout());
		final ScrolledComposite sc1 = new ScrolledComposite(this, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		  c1 = new Composite(sc1, SWT.NONE);
		sc1.setContent(c1);// 关键点2.设置Scrolled容器的Content为内层的容器
		Color red = parent.getDisplay().getSystemColor(SWT.COLOR_RED);
		c1.setBackground(red);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		c1.setLayout(layout);
		Button b1 = new Button(c1, SWT.PUSH);
		b1.setText("first button");
		c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));// 关键点3
		final ScrolledComposite sc2 = new ScrolledComposite(this, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		sc2.setExpandHorizontal(true);
		sc2.setExpandVertical(true);
		final Composite c2 = new Composite(sc2, SWT.NONE);
		sc2.setContent(c2);
		c2.setBackground(blue);
		layout = new GridLayout();
		layout.numColumns = 4;
		c2.setLayout(layout);
		Button b2 = new Button(c2, SWT.PUSH);
		b2.setText("first button");
		sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		Button add = new Button(this, SWT.PUSH);
		add.setText("add children");
		final int[] index = new int[] { 0 };
		add.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				index[0]++;
				Button button = new Button(c1, SWT.PUSH);
				button.setText("button " + index[0]); // reset size of content
														// so children can be
														// seen - method 1
				c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				c1.layout();
				button = new Button(c2, SWT.PUSH);
				button.setText("button " + index[0]); // reset the minimum width
														// and height so
														// children can be seen
														// - method 2
				sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				c2.layout();
			}
		});
	}
	public static void main(String[] args) {
		Display display = new Display();
		Color red = display.getSystemColor(SWT.COLOR_RED);
		Color blue = display.getSystemColor(SWT.COLOR_BLUE);
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());// 关键点1.外层的容器layout为FillLayout
		// set the size of the scrolled content - method 1
//		final ScrolledComposite sc1 = new ScrolledComposite(shell, SWT.H_SCROLL
//				| SWT.V_SCROLL | SWT.BORDER);
		final test2 sc1 = new test2(shell, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
//		final Composite c1 = new Composite(sc1, SWT.NONE);
//		sc1.setContent(c1);// 关键点2.设置Scrolled容器的Content为内层的容器
//		c1.setBackground(red);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 4;
//		c1.setLayout(layout);
//		Button b1 = new Button(c1, SWT.PUSH);
//		b1.setText("first button");
//		c1.setSize(c1.computeSize(SWT.DEFAULT, SWT.DEFAULT));// 关键点3
		// set the minimum width and height of the scrolled content - method 2
	
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
