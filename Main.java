import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.*;
import board.*;
import algorithms.*;
import cylinder.*;
import exceptions.*;

public class Main {
	
	//Basic commands
	private static final String QUIT = "QUIT";
	private static final String LEFT = "LEFT";
	private static final String RIGHT = "RIGHT";
	private static final String UP = "UP";
	private static final String DOWN = "DOWN";
	private static final String ISSOLVED = "ISSOLVED";
	private static final String RESTART = "RESTART";
	private static final String MOVENUMBER = "MOVENUMBER";
	private static final String COLUP = "COLUP";
	private static final String COLDOWN = "COLDOWN";
	
	//Hardcore stuff
	private static final String ALGORITHM = "ALGORITHM";
	private static final String BOTTOM = "BOTTOM";
	private static final String MIDDLE = "MIDDLE";
	private static final String TOP = "TOP";
	private static final String HOW = "HOW";
	
	public static void main(String[] args){
		Cylinder cylinder = new CylinderClass();
		Scanner in = new Scanner(System.in);
		String option=" ";
		while(!option.equals(QUIT)) {
			printCylinder(cylinder);
			option = getOption(in);
			menu(option,cylinder,in);
			clearScreen();
		}
		System.out.print("Hasta la Vista");
		in.close();
	}
	
	/**
	 * gets input to upper case from user
	 * @param in
	 * @return
	 */
	private static String getOption(Scanner in) {
		System.out.print("\n> ");
		return in.next().toUpperCase();
	}
	
