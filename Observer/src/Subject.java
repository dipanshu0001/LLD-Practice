public interface Subject<T> {
    void AddObserver(Observer<T> observer);
    void removeObserver(Observer<T> observer);
    void notifyObserver(T data);
}
