package gameplay;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveSlot implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int score;
	private Obstacle obs;
	private User user;

	public SaveSlot(int s, Obstacle o, User u) 
	{
		super();
		this.score = s;
		this.obs = o;
		this.user = u;
		
	}
	
	private void writeObject(ObjectOutputStream output) throws IOException
	{
		output.writeObject(this.obs);
		output.writeObject(this.user);
		output.writeInt(score);
	}
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		
		Obstacle s = (Obstacle) input.readObject();
		User u = (User) input.readObject();
//		int score = (int) input.readObject();
//		input.close();
//		System.out.println(u.get+"wicbud");
		
	}
	
	
	
	public int getScore() 
	{
		return score;
	}
	
	public Obstacle getObstacle() 
	{
		System.out.println(obs == null);
		return obs;
	}
	
	public User getUser() 
	{
		return user;
	}
}

