package Model;

/**
 * SLogo's highest Character in hierarchy, to support multiple types of characters
 *
 */

public interface Character {

	public abstract void setState(CharacterState myState);
	public abstract CharacterState getState();
	public abstract String getName();

}