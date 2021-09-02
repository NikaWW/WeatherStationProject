package sample;

/**
 *Interface creating methods for adding, removing and updating time observers.
 * @author  Weronika Warwas
 */
public interface ObservableTime {

    /**
     * Method to add new observer.
     * @param observer observer to add
     */
    void addObserverTime(ObserverTime observer);

    /**
     * Method to remove observer.
     * @param observer observer to remove
     */
    void removeObserver(ObserverTime observer);

    /**
     * Method to update observer.
     */
    void updateObserverT();
}
