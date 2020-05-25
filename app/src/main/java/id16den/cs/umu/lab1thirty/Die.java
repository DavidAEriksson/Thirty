package id16den.cs.umu.lab1thirty;

/**
 * @author David Eriksson, ID16
 */
public class Die {

    private int value;
    private boolean active;

    /**
     * Method: Die
     * Constructor for the Die class
     * @param value: value of a single die
     * @param active: active state for a single die
     */
    public Die(int value, boolean active) {
        this.value = value;
        this.active = active;
    }

    /**
     * Method: setValue
     * Sets the value of a single die
     * @param value: value to set to a die
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Method: checkActive
     * Returns whether a die is active or not.
     * @return: active = true?false
     */
    public boolean checkActive() {
        return active;
    }

    /**
     * Method: setActive
     * Set a single die to active.
     * @return active = true;
     */
    public boolean setActive() {
        return active = true;
    }

    /**
     * Method: setActive
     * Set a single die to inactive.
     * @return active = false;
     */
    public boolean setInactive() {
        return active = false;
    }

    /**
     * Method: getValue
     * Returns the value of a single die.
     * @return value: the value of a single die.
     */
    public int getValue() {
        return value;
    }

}
