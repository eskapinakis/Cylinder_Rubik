package algorithms;

import cylinder.*;
import exceptions.NotFreeException;

public class AlgorithmsClass implements Algorithms {

	private static final int LENGTH = 6;
	
	public AlgorithmsClass() {
		
	}
	
	/**
	 * puts the space piece in the top line
	 */
	public void format(Cylinder cylinder) {
		for(int i=0;i<LENGTH;i++) {
			try {
				cylinder.colDown(i);
			}
			catch(Exception e) {}
			
		}

	}

	/**
	 * replaces a wrong piece in the top layer with the right one
	 * @param wrong
	 * @param right
	 * @param space
	 * @param cylinder
	 */
	public void TopTop(int wrong,int right,int space,Cylinder cylinder) {
		cylinder.right(0,wrong-space);
		right=(right+(wrong-space))%6;
		try {
			cylinder.colUp(wrong-1);
		}
		catch(Exception e) {}
		cylinder.left(0,right-wrong);
		try {
			cylinder.colDown(wrong-1);
		}
		catch(Exception e) {}
		cylinder.right(0, right-wrong);
	}
	
	
	/**
	 * replaces a duplicate piece on the bottom line with a missing one on the middle line
	 * @param duplicate
	 * @param missing
	 * @param space
	 * @param cylinder
	 */
	public void BottomMiddle(int duplicate,int missing,int space,Cylinder cylinder) {	
		cylinder.right(0,duplicate-space); //moves the space piece above the duplicate
		if(missing==duplicate) {
			cylinder.right(1,1); //takes the missing piece out of the way
			missing = (missing+1)%6;
		}
		try {
			cylinder.colUp(duplicate-1); //moves the column with the duplicate up
		}
		catch(NotFreeException e) {System.out.println("(not free) that ain't gonna work");}
		cylinder.left(1,missing-duplicate);
		try {
			cylinder.colDown(duplicate-1); //moves the column with the missing piece down
		}
		catch(NotFreeException e) {System.out.println("(not free) that ain't gonna work");}
	}
	
	/**
	 * replaces a duplicate piece on the bottom line with a missing one on the middle line
	 * @param in
	 * @param cylinder
	 */
	public void BottomTop(int duplicate,int missing,int space,Cylinder cylinder) {
		try {
			cylinder.colUp(space-1);
		}
		catch(Exception e) {}
		cylinder.right(0,space-missing);
		try {
			cylinder.colDown(space-1);
		}
		catch(Exception e) {}
		BottomMiddle(duplicate,space,space,cylinder);
	}
	
	/**
	 * replaces a wrong on the middle line with a missing one on the top line
	 * @param wrong
	 * @param right
	 * @param space
	 * @param cylinder
	 */
	public void MiddleTop(int wrong,int right,int space,Cylinder cylinder) {
		cylinder.right(0,wrong-space);
		right += wrong-space;
		try {
			cylinder.colUp(wrong-1);
		}
		catch(Exception e) {System.out.println("didn't work");}
		cylinder.left(0,right-wrong);
		try {
			cylinder.colDown(wrong-1);
		}
		catch(Exception e) {System.out.println("didn't work");}
	}
	
	public void MiddleMiddle(int wrong,int right,int space,Cylinder cylinder) {
		cylinder.right(0,right-space);
		space=right;
		try {
			cylinder.colUp(right-1);
		}
		catch(Exception e) {System.out.println("middle middle nop");}
		cylinder.right(0,1);
		try {
			cylinder.colDown(right-1);
		}
		catch(Exception e) {System.out.println("middle middle no");}
		right=(right+1)%6;
		MiddleTop(wrong,right,space,cylinder);
	}

}
