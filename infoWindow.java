package sudokuSolver;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class infoWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public infoWindow() {
		try {
			open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 401);
		shell.setText("Information");
		
		Label lblG = new Label(shell, SWT.WRAP);
		lblG.setBounds(10, 10, 408, 325);
		lblG.setText(" Sudoku Solver, David Reid, 2018 \n \n "
				+ "Fill in the initial values and then click Solve. \n \n"
				+ "Solve: Uses a basic algorithm to complete rows, columns and 3x3 grids."
				+ " It then uses permutations of 3x3 grids to find the final solution if required (for harder puzzles)"
				+ " Has solved all sudoku tested from Easy-Evil difficulty, but not guaranteed to find a solution. \n \n"); 

	}
}
