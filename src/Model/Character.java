package Model;

public interface Character {

	public abstract void setState(CharacterState myState);
	public abstract CharacterState getState();
	public abstract String getName();

}