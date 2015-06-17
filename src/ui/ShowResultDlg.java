package ui;

import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Condition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import entity.Filter;
import entity.FilterConditions;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import controller.SQLdb;

public class ShowResultDlg extends Dialog {
	private Table stockListTable;
	private Shell parentShell;
	private Shell _shell;
	private SQLdb sqldb;
	private Composite stockListComposite;
	private Composite topComposite;
	private FilterConditions _filterConditions;
	private static final String[] HEADER = new String[] { "序号", "股票代码", "股票简称",
			"涨跌幅(%)", "现价(元)", "市盈率(pe)", "动态市盈率", "市净率" };
	private static final int[] HEADER_SIZE = new int[] { 55, 90, 75, 75, 70,
			100, 85, 75 };
	public static final String[] TABLE_COL_NAME = 
			new String[]{"priceChangeRatio", "curPrice", "pe", "dynamicPE", "pb"};

	public ShowResultDlg(Shell parent, FilterConditions filterConditions,SQLdb sqLdb) {
		super(parent, SWT.NONE);
		parentShell = getParent();
		_filterConditions = filterConditions;
		_shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.sqldb=sqLdb;
		
	

	}

	

	private void createComposite() {
		// TODO Auto-generated method stub
		topComposite = new Composite(_shell, SWT.NONE);
		topComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		topComposite.setLayout(new FormLayout());
		stockListComposite = new Composite(topComposite, SWT.NONE);
		stockListComposite.setLocation(0, 0);
		stockListComposite.setSize(611, 432);
//		FormData fd_stockListComposite = new FormData();
//		fd_stockListComposite.top = new FormAttachment(0, 0);
//		fd_stockListComposite.bottom = new FormAttachment(100, 0);
//		fd_stockListComposite.right = new FormAttachment(0, 0);
//		fd_stockListComposite.left = new FormAttachment(0, 0);
//		stockListComposite.setLayoutData(fd_stockListComposite);
		stockListComposite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		stockListComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		stockListTable = new Table(stockListComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		stockListTable.setHeaderVisible(true);
		stockListTable.setLinesVisible(true);
		for (int i = 0; i < HEADER.length; ++i) {
			TableColumn tblclmnNewColumn = new TableColumn(stockListTable,
					SWT.CENTER);
			tblclmnNewColumn.setWidth(HEADER_SIZE[i]);
			tblclmnNewColumn.setText(HEADER[i]);
			tblclmnNewColumn.setResizable(false);
			tblclmnNewColumn.setAlignment(SWT.CENTER);
		}
		try {
			createTableItem();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createTableItem() throws SQLException{
		ResultSet rs = sqldb.query();
	}

	public void open() {
		// TODO Auto-generated method stub
		_shell.setSize(611, 432);
		_shell.setText(getText());
		final Display display = _shell.getDisplay();

		createComposite();
		ResultSet rs = reseach();
		try {
			showResult(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Your code goes here (widget creation, set result, etc).
		_shell.open();
		// Display display = parent.getDisplay();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

	public void clearTableItem() {
		stockListTable.removeAll();
	}

	public void showResult(ResultSet rs) throws SQLException {

		clearTableItem();
		System.out.println("Table showing");
		int index = 1;
		while (rs.next()) {
			TableItem tableItem = new TableItem(stockListTable, SWT.CENTER);
			String[] text = new String[8];

			text[0] = String.valueOf(index++);

			for (int i = 1; i < 8; ++i) {
				String str = rs.getString(i);
				if (str == null)
					str = "--";
				text[i] = str;
				System.out.print("Table:"+str+"  \r\n");
			}
			tableItem.setText(text);
			
		}
		rs.close();

//		lblResultCount.setText("共有 " + (index - 1) + " 股符合条件");
	}

	public void startReseach() {
		
				
				ResultSet rs = reseach();
				try {
					showResult(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	//开始搜索
	public ResultSet reseach(){
		ResultSet rs = null;
		String querySql = "";
		ArrayList<Filter> filters=_filterConditions.getFilters();
		for(int i = 0; i < filters.size(); ++i){
			Filter flt = filters.get(i);
			
			querySql += flt.getRealName()+flt.get_sign()+flt.get_Value()+ " and ";
		
		}
		if(querySql == ""){
			querySql = "pe>100 and pe<0";
		}
		else{
			querySql = querySql.substring(0, querySql.length() - 5);
			querySql = "(" + querySql + ")" ;
		}
		
		rs = sqldb.query(querySql);
		System.out.println("My sql query is:"+querySql);
		return rs;
	}


}
