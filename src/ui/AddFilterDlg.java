package ui;

import java.util.ArrayList;
//import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Composite;

import entity.Filter;

public class AddFilterDlg extends Dialog {

	private Shell parentShell;
	private Shell shell;
	private ArrayList<Filter> _filterList;
	private String _addWhat;
	private Text value;
	private Filter result;
	private Button btnOK;
	private Button btnCancel;

	public AddFilterDlg(Shell parent, String addWhat,
			ArrayList<Filter> filterList) {
		// TODO Auto-generated constructor stub
		super(parent, SWT.NONE);
		parentShell = getParent();
		_filterList = filterList;
		_addWhat = addWhat;
		shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		create();
	}

	public void open() {
		// TODO Auto-generated method stub
		shell.setSize(407, 261);
		shell.setText(getText());
		final Display display = shell.getDisplay();

		// create();

		// Your code goes here (widget creation, set result, etc).
		shell.open();
		// Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

	public void create() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 381, 212);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(60, 70, 61, 17);
		lblNewLabel.setText(_addWhat);

		value = new Text(composite, SWT.BORDER);
		value.setBounds(127, 64, 122, 23);

		btnOK = new Button(composite, SWT.NONE);
		btnOK.setBounds(60, 129, 72, 27);
		btnOK.setText("确定");
		btnOK.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(value.getText());
				if (!value.getText().isEmpty())
					{result=split(value.getText());
					result.set_name(_addWhat);
					_filterList.add(result);
					}
				shell.close();
				shell.dispose();
			}

		});

		btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(176, 129, 72, 27);
		btnCancel.setText("取消");
		btnCancel.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				shell.close();
				shell.dispose();
			}

		});
	}

	public Text getMoney() {
		return value;
	}




}
