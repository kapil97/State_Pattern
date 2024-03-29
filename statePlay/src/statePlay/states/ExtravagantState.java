package statePlay.states;

import statePlay.util.RunningAverage;
import statePlay.util.RunningAverageI;

import java.util.List;
import java.util.Map;

public class ExtravagantState implements BudgetStateI {
    Map<String, List<String>> dataList;
    public ExtravagantState(){}
    public ExtravagantState(Map<String, List<String>> dataListIn){
        dataList=dataListIn;
    }
    /**
     * Purchase Action on the current item
     * @param item
     * @return currentState (Constructor of Context State);
     */
    @Override
    public BudgetStateI purchaseActionPerformed(String item) {
        RunningAverageI runningAverage=new RunningAverage();
        boolean isPurchased;
        BudgetStateI nextState=null;
        double currentMoney=runningAverage.getAverage();
        isPurchased=checkPurchasable(item,currentMoney);
        if(10000>currentMoney){
            nextState=new BasicState();
        }
        else if(currentMoney>=10000&&currentMoney<50000){
            nextState=new LuxuriousState();
        }
        else
            nextState=new ExtravagantState();

        return new ContextState(nextState,isPurchased);
    }
    /**
     * Private method to check if item is purchasable
     * @param item
     * @param money
     * @return boolean of if purchasable
     */
    private boolean checkPurchasable(String item,double money){
        String category=getCategory(money);
        boolean found=false;
        if(category.equals("superExpensive")){
            found=true;
        }
        else {
            if(category.equals("basic")){
                for (Map.Entry<String, List<String>> entry : dataList.entrySet()) {
                    if (entry.getKey().equals("basic")) {
                        found = entry.getValue().contains(item);
                    }
                }
            }
            else {
                for (Map.Entry<String, List<String>> entry : dataList.entrySet()) {
                    if (entry.getKey().equals("moderatelyExpensive")) {
                        found = entry.getValue().contains(item);
                    }
                    if (entry.getKey().equals("basic")&&!found) {
                        found = entry.getValue().contains(item);
                    }
                }
            }
        }
        System.out.println("item: " + item + " Category: " + category + " Purchasable: " + found);
        return found;
    }
    /**
     * Private method to access the category of the item
     * @param money
     * @return String: Category
     */
    private String getCategory(double money){
        if(10000>money){
            return "basic";
        }
        else if(money>=10000&&money<50000){
            return "moderatelyExpensive";
        }
        else
            return "superExpensive";
    }
    @Override
    public String toString(){
        return "ExtravagantState";

    }
}
