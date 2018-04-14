package sudokuSolver;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import java.util.Random;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//SUDOKU SOLVER
// Version 0.1
// The basic solving algorithm involves: 1) Iterating through the grid along rows, columns and boxes checking for
// positions where only one possible value can go (Will normally fully solve Easy/Medium difficulty sudoku).
// 2) Once no changes can be made this way the guessSolve() method is run whereby the grid with the most missing values (except if all 9 are missing)
// is assigned a random permutation of values and then technique 1) is repeated to see if a solution is found. If not, then a new permutation is checked.
// This will normally solve hard/evil difficulty sudoku but cannot guarantee a solution.
// A random solve method is also within this code but not implemented in the GUI because it is a brute force method which takes a long time.
// Any additional methods will be implemented if it is found that the hardest puzzles cannot be solved with the current methods.

//TODO: Improve the output of information to show the permutations being tried etc.
// Add a method for creating sudoku rather than solving them, allowing the user to specify difficulty level.


public class homeScreen {

	protected Shell shell;
	Random rn = new Random();
	//arraylist containing all the text boxes below
	private ArrayList<Text> values = new ArrayList<Text>();
	
	//THE GRID THAT MAKES UP THE FINAL SOLUTION
	//the arrays that make up the sudoku
	// array of 9x9 boxes: 0 is top left, 8 is bottom right, moving left to right
	private Integer[][] boxes = new Integer[9][9];
	
	//A TEST SOLUTION FOR THE guessSolve() function
	private Integer[][] guess_solution = new Integer[9][9];
	
	//private boolean change;	//a variable to store whether a change has been made to the grid or not
	
	//text boxes for each sudoku box
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Text text_19;
	private Text text_20;
	private Text text_21;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Text text_25;
	private Text text_26;
	private Text text_27;
	private Text text_28;
	private Text text_29;
	private Text text_30;
	private Text text_31;
	private Text text_32;
	private Text text_33;
	private Text text_34;
	private Text text_35;
	private Text text_36;
	private Text text_37;
	private Text text_38;
	private Text text_39;
	private Text text_40;
	private Text text_41;
	private Text text_42;
	private Text text_43;
	private Text text_44;
	private Text text_45;
	private Text text_46;
	private Text text_47;
	private Text text_48;
	private Text text_49;
	private Text text_50;
	private Text text_51;
	private Text text_52;
	private Text text_53;
	private Text text_54;
	private Text text_55;
	private Text text_56;
	private Text text_57;
	private Text text_58;
	private Text text_59;
	private Text text_60;
	private Text text_61;
	private Text text_62;
	private Text text_63;
	private Text text_64;
	private Text text_65;
	private Text text_66;
	private Text text_67;
	private Text text_68;
	private Text text_69;
	private Text text_70;
	private Text text_71;
	private Text text_72;
	private Text text_73;
	private Text text_74;
	private Text text_75;
	private Text text_76;
	private Text text_77;
	private Text text_78;
	private Text text_79;
	private Text text_80;
	
	//output console on the homescreen.
	private Text outputText;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			homeScreen window = new homeScreen();
			window.open();
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
		shell.setSize(600, 600);
		shell.setText("Sudoku Solver");
		
