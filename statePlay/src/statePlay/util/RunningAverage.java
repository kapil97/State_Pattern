package statePlay.util;
import java.lang.Math;
import java.util.ArrayList;

/**
 * Calculates running average
 */
public class RunningAverage implements RunningAverageI {
    String runAvgResultFile;
    String windowSize;
    int windowSizeInt;
    static double currentAverage;
    ArrayList<Double> currWindow=new ArrayList<>();

    /**
     * Constructor for RunningAverage
     * @param windowSizeIn
     * @param runningAvgFile
     */
    public RunningAverage(String windowSizeIn,String runningAvgFile){
       windowSize=windowSizeIn;
       runAvgResultFile =runningAvgFile;
    }

    /**
     * Overrides ObserverI interface
     * @param money
     */
    @Override
    public void update(double money)
    {
        if (currWindow.isEmpty())
        {
            currWindow.add(money);
        }
        else if(currWindow.size() < windowSizeInt)
        {
            currWindow.add(money);
        } else {
            currWindow.remove(0);
            currWindow.add(money);
        }

        windowSizeInt = Integer.parseInt(windowSize);
        currentAverage= calculateAverage(currWindow);

    }

    @Override
    public double getAverage() {
        return currentAverage;
    }


    double calculateAverage(ArrayList<Double> currWindowIn)
    {
        double sum=0;
        for(Double number : currWindowIn)
        {
            sum += number;
        }
        double avg=sum/currWindowIn.size();
        avg=Math.floor(avg * 100) / 100;
        return avg;
    }
    @Override
    public String toString(){
        String returnValue="Running average observer";
        return returnValue;
    }
}
