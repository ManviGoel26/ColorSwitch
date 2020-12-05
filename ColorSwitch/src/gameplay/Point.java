package gameplay;

class Point 
{
	private int x;
	private int y;
	
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
//	Getter functions
	int getXCoordinate()
	{
		return this.x;
	}
	
	int getYCoordinate()
	{
		return this.y;
	}
	
//	Setter functions
	void setXCoordinate(int new_x)
	{
//		Add conditions for error checking; length is not longer than panel
		this.x = new_x;
	}
	
	void setYCoordinate(int new_y)
	{
//		Add conditions for error checking; length is not longer than panel
		this.y = new_y;
	}
	
	
	

}
