package Model;

public interface Character {

	public abstract Character step(CharacterState myState);

	public abstract CharacterState getState();

	public abstract void move();

	public abstract void rotate();

	public abstract void penDown(); //Do we need both of these?

	public abstract void penUp();//^^^

	public abstract String getName();

}