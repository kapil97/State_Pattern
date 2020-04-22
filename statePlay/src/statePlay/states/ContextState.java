package statePlay.states;
import statePlay.util.ResultProcessor;
import statePlay.util.ResultProcessorI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextState implements BudgetStateI, ContextStateI{

    BudgetStateI currentState;
    boolean isPurchased;
    ResultProcessorI resultProcessor=new ResultProcessor();

    public ContextState(){
    currentState=new BasicState();
    }

    public ContextState(BudgetStateI currentStateIn, boolean isPurchasedIn){
        currentState=currentStateIn;
        isPurchased=isPurchasedIn;
    }
    static Map<String, List<String>> categorizedItems=new HashMap<>();

    /**
     * Method to Categorize items
     * @param line
     */
    @Override
    public void categorizeItems(String line){

        int index=line.indexOf(':');
        String item=line.substring(index+1);
        if(5==index){
            System.out.println("basic Category: "+ item);
            addToCategory("basic",item);
        }
        else if(19==index){
            System.out.println("Moderately Expensive Category: = "+ item);
            addToCategory("moderatelyExpensive",item);
        }
        else if(14==index){
            System.out.println("Super Expensive Category: = "+item);
            addToCategory("superExpensive",item);
        }
        else{
            System.out.println("Item category must be either basic, moderately expensive or super expensive only");
        }
    }

    /**
     * Method to Perform purchase action on current state
     * @param line
     * @return BudgetStateInterface
     */
    @Override
    public BudgetStateI purchaseActionPerformed(String line){
        int index=line.indexOf(':');
        String item=line.substring(index+1);
        System.out.println();
//        System.out.println("item: "+item);
        initialize();
        ContextState innerState;
        innerState= (ContextState) currentState.purchaseActionPerformed(item);
        currentState=innerState.currentState;
        isPurchased=innerState.isPurchased;
        String ifPurchasable;
        if(isPurchased) ifPurchasable = "YES";
        else ifPurchasable = "NO";
//        System.out.println("InnerState CurrentState: "+ currentState+"InnerState Boolean Value: "+isPurchased);
        String className=getClassName(currentState);
        String result=className+"::"+item+"--"+ifPurchasable;
        resultProcessor.addToResultList(result);
        return null;
    }

    /**
     * Private method to initialize states
     */
    private void initialize(){
        new BasicState(categorizedItems);
        new LuxuriousState(categorizedItems);
        new ExtravagantState(categorizedItems);
    }

    /**
     * Private method to get the class name in string
     * @param currentState
     * @return class name
     */
    private String getClassName(BudgetStateI currentState){
        if(currentState.toString().contains("BasicState"))
            return "BASIC";
        else if(currentState.toString().contains("LuxuriousState"))
            return "LUXURIOUS";
        else return "EXTRAVAGANT";
    }

    /**
     * method to add items to category
     * @param category
     * @param item
     */
    private void addToCategory(String category,String item) {
        if (!categorizedItems.containsKey(category)) {
            categorizedItems.put(category, new ArrayList<>());
        }
        categorizedItems.get(category).add(item);
    }

    @Override
    public void printList(){
        System.out.println(categorizedItems);
    }
}
