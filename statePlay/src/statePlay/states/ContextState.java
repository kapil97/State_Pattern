package statePlay.states;

import statePlay.util.RunningAverage;
import statePlay.util.RunningAverageI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextState{

    BudgetStateI currentState;
    boolean isPurchased;
    public ContextState(){}
    public ContextState(BudgetStateI currentStateIn, boolean isPurchasedIn){
        currentState=currentStateIn;
        isPurchased=isPurchasedIn;
    }
    static Map<String, List<String>> categorizedItems=new HashMap<>();
    public void categorizeItems(String line){

        int index=line.indexOf(':');
        String item=line.substring(index+1);
        if(5==index){
            System.out.println("basic: "+ item);
            addToCategory("basic",item);
        }
        else if(19==index){
            System.out.println("Moderately Expensive: = "+ item);
            addToCategory("moderatelyExpensive",item);
        }
        else if(14==index){
            System.out.println("Super Expensive= "+item);
            addToCategory("superExpensive",item);
        }
        else{
            System.out.println("Item category must be either basic, moderately expensive or super expensive only");
        }
    }
    public BudgetStateI purchaseActionPerformed(String line){
        System.out.println("Line: "+line);
        RunningAverageI runningAverage=new RunningAverage();
        double current_value=runningAverage.getAverage();
        System.out.println("Running Average: "+current_value);
        return null;
    }
    public void addToCategory(String category,String item) {
        if (!categorizedItems.containsKey(category)) {
            categorizedItems.put(category, new ArrayList<>());
        }
        categorizedItems.get(category).add(item);
    }
    public void printList(){
        System.out.println(categorizedItems);
    }
}
