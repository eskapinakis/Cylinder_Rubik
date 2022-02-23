package algorithms;

import cylinder.*;

public interface Algorithms {
	void format(Cylinder cylinder);
	void TopTop(int wrong,int right,int space,Cylinder cylinder);
	void BottomMiddle(int duplicate,int missing,int space,Cylinder cylinder);
	void BottomTop(int duplicate,int missing,int space,Cylinder cylinder);
	void MiddleTop(int wrong,int right,int space,Cylinder cylinder);
	void MiddleMiddle(int wrong,int right,int space,Cylinder cylinder);
}
