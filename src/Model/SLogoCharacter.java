package model;

/**
 * SLogo's highest Character in hierarchy, to support multiple types of
 * characters
 *
 */

public interface SLogoCharacter {

	public abstract void setState(SLogoCharacterState myState);

	public abstract SLogoCharacterState getState();

	public abstract String getName();

}