package Model;

public interface Character {
	
	public abstract Character step(CharacterState myState);
	public CharacterState getState();
	
}