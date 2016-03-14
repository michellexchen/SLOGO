package model;

/**
 * SLogo's highest Character in hierarchy, to support multiple types of
 * characters
 *
 */

public interface SLogoCharacter {

    public void setState(SLogoCharacterState myState);

    public SLogoCharacterState getState();

}