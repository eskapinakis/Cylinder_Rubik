package cylinder;
import exceptions.*;
import java.util.*;

public interface Cylinder {

	//functional comands for user resolution
	void up(int x,int y) throws NotFreeException;
	void down(int x,int y) throws NotFreeException;
	void colUp(int column) throws NotFreeException;
	void colDown(int column) throws NotFreeException;
	void left(int line,int step);
	void right(int line,int step);
	void restart();
	int moveNumber();
	Iterator<String[]> moves();
	boolean isSolved();
	//boolean colHasSpace(int column);
	Iterator<String> printCylinder();

}
