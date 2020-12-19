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
	private int id;

	public SaveSlot(int s, Obstacle o, User u, int id) 
	{
		super();
		this.score = s;
		this.obs = o;
		this.user = u;
		this.id = id;
		
	}
	
	private void writeObject(ObjectOutputStream output) throws IOException
	{
		output.writeInt(score);
		output.writeInt(id);
		output.writeObject(this.obs);
		output.writeObject(this.user);
		}
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		score = input.readInt();
		id = input.readInt();
		obs = (Obstacle) input.readObject();
		user = (User) input.readObject();
//		return new SaveSlot(0, s, u, 0);
		
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
//		System.out.println(obs == null);
		return obs;
	}
	
	public User getUser() 
	{
		return user;
	}
}

