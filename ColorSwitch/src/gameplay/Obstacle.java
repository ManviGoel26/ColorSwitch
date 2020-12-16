package gameplay;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.Group;

public class Obstacle implements Serializable
{
	
	public ArrayList<ObstacleSprite> obsprites;	//on screen obstacles
	
	public Obstacle()
	{
		obsprites = new ArrayList<>();
		
		int x = 250;
		for(int i = 0; i < 10; i++) 
		{
			obsprites.add(new ObstacleSprite(i%3+1, x));
			x -= 400;
		}
	}
	
	public boolean detectCollision() 
	{
		for(ObstacleSprite ob: obsprites) {
			if(ob.checkIfCollided())
				return true;
		}
		return false;	
	}
	
	public void setTranslations(Group root) 
	{	
		for(int i = 0; i < 10; i++) 
		{
			if(i%3 == 0)
			{
				obsprites.get(i).setTranslation2(root,3);
			}
			
			else if(i%3 == 1) 
			{
				obsprites.get(i).setTranslation(root,3);
			}
			
			else
			{
				obsprites.get(i).setTranslation2(root,3);
			}
		}
		
		
	}
	public void update()
	{
		for(ObstacleSprite ob: obsprites) 
		{
			ob.moveDown();//sroot);
		}
	}
	
	private void writeObject(ObjectOutputStream output) throws IOException
	{
		output.defaultWriteObject();
		
//		System.out.println("wdrine");
		

		
	}
	


}

