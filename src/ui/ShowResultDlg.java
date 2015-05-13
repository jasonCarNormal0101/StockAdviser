package ui;

import java.net.URLEncoder;

import javax.swing.JTable;

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
	private JTable table;
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
		
		
		TableColumn num_head = new TableColumn(table, SWT.NONE);
		num_head.setWidth(72);
		num_head.setText("股票代码");
		
		TableColumn name_head = new TableColumn(table, SWT.NONE);
		name_head.setWidth(72);
		name_head.setText("股票简称");
		
		TableColumn change_head = new TableColumn(table, SWT.NONE);
		change_head.setWidth(60);
		change_head.setText("涨跌幅");
		
		TableColumn price_head = new TableColumn(table, SWT.NONE);
		price_head.setWidth(62);
		price_head.setText("现价");
		
		TableColumn pe_head = new TableColumn(table, SWT.NONE);
		pe_head.setWidth(70);
		pe_head.setText("市盈率");
		
		TableColumn forePE_head = new TableColumn(table, SWT.NONE);
		forePE_head.setWidth(76);
		forePE_head.setText("预计市盈率");
		
		TableColumn pb_head = new TableColumn(table, SWT.NONE);
		pb_head.setWidth(70);
		pb_head.setText("市净率");
		
		TableColumn equity_head = new TableColumn(table, SWT.NONE);
		equity_head.setWidth(94);
		equity_head.setText("总股本");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("New Column");
		create();
		
		Object[][] data={
				{"00607.SZ","abc","5.203","16.58","17590.61","147.59","13.37"},		
				{"00607.SZ","abc","5.203","16.58","17590.61","147.59","13.37"},
		};
		table = new JTable(Info,Names);
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
