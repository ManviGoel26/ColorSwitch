package gameplay;

import java.util.ArrayList;

import javafx.scene.Group;

public class Obstacle{
	
	//public ArrayList<Shape> nodes;
	public ArrayList<ObstacleSprite> obsprites;	//on screen obstacles
	
	public Obstacle(){
		
		obsprites=new ArrayList<>();
		obsprites.add(new ObstacleSprite());
		//getChildren().add(rectangle);
	}
	
	public boolean detectCollision() {
		
		for(ObstacleSprite ob: obsprites) {
			if(ob.checkIfCollided())
				return true;
		}
		return false;
		
	}
	
	public void setTranslations(Group root) {
		
		for(ObstacleSprite ob: obsprites) {
			ob.setTranslation(root);
		}
		
	}
}
