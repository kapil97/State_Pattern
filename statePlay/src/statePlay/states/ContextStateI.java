package statePlay.states;

public interface ContextStateI {
    void categorizeItems(String items);
    void addToCategory(String category, String items);
    void printList();
}
