package gameplay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ObstacleSprite extends Pane implements Serializable
{
	
	public ArrayList<Shape> nodes;
	boolean onscreen;
	private int Shape_number; //To identify which constructor to call;
	private int constructor_number;//To identify the number in constructor;
	
	public ObstacleSprite(int i, int ht)
	{
		switch(i) 
		{
			case 1: 
				CircleSprite(ht);
				this.Shape_number = 0;
				break;
			
			case 2: 
				RectangleSprite(ht);
				this.Shape_number = 1;
				break;
			
			case 3: 
				longCircleSprite(ht);
				this.Shape_number = 3;
				break;	
		}
	}
	
	public void longCircleSprite(int ht)
	{
		this.constructor_number = ht;
		nodes = new ArrayList<>();
		int x = 0;
		for(int i = 0; i < 8; i++) 
		{
			Circle r1 = new Circle(x, ht, 30);
			
			if(i < 2)
			{
				r1.setFill(Color.RED);
			}
			
			else if(i < 4)
			{
				r1.setFill(Color.BLUE);
			}
			
			else if(i < 6)
			{
				r1.setFill(Color.GREEN);
			}
			
			else if(i < 8)
			{
				r1.setFill(Color.YELLOW);
			}
			
			x += 60;
			nodes.add(r1);
		}
		
		getChildren().addAll(nodes);
	    //return this;
	}
	
	public void CircleSprite(int ht)
	{
		this.constructor_number = ht;
		
		nodes = new ArrayList<>();
		
		Circle r = new Circle(40, ht, 30);
		r.setFill(Color.RED);
		
		Circle g = new Circle(100, ht, 30);
		g.setFill(Color.GREEN);
		
		Circle b = new Circle(160, ht, 30);
		b.setFill(Color.BLUE);
		
		Circle y = new Circle(220, ht, 30);
		y.setFill(Color.YELLOW);
		
	    nodes.add(r);
	    nodes.add(g);
	    nodes.add(b);
	    nodes.add(y);
	    getChildren().addAll(nodes);
	    //return this;
	}

	public ObstacleSprite RectangleSprite(int lowesty )
	{
		this.constructor_number = lowesty;
		
		nodes = new ArrayList<>();
			
		int leftx = 50, rightx = 500,  barwidth = 150, barheight = 30;
			
		Rectangle r = new Rectangle(barwidth, barheight);
			
		r.setX(leftx);
		r.setY(lowesty-150);
		r.setArcWidth(30);
		r.setArcHeight(30);
		r.setFill(Color.RED);
			
		Rectangle g = new Rectangle(barwidth, barheight);
			
		g.setX(rightx);
		g.setY(lowesty-100);
		g.setArcWidth(30);
		g.setArcHeight(30);
		g.setFill(Color.GREEN);
			
		Rectangle b = new Rectangle(barwidth, barheight);
			
		b.setX(leftx);
		b.setY(lowesty-50);
		b.setArcWidth(30);
		b.setArcHeight(30);
		b.setFill(Color.BLUE);
			
		Rectangle y = new Rectangle(barwidth, barheight);
			
		y.setX(rightx);
		y.setY(lowesty);
		y.setArcWidth(30);
		y.setArcHeight(30);
		y.setFill(Color.YELLOW);
		
		nodes.add(r);
		nodes.add(b);
		nodes.add(g);
		nodes.add(y);
		
		getChildren().addAll(nodes);
		
		return this;
		
	}
	
	public boolean checkIfCollided() 
	{
		//System.out.println("checked");
		boolean collisionDetected = false;
		for (Shape static_bloc : nodes)
		{
			Shape intersect = Shape.intersect(User.rectangle, static_bloc);
			if (intersect.getBoundsInLocal().getWidth() != -1 && User.rectangle.getFill()!=static_bloc.getFill())
			{
				collisionDetected = true;
			}
		}
		
		return collisionDetected;
	}
	
	
	public void setTranslation(Group root, int speed) 
	{
		for(int i = 0; i < 2; i++)
		{
			TranslateTransition transition = new TranslateTransition();
	    	transition.setDuration(Duration.seconds(speed));
	    	transition.setToX(500);
	    	transition.setAutoReverse(true);
	    	transition.setCycleCount(Animation.INDEFINITE);
	    	transition.setNode(nodes.get(i));
	    	transition.play();
	    }
		
		for(int i = 2; i < 4; i++) 
		{
			TranslateTransition transition = new TranslateTransition();
	    	transition.setDuration(Duration.seconds(speed));
	    	transition.setToX(-450);
	    	transition.setAutoReverse(true);
	    	transition.setCycleCount(Animation.INDEFINITE);
	    	transition.setNode(nodes.get(i));
	    	transition.play();
	    }
		
		root.getChildren().addAll(nodes);
	}
	
	public void setTranslation2(Group root, int speed) 
	{	
		for(Shape i: nodes) 
		{
			TranslateTransition transition=new TranslateTransition();
	    	transition.setDuration(Duration.seconds(3));
	    	transition.setToX(500);
	    	transition.setAutoReverse(true);
	    	transition.setCycleCount(Animation.INDEFINITE);
	    	transition.setNode(i);
	    	transition.play();
	    }
		
		root.getChildren().addAll(nodes);
	}
	
	public void moveDown()
	{
		for(Shape i: nodes) 
		{
		
			TranslateTransition transition=new TranslateTransition();
	    	transition.setDuration(Duration.seconds(0.001));
	    	transition.setToY(i.getLayoutY()+1);
	    	i.setLayoutY(i.getLayoutY()+1);
	    	transition.setNode(i);
	    	transition.play();
	    }
		//root.getChildren().addAll(nodes);
	}
	
	public void RotatingSprite()
	{
	
		nodes = new ArrayList<>();
				
		int wid = 20;
		int ang = 70;
		
		Arc arcr = new Arc(100, 100, 50, 50, 0, ang);
		arcr.setFill(Color.TRANSPARENT);
		arcr.setStroke(Color.RED);
		arcr.setStrokeWidth(wid);
    
		Arc arcy = new Arc(100, 100, 50, 50, 90, ang);
		arcy.setFill(Color.TRANSPARENT);
		arcy.setStroke(Color.YELLOW);
		arcy.setStrokeWidth(wid);
    
		Arc arcg = new Arc(100, 100, 50, 50, 180, ang);
		arcg.setFill(Color.TRANSPARENT);
		arcg.setStroke(Color.BLUE);
		arcg.setStrokeWidth(wid);
    
		Arc arcb = new Arc(100, 100, 50, 50, 270, ang);
		arcb.setFill(Color.TRANSPARENT);
		arcb.setStroke(Color.GREEN);
		arcb.setStrokeWidth(wid);
	
	
		nodes.add(arcr);
		nodes.add(arcb);
		nodes.add(arcg);
		nodes.add(arcy);
    
		//return this;
	}
	

//	public void RotatingSprite()
//	{
//	
//	nodes = new ArrayList<>();
//				
//	int wid=20;
//	int ang=70;
//	Arc arcr = new Arc(100, 100, 50, 50, 0, ang);
//	arcr.setFill(Color.TRANSPARENT);
//	arcr.setStroke(Color.RED);
//	arcr.setStrokeWidth(wid);
//    
//    Arc arcy = new Arc(100, 100, 50, 50, 90, ang);
//    arcy.setFill(Color.TRANSPARENT);
//    arcy.setStroke(Color.YELLOW);
//    arcy.setStrokeWidth(wid);
//    
//    Arc arcg = new Arc(100, 100, 50, 50, 180, ang);
//	arcg.setFill(Color.TRANSPARENT);
//    arcg.setStroke(Color.BLUE);
//    arcg.setStrokeWidth(wid);
//    
//    Arc arcb = new Arc(100, 100, 50, 50, 270, ang);
//	arcb.setFill(Color.TRANSPARENT);
//    arcb.setStroke(Color.GREEN);
//    arcb.setStrokeWidth(wid);
//	
//	
//    nodes.add(arcr);
//    nodes.add(arcb);
//    nodes.add(arcg);
//    nodes.add(arcy);
//    
//    //return this;
//}	
	
	
	private void writeObject(ObjectOutputStream output) throws IOException
	{
		output.writeBoolean(this.onscreen);
		output.writeInt(this.Shape_number);
		output.writeInt(this.constructor_number);
	}
	
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		boolean new_onscreen = input.readBoolean();
		int shape_number = input.readInt();
		int cons_number = input.readInt();
		
		ObstacleSprite newObstacleSprite = new ObstacleSprite(shape_number, cons_number);
	}
	

	

	
	
	
}

