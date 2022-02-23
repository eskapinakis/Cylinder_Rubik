package cylinder;
import java.util.*;
import exceptions.*;

public class CylinderClass implements Cylinder{
	
	private static final int HEIGHT = 3;
	private static final int LENGTH = 6;
	
	private static final String R = "R";
	private static final String B = "B";
	private static final String G = "G";
	private static final String Y = "Y";
	private static final String W = "W";
	private static final String P = "P";
	
	private static final String[] PIECES = {R,G,B,P," ",Y,R,G,B,P,W,Y,R,G,B,P,W,Y};
	
	private String[][] cylinder;
	private String[] pieces;
	private List<String[]> moves;
	
	public CylinderClass() {
		moves = new ArrayList<String[]>();
		pieces = shuffleArray(PIECES);
		cylinder = newCylinder(pieces);	
	}
	
	/**
	 * adds the current cylinder to moves list
	 */
	private void saveMove() {
		int k=0;
		String[] move = new String[HEIGHT*LENGTH];
		for(int i=0;i<HEIGHT;i++)
		for(int j=0;j<LENGTH;j++)
			move[k++]=cylinder[i][j];
		moves.add(move);
	}
	
	public Iterator<String[]> moves(){
		return moves.iterator();
	}
	
	/**
	 * returns a shuffled array
	 * @param array
	 * @return shuffled
	 */
	private String[] shuffleArray(String[] array) {
	    Random rnd = new Random();
	    String color;
	    String[] shuffled = array;
	    for (int i = shuffled.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      color = shuffled[index];
	      shuffled[index] = shuffled[i];
	      shuffled[i] = color;
	    }
	    return shuffled;
	  }
	
	/**
	 * creates a new cylinder given a colors array
	 * @param colors
	 */
	private String[][] newCylinder(String[] colors) {
		String[][] cylinder = new String[HEIGHT][LENGTH];
		int k=0;
		for(int i=0;i<HEIGHT;i++)
		for(int j=0;j<LENGTH;j++)
			cylinder[i][j]=colors[k++];
		return cylinder;
	}
	
	
	/**
	 * switches the value of two entries 
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 */
	private void flip(int x, int y,int i,int j) {
		String color = cylinder[x][y];
		//new coordinates module 6 and 3
		int u = ((x+i)%3+3)%3;
		int v = ((y+j)%6+6)%6;
		
		cylinder[x][y]=cylinder[u][v];
		cylinder[u][v]=color;
		saveMove();
	}
	
	/**
	 * rotates a line 
	 * @param line
	 * @param step
	 */
	private void rotateLine(int line,int step) {
		String[] array = new String[6];
		for(int i=0;i<6;i++) 
			array[i]=cylinder[line][i];
		for(int i=0;i<6;i++)
			cylinder[line][i]=array[((i+step)%6+6)%6];
		saveMove();
	}

	/**
	 * moves a piece up
	 * @param x
	 * @param y
	 * @throws NotFreeException if space isnt free for the piece to be moved
	 */
	public void up(int x, int y) throws NotFreeException {
		if(!cylinder[x-1][y].equals(" "))
			throw new NotFreeException();
		flip(x,y,-1,0);	
		
	}

	/**
	 * moves a piece down
	 * @param x
	 * @param y
	 * @throws NotFreeException if space isnt free for the piece to be moved
	 */
	public void down(int x, int y) throws NotFreeException {
		if(!cylinder[x+1][y].equals(" "))
			throw new NotFreeException();
		flip(x,y,1,0);	
	}

	/**
	 * rotates a line to the left
	 * @param line
	 * @param step
	 */
	public void left(int line, int step){
		rotateLine(line,step);
	}

	/**
	 * rotates a line to the right
	 * @param line
	 * @param step
	 */
	public void right(int line, int step){
		rotateLine(line,-step);	
	}

	
	

	
	/**
	 * sets cylinder to its initial configuration 
	 */
	public void restart() {
		moves.clear();
		cylinder = newCylinder(this.pieces);
	}
	
	public int moveNumber() {
		return moves.size();
	}
	
	/**
	 * returns true if column is solved
	 * @param column
	 */
	private boolean isColumnSolved(int column) {
		return cylinder[0][column].equals(cylinder[1][column]) && 
				cylinder[1][column].equals(cylinder[2][column]);
	}
	
	/**
	 * checks if cylinder is solved by checking its column
	 * @return true if all lines are solved
	 */
	public boolean isSolved() {
		int sum=0;
		for(int i=0;i<LENGTH;i++)
			if(isColumnSolved(i))
				sum++;
		return sum==5;
	}
	
	/**
	 * returns an iterator to print the cylinder
	 */
	public Iterator<String> printCylinder(){
		List<String> cylinder = new ArrayList<>();
		for(int i=0;i<3;i++)
		for(int j=0;j<6;j++)
			cylinder.add(this.cylinder[i][j]);
		return cylinder.iterator();
	}
	
	/**
	 * moves all elements of a column up
	 */
	public void colUp(int column) throws NotFreeException{
		if(!colHasSpace(column) || cylinder[HEIGHT-1][column].equals(" "))
			throw new NotFreeException();
		if(cylinder[0][column].equals(" ")) {
			flip(1,column,-1,0);
			flip(2,column,-1,0);
		}
		else
			flip(2,column,-1,0);
		
	}
	
	/**
	 * moves all elements of a column down
	 */
	public void colDown(int column) throws NotFreeException{
		if(!colHasSpace(column) || cylinder[0][column].equals(" "))
			throw new NotFreeException();
		if(cylinder[HEIGHT-1][column].equals(" ")) {
			flip(1,column,1,0);
			flip(0,column,1,0);
		}
		else
			flip(0,column,1,0);
	}
	/**
	 * checks if a column has the space piece
	 * @param column
	 * @return
	 */
	private boolean colHasSpace(int column) {
		for(int i=0;i<HEIGHT;i++)
			if(cylinder[i][column].equals(" "))
				return true;
		return false;
	}
	
}
