package ui;

import java.net.URLEncoder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import entity.FilterConditions;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

public class ShowResultDlg extends Dialog {
	
	private Shell parentShell;
	private Shell _shell;
	private FilterConditions _filterConditions;
	private Table table;
	private Table table_1;
    public ShowResultDlg(Shell parent,FilterConditions filterConditions){
    	super(parent, SWT.NONE);
		parentShell = getParent();
		_filterConditions = filterConditions;
		_shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		Composite composite = new Composite(_shell, SWT.V_SCROLL);
		composite.setBounds(0, 0, 605, 403);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 583, 393);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(72);
		tableColumn.setText("股票代码");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(72);
		tableColumn_1.setText("股票简称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(60);
		tableColumn_2.setText("涨跌幅");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(62);
		tableColumn_3.setText("现价");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(70);
		tableColumn_4.setText("市盈率");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(76);
		tableColumn_5.setText("预计市盈率");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(70);
		tableColumn_6.setText("市净率");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(94);
		tableColumn_7.setText("总股本");
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(10, 38, 85, 45);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		create();
    }
	private void create() {
		// TODO Auto-generated method stub
		
	}
	
	public void open() {
		// TODO Auto-generated method stub
		_shell.setSize(611, 432);
		_shell.setText(getText());
		final Display display = _shell.getDisplay();

		// create();

		// Your code goes here (widget creation, set result, etc).
		_shell.open();
		// Display display = parent.getDisplay();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}
}