	/**
	 * prints the board
	 */
	private static void printBoard() {
		GameBoard gb = new GameBoard();
        JFrame frame = new JFrame("Rubki Cylinder Board");
        JComponent board = gb.getGui();
        frame.add(board);
        frame.setLocationByPlatform(true);
        //frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(100, 100));
        board.setMinimumSize(new Dimension(500, 500));
        board.setPreferredSize(new Dimension(500,500));
        frame.setMinimumSize(new Dimension(500,500));
        frame.setLocation(50, 50);
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * calls other functions acording to user option
	 * @param option
	 * @param cylinder
	 * @param in
	 */
	private static void menu(String option,Cylinder cylinder,Scanner in) {
		switch(option) {
			case QUIT: break;
			case RIGHT: horizontalMovement(option, in, cylinder);break;
			case LEFT: horizontalMovement(option, in, cylinder);break;
			case UP: verticalMovement(option,in,cylinder);break;
			case DOWN: verticalMovement(option,in,cylinder);break;
			case ISSOLVED: break;
			case MOVENUMBER: moveNumber(cylinder);break;
			case RESTART: restart(in,cylinder);break;
			case COLUP: colMovement(option,in,cylinder);break;
			case COLDOWN: colMovement(option,in,cylinder); break;
			case ALGORITHM: algorithm(in,cylinder);break;
			case HOW:moves(cylinder);break;
			default: System.out.println("No idea what you talking bout");in.nextLine();break;
		}
	}
	
	/**
	 * prints the moves
	 * @param cylinder
	 */
	private static void moves(Cylinder cylinder) {
		Iterator<String[]> it = cylinder.moves();
		String[] move;
		int k=0,moveNumber=1;
		System.out.println("////////////");
		while(it.hasNext()) {
			System.out.println("move: "+moveNumber++);
			move = it.next();
			for(int j=0;j<3;j++) {
				for(int i=0;i<6;i++)
					System.out.print(move[k++]+" ");
				System.out.println("");
			}
			k=0;
		}
		System.out.println("////////////");
			
	}
	
	
	/**
	 * applies the correct algorithm from user's choice 
	 * @param in
	 * @param cylinder
	 */
	private static void algorithm(Scanner in,Cylinder cylinder) {
		Algorithms alg = new AlgorithmsClass();//class with the algorithms
		
		String toLine = in.next().toUpperCase();//line where the duplicate is
		String fromLine = in.next().toUpperCase();//line where the missing is
		
		int duplicate = in.nextInt(); //duplicate piece/wrong piece 
		int missing = in.nextInt(); //missing piece /right piece
		int space = in.nextInt();in.nextLine(); //space piece
		
		if(toLine.equals(BOTTOM) && fromLine.equals(MIDDLE))
			alg.BottomMiddle(duplicate, missing, space, cylinder);
		if(toLine.equals(BOTTOM) && fromLine.equals(TOP))
			alg.BottomTop(duplicate, missing, space, cylinder);
		if(toLine.equals(MIDDLE) && fromLine.equals(TOP))
			alg.MiddleTop(duplicate, missing, space, cylinder);
		if(toLine.equals(MIDDLE) && fromLine.equals(MIDDLE))
			alg.MiddleMiddle(duplicate, missing, space, cylinder);
		if(toLine.equals(TOP) && fromLine.equals(TOP))
			alg.TopTop(duplicate, missing, space, cylinder);
	}
	
	/**
	 * moves an entire column up or down
	 * @param option
	 * @param in
	 * @param cylinder
	 */
	private static void colMovement(String option,Scanner in,Cylinder cylinder) {
		int column = in.nextInt();in.nextLine();
		try {
			if(option.equals("COLUP"))
				cylinder.colUp(column-1);
			if(option.equals("COLDOWN"))
				cylinder.colDown(column-1);
		}
		catch(NotFreeException e) {System.out.println("Can't move that column daa...\n");}
		catch(Exception e) {System.out.println("The cylinder isn't that big \n");}
	}
	
	/**
	 * prints the cylinder
	 * @param cylinder
	 */
	private static void printCylinder(Cylinder cylinder) {
		System.out.println(" ");
		Iterator<String> it = cylinder.printCylinder(); 
		for(int j=0;j<3;j++) {
			for(int i=0;i<6;i++)
				System.out.print(it.next()+" ");
			System.out.println("");
		}		
	}
	
	/**
	 * rotates a line of the cylinder
	 * @param option
	 * @param in
	 * @param cylinder
	 */
	private static void horizontalMovement(String option, Scanner in, Cylinder cylinder) {
		int line = in.nextInt();
		int step = in.nextInt();in.nextLine();
		try {
			if(option.equals(LEFT))
				cylinder.left(line-1,step); //cause 0-indexed
			if(option.equals(RIGHT))
				cylinder.right(line-1,step); //cause 0-indexed
		}
		catch(Exception e) {System.out.println("why?? :(");}
	}
	
	/**
	 * moves a piece vertically
	 * @param option
	 * @param in
	 * @param cylinder
	 */
	private static void verticalMovement(String option, Scanner in, Cylinder cylinder) {
		int x = in.nextInt();
		int y = in.nextInt();in.nextLine();
		try {
			if(option.equals(DOWN))
				cylinder.down(x-1, y-1); //cause 0-indexed
			if(option.equals(UP))
				cylinder.up(x-1, y-1); //cause 0-indexed
		}
		catch(NotFreeException e) {System.out.println("Can't move that piece");}
		catch(Exception e) {System.out.println("You crazy?");}
	}
	
	/**
	 * prints the number of moves
	 * @param cylinder
	 */
	private static void moveNumber(Cylinder cylinder) {
		System.out.println("moves: "+cylinder.moveNumber());
		if(cylinder.isSolved())
			System.out.println("You got it :)");
		else
			System.out.println("\nRoses r red\nviolets r blue\nsomebody's gonna solve this\nbut"
					+ " it ain't gonna be you");
	}
	
	
	/**
	 * restarts the cylinder to its inittial state
	 * @param in
	 * @param cylinder
	 */
	private static void restart(Scanner in,Cylinder cylinder) {
		System.out.println("ARE YOU SURE?");
		String option = getOption(in);
		if(option.equals("YES")) {
			cylinder.restart();
			System.out.println("GO BANANAS\n");
		}
		else
			System.out.println(option+" is a better idea");
	}
	
	/**
	 * clears the screen
	 */
	private static void clearScreen() {
		//
	}

}
