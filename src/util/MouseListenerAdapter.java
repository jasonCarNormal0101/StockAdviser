package util;

import java.awt.event.MouseAdapter;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public abstract class MouseListenerAdapter implements SelectionListener {

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	abstract public void widgetSelected(SelectionEvent arg0);
}
