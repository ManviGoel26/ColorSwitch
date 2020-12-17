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
		
		int x=400;
		int noOfTypes=5;
		Random random=new Random();
		for(int i=0;i<20;i++) {
			Random r=new Random();
			int t=Math.abs(r.nextInt());
			//System.out.println(t%noOfTypes+1);
			obsprites.add(new ObstacleSprite(t%noOfTypes+1, startht));
			startht-=x;
		}
	}
	
	public boolean detectCollision() {
		
		for(ObstacleSprite ob: obsprites) {
			if(ob.checkIfCollided())
				return true;
		}
		return false;
	}
	
	public void setTranslations(Group root) {
		int nooftypes=5;
		for(int i=0;i<15;i++) {
			int shapetype=obsprites.get(i).Shape_number;
			
			if(shapetype==0)
				obsprites.get(i).setTranslation2(root,3);
			else if(shapetype==1) 
				obsprites.get(i).setTranslation(root,3);
			else if(shapetype==2)
				obsprites.get(i).setTranslation2(root,3);
			else if(shapetype==3)
				obsprites.get(i).setTranslation3(root,3);
			else if(shapetype==4)
				obsprites.get(i).setTranslation4(root,3);
			
		}
		
		
	}
	public void update() {//Group root) {
		for(ObstacleSprite ob: obsprites) {
			ob.moveDown();//sroot);
		}
	}
	private void writeObject(ObjectOutputStream output) throws IOException	
	{	
		output.defaultWriteObject();
		
	}
// 		public void renew(int startht) {
// 		obsprites=new ArrayList<>();
				
// 		int x=400;
// 		int noOfTypes=5;
// 		for(int i=0;i<15;i++) {
// 			obsprites.add(new ObstacleSprite(i%noOfTypes+1, startht));
// 			startht-=x;
// 		}
		
	}