		//button to click to solve sudoku
				Button btnSolve = new Button(shell, SWT.NONE);
				btnSolve.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						printToOutput("Solving.....");
						if(checkValidInput()) {	//check if the values entered into the grid are valid: numbers 1-9
							//if they are valid then continue solving the puzzle
							//System.out.println("Valid values");
							//turn the string integer input values into an Integer array: stored in array "boxes"
							createArray();
							//check that no repeats have been mistakenly entered into rows or columns
							if(checkRowsForRepeats(boxes) && checkColumnsForRepeats(boxes)) {
								addToGrid(boxes);	//this function should add whatever it can to the grid using sudoku rules for solving
								//randomSolveSudoku();	//moved to a different button on the GUI
								if(!checkSolved(boxes)) {
									printToOutput("Permutation algorithm required");
									guessSolve();	//makes a guess using a permutation and then tries to solve.
									if(!checkSolved(boxes)) {
										doubleGuessSolve();		//if the initial guess solve doesn't work then try a more computationally expensive solution
										//printToOutput("Solution not found, click randSolve, to use a brute force technique to finish");
										printToOutput("Another method will be implemented in next version");
									}
								}
								printToOutput("Finished");
								printSolution(boxes);
							} else {
								printToOutput("Error in entered values");
							}
						} else {
						//if not valid then print an error message
						printToOutput("Re-enter values");
					}
				}
				});
				btnSolve.setBounds(58, 53, 105, 35);
				btnSolve.setText("Solve");
				
				//button clears all values input into sudoku
				Button btnClear = new Button(shell, SWT.NONE);
				btnClear.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						clearValues();
					}
				});
				btnClear.setBounds(391, 53, 105, 35);
				btnClear.setText("Clear");
		
	// Each number can be input to a textbox from which the initial array will be formed.	
		text = new Text(shell, SWT.BORDER);
		text.setBounds(133, 121, 30, 30);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(164, 121, 30, 30);
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(195, 121, 30, 30);
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(133, 153, 30, 30);
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(164, 153, 30, 30);
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBounds(195, 153, 30, 30);
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setBounds(133, 185, 30, 30);
		text_7 = new Text(shell, SWT.BORDER);
		text_7.setBounds(164, 185, 30, 30);
		text_8 = new Text(shell, SWT.BORDER);
		text_8.setBounds(195, 185, 30, 30);
		text_9 = new Text(shell, SWT.BORDER);
		text_9.setBounds(231, 121, 30, 30);
		text_10 = new Text(shell, SWT.BORDER);
		text_10.setBounds(262, 121, 30, 30);
		text_11 = new Text(shell, SWT.BORDER);
		text_11.setBounds(293, 121, 30, 30);
		text_12 = new Text(shell, SWT.BORDER);
		text_12.setBounds(231, 153, 30, 30);
		text_13 = new Text(shell, SWT.BORDER);
		text_13.setBounds(262, 153, 30, 30);
		text_14 = new Text(shell, SWT.BORDER);
		text_14.setBounds(293, 153, 30, 30);
		text_15 = new Text(shell, SWT.BORDER);
		text_15.setBounds(231, 185, 30, 30);
		text_16 = new Text(shell, SWT.BORDER);
		text_16.setBounds(262, 185, 30, 30);
		text_17 = new Text(shell, SWT.BORDER);
		text_17.setBounds(293, 185, 30, 30);
		text_18 = new Text(shell, SWT.BORDER);
		text_18.setBounds(329, 121, 30, 30);
		text_19 = new Text(shell, SWT.BORDER);
		text_19.setBounds(360, 121, 30, 30);
		text_20 = new Text(shell, SWT.BORDER);
		text_20.setBounds(391, 121, 30, 30);
		text_21 = new Text(shell, SWT.BORDER);
		text_21.setBounds(329, 153, 30, 30);
		text_22 = new Text(shell, SWT.BORDER);
		text_22.setBounds(360, 153, 30, 30);
		text_23 = new Text(shell, SWT.BORDER);
		text_23.setBounds(391, 153, 30, 30);
		text_24 = new Text(shell, SWT.BORDER);
		text_24.setBounds(329, 185, 30, 30);
		text_25 = new Text(shell, SWT.BORDER);
		text_25.setBounds(360, 185, 30, 30);
		text_26 = new Text(shell, SWT.BORDER);
		text_26.setBounds(391, 185, 30, 30);
		text_27 = new Text(shell, SWT.BORDER);
		text_27.setBounds(133, 220, 30, 30);
		text_28 = new Text(shell, SWT.BORDER);
		text_28.setBounds(164, 220, 30, 30);
		text_29 = new Text(shell, SWT.BORDER);
		text_29.setBounds(195, 220, 30, 30);
		text_30 = new Text(shell, SWT.BORDER);
		text_30.setBounds(133, 252, 30, 30);
		text_31 = new Text(shell, SWT.BORDER);
		text_31.setBounds(164, 252, 30, 30);
		text_32 = new Text(shell, SWT.BORDER);
		text_32.setBounds(195, 252, 30, 30);
		text_33 = new Text(shell, SWT.BORDER);
		text_33.setBounds(133, 284, 30, 30);
		text_34 = new Text(shell, SWT.BORDER);
		text_34.setBounds(164, 284, 30, 30);
		text_35 = new Text(shell, SWT.BORDER);
		text_35.setBounds(195, 284, 30, 30);
		text_36 = new Text(shell, SWT.BORDER);
		text_36.setBounds(231, 220, 30, 30);
		text_37 = new Text(shell, SWT.BORDER);
		text_37.setBounds(262, 220, 30, 30);
		text_38 = new Text(shell, SWT.BORDER);
		text_38.setBounds(293, 220, 30, 30);
		text_39 = new Text(shell, SWT.BORDER);
		text_39.setBounds(231, 252, 30, 30);
		text_40 = new Text(shell, SWT.BORDER);
		text_40.setBounds(262, 252, 30, 30);
		text_41 = new Text(shell, SWT.BORDER);
		text_41.setBounds(293, 252, 30, 30);
		text_42 = new Text(shell, SWT.BORDER);
		text_42.setBounds(231, 284, 30, 30);
		text_43 = new Text(shell, SWT.BORDER);
		text_43.setBounds(262, 284, 30, 30);
		text_44 = new Text(shell, SWT.BORDER);
		text_44.setBounds(293, 284, 30, 30);
		text_45 = new Text(shell, SWT.BORDER);
		text_45.setBounds(329, 220, 30, 30);
		text_46 = new Text(shell, SWT.BORDER);
		text_46.setBounds(360, 220, 30, 30);
		text_47 = new Text(shell, SWT.BORDER);
		text_47.setBounds(391, 220, 30, 30);
		text_48 = new Text(shell, SWT.BORDER);
		text_48.setBounds(329, 252, 30, 30);
		text_49 = new Text(shell, SWT.BORDER);
		text_49.setBounds(360, 252, 30, 30);
		text_50 = new Text(shell, SWT.BORDER);
		text_50.setBounds(391, 252, 30, 30);
		text_51 = new Text(shell, SWT.BORDER);
		text_51.setBounds(329, 284, 30, 30);
		text_52 = new Text(shell, SWT.BORDER);
		text_52.setBounds(360, 284, 30, 30);
		text_53 = new Text(shell, SWT.BORDER);
		text_53.setBounds(391, 284, 30, 30);
		text_54 = new Text(shell, SWT.BORDER);
		text_54.setBounds(133, 320, 30, 30);
		text_55 = new Text(shell, SWT.BORDER);
		text_55.setBounds(164, 320, 30, 30);
		text_56 = new Text(shell, SWT.BORDER);
		text_56.setBounds(195, 320, 30, 30);
		text_57 = new Text(shell, SWT.BORDER);
		text_57.setBounds(133, 352, 30, 30);
		text_58 = new Text(shell, SWT.BORDER);
		text_58.setBounds(164, 352, 30, 30);
		text_59 = new Text(shell, SWT.BORDER);
		text_59.setBounds(195, 352, 30, 30);
		text_60 = new Text(shell, SWT.BORDER);
		text_60.setBounds(133, 384, 30, 30);
		text_61 = new Text(shell, SWT.BORDER);
		text_61.setBounds(164, 384, 30, 30);
		text_62 = new Text(shell, SWT.BORDER);
		text_62.setBounds(195, 384, 30, 30);
		text_63 = new Text(shell, SWT.BORDER);
		text_63.setBounds(231, 320, 30, 30);
		text_64 = new Text(shell, SWT.BORDER);
		text_64.setBounds(262, 320, 30, 30);
		text_65 = new Text(shell, SWT.BORDER);
		text_65.setBounds(293, 320, 30, 30);
		text_66 = new Text(shell, SWT.BORDER);
		text_66.setBounds(231, 352, 30, 30);
		text_67 = new Text(shell, SWT.BORDER);
		text_67.setBounds(262, 352, 30, 30);
		text_68 = new Text(shell, SWT.BORDER);
		text_68.setBounds(293, 352, 30, 30);
		text_69 = new Text(shell, SWT.BORDER);
		text_69.setBounds(231, 384, 30, 30);
		text_70 = new Text(shell, SWT.BORDER);
		text_70.setBounds(262, 384, 30, 30);
		text_71 = new Text(shell, SWT.BORDER);
		text_71.setBounds(293, 384, 30, 30);
		text_72 = new Text(shell, SWT.BORDER);
		text_72.setBounds(329, 320, 30, 30);
		text_73 = new Text(shell, SWT.BORDER);
		text_73.setBounds(360, 320, 30, 30);
		text_74 = new Text(shell, SWT.BORDER);
		text_74.setBounds(391, 320, 30, 30);
		text_75 = new Text(shell, SWT.BORDER);
		text_75.setBounds(329, 352, 30, 30);
		text_76 = new Text(shell, SWT.BORDER);
		text_76.setBounds(360, 352, 30, 30);
		text_77 = new Text(shell, SWT.BORDER);
		text_77.setBounds(391, 352, 30, 30);
		text_78 = new Text(shell, SWT.BORDER);
		text_78.setBounds(329, 384, 30, 30);
		text_79 = new Text(shell, SWT.BORDER);
		text_79.setBounds(360, 384, 30, 30);
		text_80 = new Text(shell, SWT.BORDER);
		text_80.setBounds(391, 384, 30, 30);
		
		//add all text boxes to arraylist of values
		values.add(text);
		values.add(text_1);
		values.add(text_2);
		values.add(text_3);
		values.add(text_4);
		values.add(text_5);
		values.add(text_6);
		values.add(text_7);
		values.add(text_8);
		values.add(text_9);
		values.add(text_10);
		values.add(text_11);
		values.add(text_12);
		values.add(text_13);
		values.add(text_14);
		values.add(text_15);
		values.add(text_16);
		values.add(text_17);
		values.add(text_18);
		values.add(text_19);
		values.add(text_20);
		values.add(text_21);
		values.add(text_22);
		values.add(text_23);
		values.add(text_24);
		values.add(text_25);
		values.add(text_26);
		values.add(text_27);
		values.add(text_28);
		values.add(text_29);
		values.add(text_30);
		values.add(text_31);
		values.add(text_32);
		values.add(text_33);
		values.add(text_34);
		values.add(text_35);
		values.add(text_36);
		values.add(text_37);
		values.add(text_38);
		values.add(text_39);
		values.add(text_40);
		values.add(text_41);
		values.add(text_42);
		values.add(text_43);
		values.add(text_44);
		values.add(text_45);
		values.add(text_46);
		values.add(text_47);
		values.add(text_48);
		values.add(text_49);
		values.add(text_50);
		values.add(text_51);
		values.add(text_52);
		values.add(text_53);
		values.add(text_54);
		values.add(text_55);
		values.add(text_56);
		values.add(text_57);
		values.add(text_58);
		values.add(text_59);
		values.add(text_60);
		values.add(text_61);
		values.add(text_62);
		values.add(text_63);
		values.add(text_64);
		values.add(text_65);
		values.add(text_66);
		values.add(text_67);
		values.add(text_68);
		values.add(text_69);
		values.add(text_70);
		values.add(text_71);
		values.add(text_72);
		values.add(text_73);
		values.add(text_74);
		values.add(text_75);
		values.add(text_76);
		values.add(text_77);
		values.add(text_78);
		values.add(text_79);
		values.add(text_80);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmInformation = new MenuItem(menu, SWT.NONE);
		mntmInformation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				infoWindow info = new infoWindow();
			}
		});
		mntmInformation.setText("Information");
		
		//output console
		outputText = new Text(shell, SWT.BORDER);
		outputText.setBounds(56, 429, 440, 75);
		
	}
	// clear all the values in the text boxes.
	private void clearValues() {
		for (Text text : values) {
			text.setText("");
		}
	}
	
	
	//method for solving the sudoku when other methods can't continue
	private void randomSolveSudoku() {
		boolean complete = false;
		// for each grid in turn, create a permutation, check it fits the current state and then move to next grid
			while(complete == false) {	//continue running until a solution found
				System.out.println("Refresh");
				int count = 0;
				Integer[][] possibleSolution = new Integer[9][9];	//create a copy of the starting configuration
				//copy all the values of the initial boxes into this possible solution
				//This should reset the possible solution back to the starting grid
				for(int i=0;i<boxes.length;i++) {
					for(int j=0;j<boxes[0].length;j++) {
						possibleSolution[i][j] = boxes[i][j];
					}
				}
				//Test that possible solution has been refreshed
				//System.out.println("Box should be 0: " + possibleSolution[7][6]);
				for(int i=0;i<9;i++) {	//go through each grid separately
					Integer[] possibleGrid = createPermutation(possibleSolution[i]);	//take the starting grid and add random, possible solutions the sub-grid
					possibleSolution[i] = possibleGrid;
					if(!checkGrid(possibleSolution)) {
						//System.out.println("Not right");	
						break;							
					} else {
						count++;	//if check is fine then the grid is OK, so move on and count++.
						//System.out.println("Count = " + count);
					}
					
			}
				
				if(count == 9) {
					complete = true;
					boxes = possibleSolution;
		
				}
			}
			
		} 

	
	// compares the input values to the values 1-9. If something other is input then returns false, otherwise returns true.
	//ADD: NEED TO ADD A CHECK THAT INPUT ROWS DON'T CONTAIN MULTIPLE OF SAME VALUE
	private boolean checkValidInput() {
		for(Text text : values) {
			// get the text box entries as string "values". Parsing to integer requires that integer strings are input.
			//Input text cannot have a space before it
			String value = text.getText();
			if(!value.equals(" ") && !value.equals("") && !value.equals("1") && !value.equals("2") && !value.equals("3") && !value.equals("4") && !value.equals("5") && !value.equals("6") && !value.equals("7") && !value.equals("8") && !value.equals("9")) {
				printToOutput("Invalid value input: " + value.toString());
				return false;
			}
		}
		return true;
	}
	
	//take the string input values and generates the starting array of numbers which will be solved.
	// String values must be parsed to Integers. 0 represents unknown values in the array.
	//Only runs if input values are valid, so no need to check
	// the sudoku is stored in the array boxes
	private void createArray() {
		int box = 0;	//keep track of the box number: 0 is top left and count from left to right and top to bottom within each 3x3 grid and then across to the next grid on the left....
		for(Text text : values) {
			int mainBox = box/9; //the value of the main 3x3 grid it is in
			int smallBox = box%9; // the individual box within the 3x3 grid
			//the coords [mainbox,smallbox] are the positions of each box in the sudoku array
			if(text.getText().equals("")) {
				Integer value = 0;
				boxes[mainBox][smallBox] = value;
				box++;
			} else {
				Integer value = Integer.parseInt(text.getText());
				boxes[mainBox][smallBox]=value;
				box++;
		}
	}
}
	
	//INPUT: Integer[] of a sub-grid
	//OUTPUT: Add permutations of this unknown grid to the corresponding grid permutations arraylist
	private Integer[] createPermutation(Integer[] grid) {
		ArrayList<Integer> missingNums = findMissingValues(grid);
		//System.out.println("Number of missing values= " + missingNums.size());
		Integer[] possibleGrid = grid;		//the grid to return starts as the same as the input grid
		int missingSize = missingNums.size();
		Integer[] perm = new Integer[missingSize];
		//loop through the list of missing numbers, creating a random permutation of them
		for(int i=0; i< missingSize;i++) {
			Integer choice = rn.nextInt(missingNums.size());
			//System.out.println("Choice = " + choice);
			perm[i] = missingNums.get(choice);
			missingNums.remove(perm[i]);
		}
		//add the permutation to the grid
		int count = 0;
		for(int i=0; i< 9; i++) {
			if(possibleGrid[i] == 0) {
				possibleGrid[i] = perm[count];
				count++;
			}
		}
		return possibleGrid;
		
		
		
	}
	
	//INPUT: a 3x3 grid
	//OUTPUT: the missing values as an int[]
	private ArrayList<Integer> findMissingValues(Integer[] grid) {
		ArrayList<Integer> missing = new ArrayList<Integer>();
		for(int i=1; i< 10; i++) {	// loop through all numbers 1-9 that should be in the grid
			int count = 0;
			for(int j=0; j<9;j++) {	//loop through each value in the grid and check if the above value is in there
				if(grid[j] == i) {	//if that value (i) is in the grid then break
					break;
				} else {
					count++;	
				}
			}
			if(count == 9) {	//if after going through the full grid, the count has reached 9 then the number is missing so add it
				//System.out.print("Missing value: " + i);
				missing.add(i);
			}
		}
		return missing;
	}
	
	
	//check if rows and columns do not contain a repeated number
	// return true if grid is fine
	private boolean checkGrid(Integer[][] possibleSolution ) {
		if(checkRows(possibleSolution) && checkColumns(possibleSolution)) {
				return true;
	} else {
		return false;		
	}
	
}
	//check that there is no repeated number in a row - discounting 0
	private boolean checkRows(Integer[][] possibleSolution) {
		for(int i=0;i<9;i++) {	//loop through all rows
			Integer[] row = createRow(possibleSolution, i);		// create the next row to check
			if(!checkRow(row)) {	// if this row doesn't work then return false, else continue
				return false;
		}
	}
		return true;	//if it makes it to the end without returning false then rows are fine: return true
}
	//takes the possible solution and the row number and creates the values in that row
	private Integer[] createRow(Integer[][] possibleSolution, int rowNum) {
		Integer[] row = new Integer[9];	//row to return
		if(rowNum == 0) {
			row[0] = possibleSolution[0][0];
			row[1] = possibleSolution[0][1];
			row[2] = possibleSolution[0][2];
			row[3] = possibleSolution[1][0];
			row[4] = possibleSolution[1][1];
			row[5] = possibleSolution[1][2];
			row[6] = possibleSolution[2][0];
			row[7] = possibleSolution[2][1];
			row[8] = possibleSolution[2][2];
		} else if(rowNum == 1) {
			row[0] = possibleSolution[0][3];
			row[1] = possibleSolution[0][4];
			row[2] = possibleSolution[0][5];
			row[3] = possibleSolution[1][3];
			row[4] = possibleSolution[1][4];
			row[5] = possibleSolution[1][5];
			row[6] = possibleSolution[2][3];
			row[7] = possibleSolution[2][4];
			row[8] = possibleSolution[2][5];
		} else if(rowNum == 2) {
			row[0] = possibleSolution[0][6];
			row[1] = possibleSolution[0][7];
			row[2] = possibleSolution[0][8];
			row[3] = possibleSolution[1][6];
			row[4] = possibleSolution[1][7];
			row[5] = possibleSolution[1][8];
			row[6] = possibleSolution[2][6];
			row[7] = possibleSolution[2][7];
			row[8] = possibleSolution[2][8];
		} else if(rowNum == 3) {
			row[0] = possibleSolution[3][0];
			row[1] = possibleSolution[3][1];
			row[2] = possibleSolution[3][2];
			row[3] = possibleSolution[4][0];
			row[4] = possibleSolution[4][1];
			row[5] = possibleSolution[4][2];
			row[6] = possibleSolution[5][0];
			row[7] = possibleSolution[5][1];
			row[8] = possibleSolution[5][2];
		} else if(rowNum == 4) {
			row[0] = possibleSolution[3][3];
			row[1] = possibleSolution[3][4];
			row[2] = possibleSolution[3][5];
			row[3] = possibleSolution[4][3];
			row[4] = possibleSolution[4][4];
			row[5] = possibleSolution[4][5];
			row[6] = possibleSolution[5][3];
			row[7] = possibleSolution[5][4];
			row[8] = possibleSolution[5][5];
		} else if(rowNum == 5) {
			row[0] = possibleSolution[3][6];
			row[1] = possibleSolution[3][7];
			row[2] = possibleSolution[3][8];
			row[3] = possibleSolution[4][6];
			row[4] = possibleSolution[4][7];
			row[5] = possibleSolution[4][8];
			row[6] = possibleSolution[5][6];
			row[7] = possibleSolution[5][7];
			row[8] = possibleSolution[5][8];
		} else if(rowNum == 6) {
			row[0] = possibleSolution[6][0];
			row[1] = possibleSolution[6][1];
			row[2] = possibleSolution[6][2];
			row[3] = possibleSolution[7][0];
			row[4] = possibleSolution[7][1];
			row[5] = possibleSolution[7][2];
			row[6] = possibleSolution[8][0];
			row[7] = possibleSolution[8][1];
			row[8] = possibleSolution[8][2];
		} else if(rowNum == 7) {
			row[0] = possibleSolution[6][3];
			row[1] = possibleSolution[6][4];
			row[2] = possibleSolution[6][5];
			row[3] = possibleSolution[7][3];
			row[4] = possibleSolution[7][4];
			row[5] = possibleSolution[7][5];
			row[6] = possibleSolution[8][3];
			row[7] = possibleSolution[8][4];
			row[8] = possibleSolution[8][5];
		} else {
			row[0] = possibleSolution[6][6];
			row[1] = possibleSolution[6][7];
			row[2] = possibleSolution[6][8];
			row[3] = possibleSolution[7][6];
			row[4] = possibleSolution[7][7];
			row[5] = possibleSolution[7][8];
			row[6] = possibleSolution[8][6];
			row[7] = possibleSolution[8][7];
			row[8] = possibleSolution[8][8];
		}
		return row;
	}
	
	//checks the row created from createRow
	// 0 values should be ignored, multiple zeros should not return false.
	private boolean checkRow(Integer[] row) {
		for(int i=0;i<row.length;i++) {
			//System.out.print(row[i]);
			for(int j=i+1;j<row.length;j++) {
				if(row[i] == row[j] && row[i] != 0) {	//checking that one of the numbers isn't zero is enough
					//System.out.println("row false");
					return false;			//if at any point there are 2 of the same value then the row fails.
			}
		}
	}
		//System.out.println("row true");
		return true;		//if it makes it through then the row is fine.
}
	
	
	//check that there is no repeated number in a column - discounting 0
	private boolean checkColumns(Integer[][] possibleSolution) {
		for(int i=0;i<9;i++) {	//loop through all columns
			Integer[] col = createColumn(possibleSolution, i);		// create the next column to check
			if(!checkColumn(col)) {	// if this column doesn't work then return false, else continue
				return false;
		}
	}
		return true;	//if it makes it to the end without returning false then columns are fine: return true
}
	
	//takes the possible solution and the row number and creates the values in that column
	//Method copied from row checking, so variables are named row not column
		private Integer[] createColumn(Integer[][] possibleSolution, int colNum) {
			Integer[] row = new Integer[9];	//column to return
			if(colNum == 0) {
				row[0] = possibleSolution[0][0];
				row[1] = possibleSolution[0][3];
				row[2] = possibleSolution[0][6];
				row[3] = possibleSolution[3][0];
				row[4] = possibleSolution[3][3];
				row[5] = possibleSolution[3][6];
				row[6] = possibleSolution[6][0];
				row[7] = possibleSolution[6][3];
				row[8] = possibleSolution[6][6];
			} else if(colNum == 1) {
				row[0] = possibleSolution[0][1];
				row[1] = possibleSolution[0][4];
				row[2] = possibleSolution[0][7];
				row[3] = possibleSolution[3][1];
				row[4] = possibleSolution[3][4];
				row[5] = possibleSolution[3][7];
				row[6] = possibleSolution[6][1];
				row[7] = possibleSolution[6][4];
				row[8] = possibleSolution[6][7];
			} else if(colNum == 2) {
				row[0] = possibleSolution[0][2];
				row[1] = possibleSolution[0][5];
				row[2] = possibleSolution[0][8];
				row[3] = possibleSolution[3][2];
				row[4] = possibleSolution[3][5];
				row[5] = possibleSolution[3][8];
				row[6] = possibleSolution[6][2];
				row[7] = possibleSolution[6][5];
				row[8] = possibleSolution[6][8];
			} else if(colNum == 3) {
				row[0] = possibleSolution[1][0];
				row[1] = possibleSolution[1][3];
				row[2] = possibleSolution[1][6];
				row[3] = possibleSolution[4][0];
				row[4] = possibleSolution[4][3];
				row[5] = possibleSolution[4][6];
				row[6] = possibleSolution[7][0];
				row[7] = possibleSolution[7][3];
				row[8] = possibleSolution[7][6];
			} else if(colNum == 4) {
				row[0] = possibleSolution[1][1];
				row[1] = possibleSolution[1][4];
				row[2] = possibleSolution[1][7];
				row[3] = possibleSolution[4][1];
				row[4] = possibleSolution[4][4];
				row[5] = possibleSolution[4][7];
				row[6] = possibleSolution[7][1];
				row[7] = possibleSolution[7][4];
				row[8] = possibleSolution[7][7];
			} else if(colNum == 5) {
				row[0] = possibleSolution[1][2];
				row[1] = possibleSolution[1][5];
				row[2] = possibleSolution[1][8];
				row[3] = possibleSolution[4][2];
				row[4] = possibleSolution[4][5];
				row[5] = possibleSolution[4][8];
				row[6] = possibleSolution[7][2];
				row[7] = possibleSolution[7][5];
				row[8] = possibleSolution[7][8];
			} else if(colNum == 6) {
				row[0] = possibleSolution[2][0];
				row[1] = possibleSolution[2][3];
				row[2] = possibleSolution[2][6];
				row[3] = possibleSolution[5][0];
				row[4] = possibleSolution[5][3];
				row[5] = possibleSolution[5][6];
				row[6] = possibleSolution[8][0];
				row[7] = possibleSolution[8][3];
				row[8] = possibleSolution[8][6];
			} else if(colNum == 7) {
				row[0] = possibleSolution[2][1];
				row[1] = possibleSolution[2][4];
				row[2] = possibleSolution[2][7];
				row[3] = possibleSolution[5][1];
				row[4] = possibleSolution[5][4];
				row[5] = possibleSolution[5][7];
				row[6] = possibleSolution[8][1];
				row[7] = possibleSolution[8][4];
				row[8] = possibleSolution[8][7];
			} else {
				row[0] = possibleSolution[2][2];
				row[1] = possibleSolution[2][5];
				row[2] = possibleSolution[2][8];
				row[3] = possibleSolution[5][2];
				row[4] = possibleSolution[5][5];
				row[5] = possibleSolution[5][8];
				row[6] = possibleSolution[8][2];
				row[7] = possibleSolution[8][5];
				row[8] = possibleSolution[8][8];
			}
			return row;
		}
	
	
		//checks the row created from createRow
		// 0 values should be ignored, multiple zeros should not return false.
		//Copied from row method, so columns called rows
		private boolean checkColumn(Integer[] col) {
			for(int i=0;i<col.length;i++) {
				//System.out.print(col[i]);
				for(int j=i+1;j<col.length;j++) {
					if(col[i] == col[j] && col[i] != 0) {	//checking that one of the numbers isn't zero is enough
						//System.out.println("column false");
						return false;			//if at any point there are 2 of the same value then the row fails.
				}
			}
		}
			//System.out.println("column true");
			return true;		//if it makes it through then the col is fine.
	}
	
		
