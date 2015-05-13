package ui;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.commands.ToggleState;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.sun.xml.internal.ws.wsdl.writer.document.OpenAtts;

import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class MyMainFrame {
	private Display display;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Button button;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		// TODO Auto-generated method stub
			shell = new Shell(display,SWT.CLOSE | SWT.MIN);
			shell.setToolTipText("");
			shell.setSize(257, 470);
			shell.setText("SWT Application");
			
			Composite composite = new Composite(shell, SWT.NONE);
			composite.setBounds(10, 10, 225, 423);
			
			TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
			tabFolder.setBounds(10, 0, 215, 289);
			
			TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
			tbtmNewItem.setText("                      选股                     ");
			
			Composite composite_1 = new Composite(tabFolder, SWT.NONE);
			tbtmNewItem.setControl(composite_1);
			
			Group group = new Group(composite_1, SWT.NONE);
			group.setBounds(0, 10, 116, 157);
			
			button = new Button(group, SWT.TOGGLE);
			button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					if(button.getSelection()){
						System.out.println("on");
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			button.setBounds(10, 10, 98, 17);
			button.setText("涨跌幅");
			
			Button button_1 = new Button(group, SWT.TOGGLE);
			button_1.setBounds(10, 33, 98, 17);
			button_1.setText("价格");
			
			Button btnPe = new Button(group, SWT.TOGGLE);
			btnPe.setLocation(10, 56);
			btnPe.setSize(98, 17);
			btnPe.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			btnPe.setText("市盈率");
			
			Button btnCheckButton = new Button(group, SWT.TOGGLE);
			btnCheckButton.setLocation(10, 81);
			btnCheckButton.setSize(98, 17);
			btnCheckButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			btnCheckButton.setText("预测市盈率");
			
			Button button_2 = new Button(group, SWT.TOGGLE);
			button_2.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_2.setBounds(10, 127, 98, 17);
			button_2.setText("总股本");
			
			Button button_3 = new Button(group, SWT.TOGGLE);
			button_3.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_3.setLocation(10, 104);
			button_3.setSize(98, 17);
			button_3.setText("市净率");
			
			Group group_1 = new Group(composite_1, SWT.NONE);
			group_1.setBounds(122, 10, 76, 157);
			
			Label lblNewLabel = new Label(group_1, SWT.NONE);
			lblNewLabel.setAlignment(SWT.CENTER);
			lblNewLabel.setBounds(0, 10, 61, 17);
			lblNewLabel.setText("<3");
			
			Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
			lblNewLabel_1.setAlignment(SWT.CENTER);
			lblNewLabel_1.setBounds(0, 55, 61, 17);
			lblNewLabel_1.setText("<3");
			
			Label lblNewLabel_2 = new Label(group_1, SWT.NONE);
			lblNewLabel_2.setAlignment(SWT.CENTER);
			lblNewLabel_2.setBounds(0, 81, 61, 17);
			lblNewLabel_2.setText("<3");
			
			Label lblNewLabel_3 = new Label(group_1, SWT.NONE);
			lblNewLabel_3.setAlignment(SWT.CENTER);
			lblNewLabel_3.setBounds(0, 104, 61, 17);
			lblNewLabel_3.setText("=20");
			
			Label lblNewLabel_4 = new Label(group_1, SWT.NONE);
			lblNewLabel_4.setAlignment(SWT.CENTER);
			lblNewLabel_4.setBounds(0, 127, 61, 17);
			lblNewLabel_4.setText("200亿");
			
			Label label = new Label(group_1, SWT.CENTER);
			label.setBounds(0, 32, 61, 17);
			label.setText("2<x<3");
			
			Button button_4 = new Button(composite_1, SWT.NONE);
			button_4.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_4.setBounds(0, 222, 102, 27);
			button_4.setText("收藏");
			
			Combo combo = new Combo(composite_1, SWT.NONE);
			combo.setBounds(0, 191, 102, 25);
			
			Button button_5 = new Button(composite_1, SWT.NONE);
			button_5.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_5.setBounds(118, 222, 80, 27);
			button_5.setText("搜索");
			
			Composite composite_4 = new Composite(composite, SWT.BORDER);
			composite_4.setBounds(10, 302, 205, 100);
			
			Label label_1 = new Label(composite_4, SWT.NONE);
			label_1.setFont(SWTResourceManager.getFont("Microsoft JhengHei UI", 12, SWT.NORMAL));
			label_1.setBounds(10, 10, 61, 17);
			label_1.setText("涨跌幅");
			
			text = new Text(composite_4, SWT.BORDER);
			text.setBounds(23, 55, 48, 23);
			
			text_1 = new Text(composite_4, SWT.BORDER);
			text_1.setBounds(143, 55, 48, 23);
			
			Label label_2 = new Label(composite_4, SWT.NONE);
			label_2.setBounds(92, 58, 30, 17);
			label_2.setText("<x<");
		
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		Display homeDisplay = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(homeDisplay),
				new Runnable() {
					public void run() {
						try {
							MyMainFrame window = new MyMainFrame();
							window.open();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}
}
