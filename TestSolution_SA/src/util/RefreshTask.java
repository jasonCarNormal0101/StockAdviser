package util;

import org.eclipse.swt.widgets.Display;

public class RefreshTask {
	static RefreshMethod refreshMethod;

	public static void addRefreshTask(Display display, RefreshMethod _refreshMethod) {
		refreshMethod = _refreshMethod;
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				refreshMethod.refresh();
			}
		});
	}
}