//SOLVE solution, rather than random solve==========================================================
	//INPUT: adapts the original boxes to fill in any "obvious" values that can be found using basic rules
	//addToRows: create rows and then check for missing values in each
	//addToColumns: same as rows but for columns
	//should continue running as long as changes are being made to the grid - if not return
	private void addToGrid(Integer[][] boxes) {
		boolean change = true;	//allow the while loop to start running
		Integer[][] start = createCopy(boxes);
		while (change == true) {	//continue running as long as changes are being made.
			
			//change = false;		//no changes have been made yet. If a change is made it should add to change
			for(int i = 0; i < 9; i++) {
				addToRow(boxes,i);
			}
		
			for (int j=0; j<9;j++) {
				addToColumn(boxes,j);
			}
			
			for (int k=0;k<9;k++) {
				addToBox(boxes,k);
			}
			change = checkForChange(start,boxes);
			start = createCopy(boxes);
	}
}
	private void addToRow(Integer[][] boxes, int rowNum) {
		Integer[] row = createRow(boxes,rowNum);
		ArrayList<Integer> missingNums = findMissingValues(row);
		for(Integer missing : missingNums) {
			checkAgainstColumns(boxes,row, missing, rowNum);
		}
		checkSingleRowSpace(boxes, row, missingNums, rowNum);	//JUST ADDED, DELETE IF PROBLEMS
		
	}
	
	//INPUT: the row being checked, the row number in the grid and the value being checked.
	//OUTPUT: Changes the input array boxes to include the value if there is only one possible position
	private void checkAgainstColumns(Integer[][] boxes,Integer[] row, Integer value, int rowNum) {
		//printSolution();	//TESTING
		//printRow(row);
		int possiblePositions = 0;
		Integer location = 0;		//the position in the row to put the number if it only has one possible location
		for (int i=0;i<9;i++) {
			if(row[i] == 0) {
				Integer[] col = createColumn(boxes, i);
				ArrayList<Integer> missingInColumn = findMissingValues(col);
				if(missingInColumn.contains(value)) {
					possiblePositions++;
					location = i;
				}
			}
		}
		if(possiblePositions == 1) {
			//if there is only one possible position then change the boxes array
			row[location] = value;
			rowToGrid(boxes, row,rowNum,location);
			//change++;		//a change has been made
			//printRow(row);
			//printSolution();
		}
	}
	
	private void addToColumn(Integer[][] boxes, int colNum) {
		Integer[] col = createColumn(boxes,colNum);
		ArrayList<Integer> missingNums = findMissingValues(col);
		for(Integer missing : missingNums) {
			checkAgainstRows(boxes, col, missing, colNum);
		}
		checkSingleColumnSpace(boxes, col, missingNums, colNum);	//JUST ADDED, DELETE IF PROBLEMS
	}
		
	//INPUT: the row being checked, the row number in the grid and the value being checked.
		//OUTPUT: Changes the original boxes array to include the value if there is only one possible position
		private void checkAgainstRows(Integer[][] boxes, Integer[] col, Integer value, int colNum) {
			//printSolution();	//TESTING
			//printRow(col);
			int possiblePositions = 0;
			Integer location = 0;		//the position in the row to put the number if it only has one possible location
			for (int i=0;i<9;i++) {
				if(col[i] == 0) {
					Integer[] row = createRow(boxes, i);
					ArrayList<Integer> missingInRow = findMissingValues(row);
					if(missingInRow.contains(value)) {
						possiblePositions++;
						location = i;
					}
				}
			}
			if(possiblePositions == 1) {
				//if there is only one possible position then change the boxes array
				col[location] = value;
				colToGrid(boxes, col,colNum,location);
				//change++;		//a change has been made
				//printRow(col);
				//printSolution();
			}
		}
	
	
	
	//Takes a row with a new value entered and adds this to the full grid.
	private void rowToGrid(Integer[][] boxes, Integer[] row, int rowNum, int rowIndex){
		if(rowNum == 0) {
			if(rowIndex == 0) {
				boxes[0][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][1] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][2] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[1][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[1][1] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[1][2] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[2][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[2][1] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[2][2] = row[rowIndex];
			}
		} else if(rowNum == 1) {
			if(rowIndex == 0) {
				boxes[0][3] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][5] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[1][3] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[1][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[1][5] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[2][3] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[2][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[2][5] = row[rowIndex];
			}
		} else if(rowNum == 2) {
			if(rowIndex == 0) {
				boxes[0][6] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][7] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[1][6] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[1][7] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[1][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[2][6] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[2][7] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[2][8] = row[rowIndex];
			}
		} else if(rowNum == 3) {
			if(rowIndex == 0) {
				boxes[3][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[3][1] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[3][2] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][1] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][2] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[5][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[5][1] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[5][2] = row[rowIndex];
			}
		} else if(rowNum == 4) {
			if(rowIndex == 0) {
				boxes[3][3] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[3][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[3][5] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][3] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][5] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[5][3] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[5][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[5][5] = row[rowIndex];
			}
		} else if(rowNum == 5) {
			if(rowIndex == 0) {
				boxes[3][6] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[3][7] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[3][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][6] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][7] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[5][6] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[5][7] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[5][8] = row[rowIndex];
			}
		} else if(rowNum == 6) {
			if(rowIndex == 0) {
				boxes[6][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[6][1] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[6][2] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[7][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[7][1] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[7][2] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][1] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][2] = row[rowIndex];
			}
		} else if(rowNum == 7) {
			if(rowIndex == 0) {
				boxes[6][3] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[6][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[6][5] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[7][3] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[7][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[7][5] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][3] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][5] = row[rowIndex];
			}
		} else {
			if(rowIndex == 0) {
				boxes[6][6] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[6][7] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[6][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[7][6] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[7][7] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[7][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][6] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][7] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][8] = row[rowIndex];
			}
		}
	}
	
	//Same as rowToGrid, but for columns.
	//Copied from rowToGrid, so variables are named after rows instead of columns.
	private void colToGrid(Integer[][] boxes, Integer[] row, int rowNum, int rowIndex) {
		if(rowNum == 0) {
			if(rowIndex == 0) {
				boxes[0][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][3] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][6] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[3][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[3][3] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[3][6] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[6][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[6][3] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[6][6] = row[rowIndex];
			}
		} else if(rowNum == 1) {
			if(rowIndex == 0) {
				boxes[0][1] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][7] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[3][1] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[3][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[3][7] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[6][1] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[6][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[6][7] = row[rowIndex];
			}
		} else if(rowNum == 2) {
			if(rowIndex == 0) {
				boxes[0][2] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[0][5] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[0][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[3][2] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[3][5] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[3][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[6][2] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[6][5] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[6][8] = row[rowIndex];
			}
		} else if(rowNum == 3) {
			if(rowIndex == 0) {
				boxes[1][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[1][3] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[1][6] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][3] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][6] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[7][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[7][3] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[7][6] = row[rowIndex];
			}
		} else if(rowNum == 4) {
			if(rowIndex == 0) {
				boxes[1][1] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[1][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[1][7] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][1] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][7] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[7][1] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[7][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[7][7] = row[rowIndex];
			}
		} else if(rowNum == 5) {
			if(rowIndex == 0) {
				boxes[1][2] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[1][5] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[1][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[4][2] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[4][5] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[4][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[7][2] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[7][5] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[7][8] = row[rowIndex];
			}
		} else if(rowNum == 6) {
			if(rowIndex == 0) {
				boxes[2][0] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[2][3] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[2][6] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[5][0] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[5][3] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[5][6] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][0] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][3] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][6] = row[rowIndex];
			}
		} else if(rowNum == 7) {
			if(rowIndex == 0) {
				boxes[2][1] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[2][4] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[2][7] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[5][1] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[5][4] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[5][7] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][1] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][4] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][7] = row[rowIndex];
			}
		} else {
			if(rowIndex == 0) {
				boxes[2][2] = row[rowIndex];
			} else if(rowIndex == 1) {
				boxes[2][5] = row[rowIndex];
			} else if(rowIndex == 2) {
				boxes[2][8] = row[rowIndex];
			} else if(rowIndex == 3) {
				boxes[5][2] = row[rowIndex];
			} else if(rowIndex == 4) {
				boxes[5][5] = row[rowIndex];
			} else if(rowIndex == 5) {
				boxes[5][8] = row[rowIndex];
			} else if(rowIndex == 6) {
				boxes[8][2] = row[rowIndex];
			} else if(rowIndex == 7) {
				boxes[8][5] = row[rowIndex];
			} else if(rowIndex == 8) {
				boxes[8][8] = row[rowIndex];
			}
		}
	}
	
	//checks if there is only one available position in a 3x3 box and adds the value if so.
	//boxNum tells the grid position of the 3x3 within the 9x9 from 0-8 top left to bottom right
	private void addToBox(Integer[][] boxes, int boxNum) {
		Integer[] box = boxes[boxNum];
		ArrayList<Integer> missingNums = findMissingValues(box);
		for(Integer missing : missingNums) {
			checkBoxAgainstRowCol(boxes, box, missing, boxNum);
		}
	}
	
	//checks a 3x3 grid against the corresponding rows and columns in order to figure out if there is a position available for a number
	private void checkBoxAgainstRowCol(Integer[][] boxes, Integer[] box, int value, int boxNum) {
		int possiblePositions = 0;
		Integer location = 0;		//the position in the box to put the number if it only has one possible location
		for (int i=0;i<9;i++) {
			//find each empty position in the grid
			if(box[i] == 0) {
				Integer[] rowToCheck = new Integer[9];	//the row and column that need to be checked for this empty value
				Integer[] columnToCheck = new Integer[9];
				//decide which row and column to check against
				if(boxNum == 0) {
					if(i == 0) {
						rowToCheck = createRow(boxes,0);
						columnToCheck = createColumn(boxes,0);
				} else if(i == 1) {
					rowToCheck = createRow(boxes,0);
					columnToCheck = createColumn(boxes,1);
				} else if(i==2) {
					rowToCheck = createRow(boxes,0);
					columnToCheck = createColumn(boxes,2);
				} else if(i==3) {
					rowToCheck = createRow(boxes,1);
					columnToCheck = createColumn(boxes,0);
				} else if(i==4) {
					rowToCheck = createRow(boxes,1);
					columnToCheck = createColumn(boxes,1);
				} else if(i==5) {
					rowToCheck = createRow(boxes,1);
					columnToCheck = createColumn(boxes,2);
				} else if(i==6){
					rowToCheck = createRow(boxes,2);
					columnToCheck = createColumn(boxes,0);
				} else if(i==7) {
					rowToCheck = createRow(boxes,2);
					columnToCheck = createColumn(boxes,1);
				} else if(i==8) {
					rowToCheck = createRow(boxes,2);
					columnToCheck = createColumn(boxes,2);
				}
			} else if(boxNum == 1) {
				if(i == 0) {
					rowToCheck = createRow(boxes,0);
					columnToCheck = createColumn(boxes,3);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,0);
				columnToCheck = createColumn(boxes,4);
			} else if(i==2) {
				rowToCheck = createRow(boxes,0);
				columnToCheck = createColumn(boxes,5);
			} else if(i==3) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,3);
			} else if(i==4) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,4);
			} else if(i==5) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,5);
			} else if(i==6){
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,3);
			} else if(i==7) {
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,4);
			} else if(i==8) {
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,5);
			}
			} else if(boxNum == 2) {
				if(i == 0) {
					rowToCheck = createRow(boxes,0);
					columnToCheck = createColumn(boxes,6);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,0);
				columnToCheck = createColumn(boxes,7);
			} else if(i==2) {
				rowToCheck = createRow(boxes,0);
				columnToCheck = createColumn(boxes,8);
			} else if(i==3) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,6);
			} else if(i==4) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,7);
			} else if(i==5) {
				rowToCheck = createRow(boxes,1);
				columnToCheck = createColumn(boxes,8);
			} else if(i==6){
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,6);
			} else if(i==7) {
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,7);
			} else if(i==8) {
				rowToCheck = createRow(boxes,2);
				columnToCheck = createColumn(boxes,8);
			}
			} else if(boxNum == 3) {
				if(i == 0) {
					rowToCheck = createRow(boxes,3);
					columnToCheck = createColumn(boxes,0);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,1);
			} else if(i==2) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,2);
			} else if(i==3) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,0);
			} else if(i==4) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,1);
			} else if(i==5) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,2);
			} else if(i==6){
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,0);
			} else if(i==7) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,1);
			} else if(i==8) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,2);
			}
			} else if(boxNum == 4) {
				if(i == 0) {
					rowToCheck = createRow(boxes,3);
					columnToCheck = createColumn(boxes,3);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,4);
			} else if(i==2) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,5);
			} else if(i==3) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,3);
			} else if(i==4) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,4);
			} else if(i==5) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,5);
			} else if(i==6){
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,3);
			} else if(i==7) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,4);
			} else if(i==8) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,5);
			}
			} else if(boxNum == 5) {
				if(i == 0) {
					rowToCheck = createRow(boxes,3);
					columnToCheck = createColumn(boxes,6);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,7);
			} else if(i==2) {
				rowToCheck = createRow(boxes,3);
				columnToCheck = createColumn(boxes,8);
			} else if(i==3) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,6);
			} else if(i==4) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,7);
			} else if(i==5) {
				rowToCheck = createRow(boxes,4);
				columnToCheck = createColumn(boxes,8);
			} else if(i==6){
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,6);
			} else if(i==7) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,7);
			} else if(i==8) {
				rowToCheck = createRow(boxes,5);
				columnToCheck = createColumn(boxes,8);
			}
			} else if(boxNum == 6) {
				if(i == 0) {
					rowToCheck = createRow(boxes,6);
					columnToCheck = createColumn(boxes,0);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,1);
			} else if(i==2) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,2);
			} else if(i==3) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,0);
			} else if(i==4) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,1);
			} else if(i==5) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,2);
			} else if(i==6){
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,0);
			} else if(i==7) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,1);
			} else if(i==8) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,2);
			}
			} else if(boxNum == 7) {
				if(i == 0) {
					rowToCheck = createRow(boxes,6);
					columnToCheck = createColumn(boxes,3);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,4);
			} else if(i==2) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,5);
			} else if(i==3) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,3);
			} else if(i==4) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,4);
			} else if(i==5) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,5);
			} else if(i==6){
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,3);
			} else if(i==7) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,4);
			} else if(i==8) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,5);
			}
			} else {
				if(i == 0) {
					rowToCheck = createRow(boxes,6);
					columnToCheck = createColumn(boxes,6);
			} else if(i == 1) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,7);
			} else if(i==2) {
				rowToCheck = createRow(boxes,6);
				columnToCheck = createColumn(boxes,8);
			} else if(i==3) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,6);
			} else if(i==4) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,7);
			} else if(i==5) {
				rowToCheck = createRow(boxes,7);
				columnToCheck = createColumn(boxes,8);
			} else if(i==6){
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,6);
			} else if(i==7) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,7);
			} else if(i==8) {
				rowToCheck = createRow(boxes,8);
				columnToCheck = createColumn(boxes,8);
			}
			}
				
				//check if the row and column have the value missing in them
				ArrayList<Integer> missingInRow = findMissingValues(rowToCheck);
				ArrayList<Integer> missingInCol = findMissingValues(columnToCheck);
				// if the value being checked is also missing from both the column and row then it is a possible position
				if(missingInRow.contains(value) && missingInCol.contains(value)) {
					possiblePositions++;
					location = i;
				}
			
		}
	}
		if(possiblePositions == 1) {
			//if there is only one possible position then change the boxes array
			box[location] = value;	// add the new value into the correct location
			boxes[boxNum] = box;	//change the overall boxes array
			//change++;		//a change has been made
			//printRow(col);
			//printSolution();
		}
	}
	
	//OUTPUTS the solution to the grid on the GUI
		private void printSolution(Integer[][] solution) {
			int text_box_num = 0;
			//System.out.println("The full grid: ");
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					if(solution[i][j] != 0) {
						values.get(text_box_num).setText(solution[i][j].toString());
					}
					text_box_num++;
				}
			}
		}
	
		//checks that multiple of the same value has not been entered into a row
		private boolean checkRowsForRepeats(Integer[][] grid) {
			for(int i=0;i<9;i++) {
				Integer[] row = createRow(grid,i);
				for(int j=0;j<9;j++) {
					for(int k=j+1;k<9;k++) {
						if(row[j] == row[k] && row[j] != 0) {
							//System.out.println(" Repeat in row");
							return false;
						}
					}
				}
			}
			return true;
		}
		
		//checks that multiple of the same value has not been entered into a column
		//returns false if there is a repeat
				private boolean checkColumnsForRepeats(Integer[][] grid) {
					for(int i=0;i<9;i++) {
						Integer[] col = createColumn(grid,i);
						for(int j=0;j<9;j++) {
							for(int k=j+1;k<9;k++) {
								if(col[j] == col[k] && col[j] != 0) {
									//System.out.println(" Repeat in column");
									return false;
								}
							}
						}
					}
					return true;
				}
				
		//Checks that there are no repeat digits in a 3x3 grid		
		private boolean checkBoxForRepeats(Integer[][] grid) {
			for(int i=0;i<9;i++) {
				Integer[] box = grid[i];
				for(int j=0;j<9;j++) {
					for(int k=j+1;k<9;k++) {
						if(box[j] == box[k] && box[j] != 0) {
							//System.out.println(" Repeat in 3x3 grid");
							return false;
						}
					}
				}
		}
			return true;
		}
				
	//checks if all values have been added.			
	private boolean checkSolved(Integer[][] boxes) {
		if(!checkRowsForRepeats(boxes) || !checkColumnsForRepeats(boxes) || !checkBoxForRepeats(boxes)) {
			return false;
		} 
		for(int j=0;j<9;j++) {
			for(int k=0;k<9;k++) {
				if(boxes[j][k] == 0) {
					return false;		//there is still a zero in the boxes array, so full solution not found
				}
			}
		}
		return true;
	}
	
	//Used after addToGrid() has attempted to add as much as it can.
	// Find the 3x3 with the most missing values.
	// Find a permutation of missing numbers that fits the grid.
	// Then try addToGrid() again. Repeat for all permutations.
	private void guessSolve() {
		int largestMissing = 0;
		int missingIndex = 0;
		//search through all 3x3 grids and find the one with the largest number of missing values.
		for(int i=0; i<9;i++) {
			int numMissing = findMissingValues(boxes[i]).size();
			if(numMissing > largestMissing && numMissing < 9) {	//9 permutations is too computationally taxing
				largestMissing = numMissing;
				missingIndex = i;			//the index of the grid with the most missing values - first if two have equal numbers missing
			}
		}
		int numPermutations = factorial(largestMissing);
		//store a list of permutations, so that method runs until all permutations have been tried
		ArrayList<Integer[]> perm_list = new ArrayList<Integer[]>();	//list of all permutations tried so far
		
		while(perm_list.size() < numPermutations) {	//keep trying until all permutations are attempted
			guess_solution = createCopy(boxes);
			Integer[] guess_grid = createPermutation(guess_solution[missingIndex]);
			if(!arrayContains(perm_list,guess_grid)) {	//if the guessed permutation is not in the list of already attempted permutations then try it
				perm_list.add(guess_grid);
				guess_solution[missingIndex] = guess_grid;
				if(checkGrid(guess_solution)) {
					//System.out.println("Before adding guess");
					//printToConsole(guess_solution);
			
					addToGrid(guess_solution);
					
					//System.out.println("After adding guess");
					//printToConsole(guess_solution);
					if(checkSolved(guess_solution)) {
						printToOutput("Solved after: " + Integer.toString(perm_list.size()) + "/" + Integer.toString(numPermutations) + " permutations.");
						boxes = guess_solution;
						break;
						//printSolution(guess_solution);
				}
					printToOutput("Attempting permutation number: " + Integer.toString(perm_list.size()));
			}
			}
		}
		
		
	}
	
	//finds the factorial of an integer n, in order to find the number of permutations
	private int factorial(int n) {
		int factorial = 1;
		for(int i=n;i>0;i--) {
			factorial *= i;
		}
		return factorial;
	}
	
	
	
	
	private Integer[][] createCopy(Integer[][] boxes) {
		Integer[][] copy = new Integer[9][9];
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				copy[i][j] = boxes[i][j];
			}
		}
		return copy;
	}
	
	//creates a double permutation and checks if the grid can be solved
	private void doubleGuessSolve() {
		
	}
	
	private boolean checkForChange(Integer[][] start, Integer[][] end) {
		for(int i =0; i< 9; i++) {
			for(int j=0;j<9;j++) {
				if(start[i][j] != end[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	//check if a list of arrays contains a specific array
	private boolean arrayContains(ArrayList<Integer[]> list, Integer[] test_grid) {
		for(Integer[] item : list) {
			if(Arrays.equals(item, test_grid)) {
				return true;
			}
		}
		return false;
	}
	//INPUT: the full grid (boxes), missing numbers in the row, the row itself and row number
	//OUTPUT: add values to boxes if there is only one possible position
	//METHOD: For each position missing in the row, find out how many of the missing nums can go in that position...
	//...if only one, then add it
	private void checkSingleRowSpace(Integer[][] boxes, Integer[] row, ArrayList<Integer> missingNums, int rowNum) {
		for(int i=0;i<9;i++) {
			ArrayList<Integer> possibleNumbers = new ArrayList<Integer>();
			int location = 0;
			if(row[i] == 0) {
				Integer[] col = createColumn(boxes,i);	//create the column corresponding to the missing box in the row
				ArrayList<Integer> missingInCol = findMissingValues(col);
				for(Integer missing : missingInCol) {
					if(missingNums.contains(missing)) {
						possibleNumbers.add(missing);
						location = i;
						
					}
				}
				if(possibleNumbers.size() == 1) {
					row[location] = possibleNumbers.get(0);
					rowToGrid(boxes, row,rowNum,location);
				}
			}
		}
	}
	
	//SAME as for rows above, but for columns
	private void checkSingleColumnSpace(Integer[][] boxes, Integer[] col, ArrayList<Integer> missingNums, int colNum) {
		for(int i=0;i<9;i++) {
			ArrayList<Integer> possibleNumbers = new ArrayList<Integer>();
			int location = 0;
			if(col[i] == 0) {
				Integer[] row = createRow(boxes,i);	//create the column corresponding to the missing box in the row
				ArrayList<Integer> missingInRow = findMissingValues(row);
				for(Integer missing : missingInRow) {
					if(missingNums.contains(missing)) {
						possibleNumbers.add(missing);
						location = i;
						
					}
				}
				if(possibleNumbers.size() == 1) {
					col[location] = possibleNumbers.get(0);
					colToGrid(boxes, col,colNum,location);
				}
			}
		}
	}
	
	//Print a string to the output box on the homescreen.
	// Output reveals which method is being used to solve and how many iterations have been tried.
	private void printToOutput(String output) {
		outputText.setText(output);
	}
	
	
	
//TEST FUNCTIONS ==============================================================================	
	
	private void printToConsole(Integer[][] solution) {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(solution[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	private void printRow(Integer[] row) {
		System.out.println("The col: ");
		for(int i=0;i<row.length;i++) {
			System.out.print(row[i]);
		}
	}
		
	//compares the boxes array to the copy of it made before addRows and addColumns were run.
	//If comparison values equal boxes values then no change has been made and return false.
	private boolean checkForChange(Integer[][] comparison) {
		System.out.print("Comparison array:");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(comparison[i][j]);
			}
			System.out.print("\n");
		}
		
		System.out.print("boxes array:");
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(boxes[i][j]);
			}
			System.out.print("\n");
		}
		
		
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(boxes[i][j] != comparison[i][j]) {
					return true;	//a change has been made so return true
				}
			}
		}
		return false;	//if no changes have been made, return false 
		
	}
}
