package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import model.CollectionCondition;
//import model.Condition;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;

import org.eclipse.swt.events.MouseEvent;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.wb.swt.SWTResourceManager;

import controller.DBCatcherOnX;

import controller.DBCatherOnT;
import controller.SqlDB;

import entity.Filter;
import entity.FilterConditions;

public class NewShowResultDlg extends Dialog {

	private Shell parentShell;
	private Shell _shell;
	private FilterConditions _filterConditions;

	private static final String[] HEADER = new String[] { "序号", "股票代码", "股票简称",
			"涨跌幅(%)", "现价(元)", "市盈率(pe)", "动态市盈率", "市净率" };
	private static final int[] HEADER_SIZE = new int[] { 55, 90, 75, 75, 70,
			100, 85, 75 };
	public static final String[] TABLE_COL_NAME = new String[] { "pcRadio",
			"curPrice", "pe", "dynamicPE", "pb" };

	protected Shell shell;
	private Composite topComposite;


	private ArrayList<SqlDB> SQLdbs;
	private SqlDB SQLdb;
	private ArrayList<String> sourceNames;

	private Composite stockListComposite;
	private Table stockListTable;
	public NewShowResultDlg(Shell parent, FilterConditions filterConditions,
			SqlDB SQLdb) {
		super(parent, SWT.NONE);
		parentShell = getParent();
		_filterConditions = filterConditions;

		this.SQLdb = SQLdb;

	}

	public void open() {
		Display display = Display.getDefault();

		createContents();

		shell.open();
		shell.layout();
	}

	public void eventLooper(Display display) {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void initSql() {
		if (SQLdbs == null) {
			SQLdbs = new ArrayList<SqlDB>();
		}
		if (sourceNames == null) {
			sourceNames = new ArrayList<String>();
		}

		DBCatherOnT ths = new DBCatherOnT();
		DBCatcherOnX xueqiu = new DBCatcherOnX();

		SQLdbs.add(new SqlDB(ths));
		SQLdbs.add(new SqlDB(xueqiu));

		sourceNames.add(ths.getSourceName());
		sourceNames.add(xueqiu.getSourceName());

		SQLdb = SQLdbs.get(0);
	}

	protected void createContents() {
		shell = new Shell(SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		shell.setSize(710, 580);
		shell.setText("搜素结果");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		topComposite = new Composite(shell, SWT.NONE);
		topComposite.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		topComposite.setLayout(new FormLayout());

		createResultComposite();

	}

	public void createResultComposite() {

		stockListComposite = new Composite(topComposite, SWT.NONE);
		FormData fd = new FormData();
		fd.top = new FormAttachment(0, 0);
		fd.bottom = new FormAttachment(100, 0);
		fd.right = new FormAttachment(0, 700);
		fd.left = new FormAttachment(0, 0);
		stockListComposite.setLayoutData(fd);
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

			tblclmnNewColumn.setResizable(false);
			tblclmnNewColumn.setAlignment(SWT.CENTER);
			tblclmnNewColumn.setWidth(HEADER_SIZE[i]);
			tblclmnNewColumn.setText(HEADER[i]);
		}

		try {
			createTableItem();
		} catch (SQLException e) {
		}
		startSerchNShow();

	}

	public void createTableItem() throws SQLException {
		ResultSet rs = SQLdb.query();
	}

	public void clearTableItem() {
		stockListTable.removeAll();
	}

	public void showResult(ResultSet rs) throws SQLException {

		clearTableItem();

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

			}
			tableItem.setText(text);
		}
		rs.close();

	}

	public void startSerchNShow() {
		ResultSet rs = serch();
		try {
			showResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet serch() {
		ResultSet rs = null;
		String query = "";
		ArrayList<Filter> filters = _filterConditions.getFilters();
		for (int i = 0; i < filters.size(); ++i) {
			Filter flt = filters.get(i);

			query += flt.getRealName() + flt.get_sign() + flt.get_Value()
					+ " and ";

		}
		if (query == "") {
			query = "pe>100 and pe<0";
		} else {
			query = query.substring(0, query.length() - 5);
			query = "(" + query + ")";
		}

		rs = SQLdb.query(query);
		return rs;
	}

	public void selecCondition() {

	}

	public Shell getShell() {

		return shell;
	}
}
