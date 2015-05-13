package ui;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.commands.ToggleState;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.sun.xml.internal.ws.wsdl.writer.document.OpenAtts;

import entity.Filter;
import entity.FilterConditions;

import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.omg.CORBA.PUBLIC_MEMBER;

import util.MouseListenerAdapter;

public class MainFrame {
	private Display display;
	protected Shell shell;
	private FilterConditions filterConditions=new FilterConditions();

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
		shell = new Shell(display, SWT.CLOSE | SWT.MIN);
		shell.setSize(566, 470);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("Microsoft Yi Baiti", 9,
				SWT.BOLD));
		composite.setBounds(0, 0, 560, 433);

		Composite composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setBounds(0, 0, 175, 433);

		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(10, 10, 61, 17);
		label.setText("条件类型");

		Label lb_prizelimit = new Label(composite_1, SWT.NONE);
		lb_prizelimit.setBounds(35, 49, 61, 17);
		lb_prizelimit.setText("涨跌幅");

		Label lb_prize = new Label(composite_1, SWT.NONE);
		lb_prize.setText("现价");
		lb_prize.setBounds(35, 82, 61, 17);

		Label lb_pe = new Label(composite_1, SWT.NONE);
		lb_pe.setText("市盈率");
		lb_pe.setBounds(35, 117, 61, 17);

		Label lb_forePE = new Label(composite_1, SWT.NONE);
		lb_forePE.setText("预测市盈率");
		lb_forePE.setBounds(35, 153, 61, 17);

		Label lb_pb = new Label(composite_1, SWT.NONE);
		lb_pb.setText("市净率");
		lb_pb.setBounds(35, 189, 61, 17);

		Label lb_totalquity = new Label(composite_1, SWT.NONE);
		lb_totalquity.setText("总股本");
		lb_totalquity.setBounds(35, 225, 61, 17);

		AddButton btn_addPL = new AddButton(composite_1, lb_prizelimit.getText(), filterConditions);
		btn_addPL.setBounds(99, 49, 21, 17);

		AddButton btn_addPrize = new AddButton(composite_1, lb_prize.getText(), filterConditions);
		btn_addPrize.setBounds(99, 82, 21, 17);

		AddButton btn_addPE = new AddButton(composite_1, lb_pe.getText(), filterConditions);
		btn_addPE.setBounds(99, 117, 21, 17);

		AddButton btn_addFPE = new AddButton(composite_1, lb_forePE.getText(), filterConditions);
		btn_addFPE.setBounds(99, 153, 21, 17);

		AddButton btn_addPB = new AddButton(composite_1, lb_pb.getText(), filterConditions);
		btn_addPB.setBounds(99, 189, 21, 17);

		AddButton btn_addTQ = new AddButton(composite_1, lb_totalquity.getText(), filterConditions);
		btn_addTQ.setBounds(99, 225, 21, 17);

		Button btnGo = new Button(composite_1, SWT.NONE);
		btnGo.setBounds(35, 297, 80, 27);
		btnGo.setText("GO");
		btnGo.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI Light",
				12, SWT.BOLD));
		label_1.setBounds(191, 10, 87, 27);
		label_1.setText("过滤器");

		Composite composite_2 = new Composite(composite, SWT.BORDER | SWT.V_SCROLL);
		composite_2.setBounds(216, 43, 289, 294);

		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(381, 11, 124, 25);
		combo.setText("此处选择已收藏");

		Button button = new Button(composite, SWT.NONE);
		button.setBounds(381, 354, 80, 27);
		button.setText("收藏");

	}

	private class AddButton {
		public Button button;

		public AddButton(Composite parent, String addWhat,
				FilterConditions filterlist) {
			button = new Button(parent, SWT.NONE);

			button.setText("+");
			button.addSelectionListener(new AddFilterListener(shell, addWhat,
					filterlist));
		}

		public void setBounds(int x, int y, int width, int height) {
			button.setBounds(x, y, width, height);
		}

		private class AddFilterListener extends MouseListenerAdapter {

			Shell _parent;
			String _addWhat;
			FilterConditions _filterList;

			public AddFilterListener(Shell parent, String addWhat,
					FilterConditions filterlist) {
				_parent = parent;
				_addWhat = addWhat;
				_filterList = filterlist;
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				AddFilterDlg dlg = new AddFilterDlg(_parent, _addWhat,
						_filterList);
				dlg.open();
			}

		}
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
							MainFrame window = new MainFrame();
							window.open();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}
}
