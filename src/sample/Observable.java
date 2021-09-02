package sample;
/**
 * Interface creating methods for adding, removing and updating weather conditions observers.
 * @author  Weronika Warwas
 */
public interface Observable {

    /**
     * Method to add new observer.
     * @param observer observer to add
     */
    void addObserver(Observer observer);

    /**
     * Method to remove observer.
     * @param observer observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Method to update observer.
     */

    void updateObserver();

}