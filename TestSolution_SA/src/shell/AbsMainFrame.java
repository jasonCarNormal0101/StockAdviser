package shell;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.chainsaw.Main;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.SWTBotTestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import ui.MainFrame;

public class AbsMainFrame extends SWTBotTestCase{
	static Thread uiThread;

	static Shell shell;

	static MainFrame instance;
	public MainFrame getInstance(){
		return instance;
	}
	private final static CyclicBarrier swtBarrier = new CyclicBarrier(2);

	@BeforeClass
	public static synchronized void setupApp() {
		if (uiThread == null) {
			uiThread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							// open and layout the shell
							MainFrame mainFrame = new MainFrame();
							shell = mainFrame.getShell();
                            instance=mainFrame;
							

							// wait for the test setup
							swtBarrier.await();

							// run the event loop
							mainFrame.open();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			uiThread.setDaemon(true);
			uiThread.start();
		}
	}

	@Before
	public final void setupSWTBot() throws InterruptedException,
			BrokenBarrierException {
		// synchronize with the thread opening the shell
		swtBarrier.await();
		bot = new SWTBot(shell);
	}

	@After
	public void closeShell() throws InterruptedException {
		// close the shell
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				shell.close();
				shell.dispose();
			}
		});
	}

}
