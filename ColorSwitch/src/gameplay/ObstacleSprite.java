package gameplay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//import com.sun.prism.paint.Paint;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
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
	public int Shape_number; //To identify which constructor to call;
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
				this.Shape_number = 2;
				break;	
			case 4:
				RotatingSprite(ht);
				this.Shape_number = 3;
				break;
			case 5:
				DoubleRotatingSprite(ht);
				this.Shape_number = 4;
				break;
			
		}
	}
	
	public void DoubleRotatingSprite(int ht) {
		this.constructor_number =ht;
		nodes = new ArrayList<>();
		
		int wid = 20, x=300, radius=120;
		int ang = 70;
		
		Arc arcr = new Arc(x-radius, ht, radius,radius, 0, ang);
		arcr.setFill(Color.TRANSPARENT);
		arcr.setStroke(Color.RED);
		arcr.setStrokeWidth(wid);
    
		Arc arcy = new Arc(x-radius, ht, radius,radius, 90, ang);
		arcy.setFill(Color.TRANSPARENT);
		arcy.setStroke(Color.YELLOW);
		arcy.setStrokeWidth(wid);
    
		Arc arcg = new Arc(x-radius, ht, radius,radius, 180, ang);
		arcg.setFill(Color.TRANSPARENT);
		arcg.setStroke(Color.BLUE);
		arcg.setStrokeWidth(wid);
    
		Arc arcb = new Arc(x-radius, ht, radius,radius, 270, ang);
		arcb.setFill(Color.TRANSPARENT);
		arcb.setStroke(Color.GREEN);
		arcb.setStrokeWidth(wid);
		
		nodes.add(arcr);
		nodes.add(arcb);
		nodes.add(arcg);
		nodes.add(arcy);
		
		Arc arcr2 = new Arc(x+radius, ht, radius,radius, 110, ang);
		arcr2.setFill(Color.TRANSPARENT);
		arcr2.setStroke(Color.RED);
		arcr2.setStrokeWidth(wid);
    
		Arc arcy2 = new Arc(x+radius, ht, radius,radius, 20, ang);
		arcy2.setFill(Color.TRANSPARENT);
		arcy2.setStroke(Color.YELLOW);
		arcy2.setStrokeWidth(wid);
    
		Arc arcg2 = new Arc(x+radius, ht, radius,radius, 290, ang);
		arcg2.setFill(Color.TRANSPARENT);
		arcg2.setStroke(Color.BLUE);
		arcg2.setStrokeWidth(wid);
    
		Arc arcb2 = new Arc(x+radius, ht, radius,radius, 200, ang);
		arcb2.setFill(Color.TRANSPARENT);
		arcb2.setStroke(Color.GREEN);
		arcb2.setStrokeWidth(wid);
		
		nodes.add(arcr2);
		nodes.add(arcb2);
		nodes.add(arcg2);
		nodes.add(arcy2);
		
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
				r1.setStroke(Color.RED);
			}
			
			else if(i < 4)
			{
				r1.setFill(Color.BLUE);
				r1.setStroke(Color.BLUE);
			}
			
			else if(i < 6)
			{
				r1.setFill(Color.GREEN);
				r1.setStroke(Color.GREEN);
			}
			
			else if(i < 8)
			{
				r1.setFill(Color.YELLOW);
				r1.setStroke(Color.YELLOW);
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
		r.setStroke(Color.RED);
		
		Circle g = new Circle(100, ht, 30);
		g.setFill(Color.GREEN);
		g.setStroke(Color.GREEN);
		
		Circle b = new Circle(160, ht, 30);
		b.setFill(Color.BLUE);
		b.setStroke(Color.BLUE);
		
		Circle y = new Circle(220, ht, 30);
		y.setFill(Color.YELLOW);
		y.setStroke(Color.YELLOW);
		
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
		r.setStroke(Color.RED);
			
		Rectangle g = new Rectangle(barwidth, barheight);
			
		g.setX(rightx);
		g.setY(lowesty-100);
		g.setArcWidth(30);
		g.setArcHeight(30);
		g.setFill(Color.GREEN);
		g.setStroke(Color.GREEN);
		
		Rectangle b = new Rectangle(barwidth, barheight);
			
		b.setX(leftx);
		b.setY(lowesty-50);
		b.setArcWidth(30);
		b.setArcHeight(30);
		b.setFill(Color.BLUE);
		b.setStroke(Color.BLUE);
		
		Rectangle y = new Rectangle(barwidth, barheight);
			
		y.setX(rightx);
		y.setY(lowesty);
		y.setArcWidth(30);
		y.setArcHeight(30);
		y.setFill(Color.YELLOW);
		y.setStroke(Color.YELLOW);
		
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
			
			if (intersect.getBoundsInLocal().getWidth() != -1 && User.rectangle.getFill()!=static_bloc.getStroke())
			{
				//&& static_bloc.getFill()!=Color.BLACK 
				//System.out.println(static_bloc.getStroke());
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
	
	public void setTranslation3(Group root, int speed) 
	{	
		Group g=new Group();
		for(Shape i: nodes) 
		{
			g.getChildren().add(i);
	    }
	
		RotateTransition transition=new RotateTransition();
    	transition.setDuration(Duration.seconds(15));
    	transition.setByAngle(720);
    	transition.setCycleCount(Animation.INDEFINITE);
    	transition.setNode(g);
    	transition.play();
    	root.getChildren().addAll(g);
    	
    	
	}
	public void setTranslation4(Group root, int speed) 
	{	
		Group g=new Group();
		for(int i=0;i<4;i++)
		{
			g.getChildren().add(nodes.get(i));
	    }
	
		RotateTransition transition=new RotateTransition();
    	transition.setDuration(Duration.seconds(15));
    	transition.setByAngle(720);
    	transition.setCycleCount(Animation.INDEFINITE);
    	transition.setNode(g);
    	transition.play();
    	
    	Group g2=new Group();
		for(int i=4;i<8;i++)
		{
			g2.getChildren().add(nodes.get(i));
	    }
	
		RotateTransition transition2=new RotateTransition();
    	transition2.setDuration(Duration.seconds(15));
    	transition2.setByAngle(-720);
    	transition2.setCycleCount(Animation.INDEFINITE);
    	transition2.setNode(g2);
    	transition2.play();
    	
    	root.getChildren().addAll(g);
    	root.getChildren().addAll(g2);
    	
    	
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
	
	public void RotatingSprite(int ht)
	{
		this.constructor_number = ht;
	
		nodes = new ArrayList<>();
				
		int wid = 20, x=300, radius=120;
		int ang = 70;
		
		Arc arcr = new Arc(x, ht, radius,radius, 0, ang);
		arcr.setFill(Color.TRANSPARENT);
		arcr.setStroke(Color.RED);
		arcr.setStrokeWidth(wid);
    
		Arc arcy = new Arc(x, ht, radius,radius, 90, ang);
		arcy.setFill(Color.TRANSPARENT);
		arcy.setStroke(Color.YELLOW);
		arcy.setStrokeWidth(wid);
    
		Arc arcg = new Arc(x, ht, radius,radius, 180, ang);
		arcg.setFill(Color.TRANSPARENT);
		arcg.setStroke(Color.BLUE);
		arcg.setStrokeWidth(wid);
    
		Arc arcb = new Arc(x, ht, radius,radius, 270, ang);
		arcb.setFill(Color.TRANSPARENT);
		arcb.setStroke(Color.GREEN);
		arcb.setStrokeWidth(wid);
	
	
		nodes.add(arcr);
		nodes.add(arcb);
		nodes.add(arcg);
		nodes.add(arcy);
    
		//return this;
	}
	


	private void writeObject(ObjectOutputStream output) throws IOException
	{
		output.writeBoolean(this.onscreen);
		output.writeInt(this.Shape_number);
		output.writeInt(this.constructor_number);
	}
	
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		onscreen = input.readBoolean();
		Shape_number = input.readInt();
		constructor_number = input.readInt();
		nodes = new ArrayList<>();
		switch(Shape_number) 
		{
			case 0: 
				CircleSprite(constructor_number);
//				Shape_number = 0;
				break;
			
			case 1: 
				RectangleSprite(constructor_number);
//				Shape_number = 1;
				break;
			
			case 2: 
				longCircleSprite(constructor_number);
//				Shape_number = 2;
				break;	
			case 3:
				RotatingSprite(constructor_number);
//				Shape_number = 3;
				break;
			case 4:
				DoubleRotatingSprite(constructor_number);
//				Shape_number = 4;
				break;
		}
		
	}
	
}
//class complexshape extends Shape{
//	Rectangle rectangle;
//	public complexshape() {
//		rectangle=new Rectangle(300, 400);
//	}
//}
//class bloop extends Group
////{
//	public Paint getFill() {
//		return null;//Color.BLACK;
//	}
//}
