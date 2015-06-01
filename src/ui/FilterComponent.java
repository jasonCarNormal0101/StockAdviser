package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import entity.Filter;

public class FilterComponent extends Composite {

	private Shell shell;
    private Filter data;
	private Label name;
	private Label type;
	private Label price;
	private Label volumes;
	private Label handle;
	private Label change;
	private Label delete;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FilterComponent(Group parent, int style,Filter filter) {
		super(parent, style);
		shell = getShell();
        data=filter;
		name = new Label(this, SWT.NONE);
		name.setText(filter.get_name());
		name.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		name.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		name.setBounds(0, 10, 84, 19);

		type = new Label(this, SWT.NONE);
		type.setText(filter.get_sign()+filter.get_Value());
		type.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		type.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		type.setBounds(89, 10, 30, 19);


		delete = new Label(this, SWT.NONE);
		delete.setBounds(180, 12, 25, 17);
		delete.setText("删除");
		type.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		type.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
//		delete.setVisible(false);
	}

	public Label getLabel(int index) {
		switch (index) {
		case 1:
			return type;
		case 2:
			return price;
		case 3:
			return volumes;
		case 4:
			return handle;
		default:
			return null;
		}
	}

	public Label getChange() {
		return change;
	}

	public Label getDelete() {
		return delete;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
