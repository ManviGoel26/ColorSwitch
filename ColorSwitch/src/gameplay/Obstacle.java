package gameplay;

import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;	
import java.io.ObjectOutputStream;	
import java.io.Serializable;
import javafx.scene.Group;

public class Obstacle implements Serializable{
	public ArrayList<ObstacleSprite> obsprites;	//on screen obstacles
	
	public Obstacle(int startht){
		
		obsprites=new ArrayList<>();
		
		int x = 400;
		int noOfTypes = 5;
		Random random = new Random();
		for(int i=0;i<20;i++) {
			Random r = new Random();
			int t = Math.abs(r.nextInt());
			//System.out.println(t%noOfTypes+1);
			obsprites.add(new ObstacleSprite(t%noOfTypes+1, startht));
			startht-=x;
		}
	}
	
	public boolean detectCollision(User user) {
		
		for(ObstacleSprite ob: obsprites) {
			if(ob.checkIfCollided(user))
			{
//				System.out.println("happen");
				return true;
			}
		}
		return false;
	}
	
	public void setTranslations(Group root) {
		int nooftypes=5;
		for(int i=0;i<20;i++) {
			int sp;
			if(i<10) sp=3;
			else if(i<15) sp=2;
			else sp=1;
			
				int shapetype=obsprites.get(i).Shape_number;
				
				if(shapetype==0)
					obsprites.get(i).setTranslation2(root,sp);
				else if(shapetype==1) 
					obsprites.get(i).setTranslation(root,sp);
				else if(shapetype==2)
					obsprites.get(i).setTranslation2(root,sp);
				else if(shapetype==3)
					obsprites.get(i).setTranslation3(root,sp);
				else if(shapetype==4)
					obsprites.get(i).setTranslation4(root,sp);
			
			
			
				int shapetype = obsprites.get(i).Shape_number;
				
				if(shapetype == 0)
				{
					obsprites.get(i).setTranslation2(root,sp);
				}
				
				else if(shapetype == 1) 
				{
					obsprites.get(i).setTranslation(root,sp);
				}
				
				else if(shapetype == 2)
				{
					obsprites.get(i).setTranslation2(root,sp);
				}
				else if(shapetype == 3)
				{
					obsprites.get(i).setTranslation3(root,sp);
				}
				
				else if(shapetype == 4)
				{
					obsprites.get(i).setTranslation4(root,sp);
				}
		}
		
		
	}
	public void update() 
	{
		for(ObstacleSprite ob: obsprites) 
		{
			ob.moveDown();
		}
	}
	
	
	private void writeObject(ObjectOutputStream output) throws IOException	
	{	
		output.defaultWriteObject();
		
	}
		
	}
