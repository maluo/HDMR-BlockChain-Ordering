package ec.CALCULATION;

import java.text.NumberFormat;
import java.util.List;

public class ContinousKnapsackWrapper {
    final private double tolerance = 0.0005;
    private StringBuilder buf = new StringBuilder();
    
    public ContinousKnapsackWrapper() {}
    
    public String getBuf() {
    	return buf.toString();
    }
    
    public ContinousKnapsackWrapper(int weight,List<ItemUnit> list) {
        ContinuousKnapsack cok = new ContinuousKnapsack(weight); // 15 kg
        
        for(ItemUnit obj : list) {
        	cok.add(obj.name, obj.weight, obj.value);
        }

        // calculate the solution:
        List<ItemUnit> itemList = cok.calcSolution();

        // write out the solution in the standard output
        if (cok.isCalculated()) {
            NumberFormat nf  = NumberFormat.getInstance();
            
            buf.append("Maximal budget           = $" +
                    nf.format(cok.getMaxWeight()) + "\n");
            
            buf.append("Total payment of solution = $" +
                nf.format(cok.getSolutionWeight()) + "\n");
            
            buf.append("Total revenue (profit)     = $" +
                    nf.format(cok.getProfit())+"\n\n"
            );
            
            buf.append("You can carry the following materials " +
                    "in the knapsack:\n");
            
            String pattern = "%1$-10s %2$-15s %3$-15s \n";
            for (ItemUnit item : itemList) {
                if (item.getInKnapsack() > tolerance) {
                	buf.append(String.format(pattern,"$" + nf.format(item.getInKnapsack()),
                        item.getName(),
                        "(value = $" + nf.format(item.getInKnapsack() *
                                                (item.getValue() / item.getWeight())) + ")"));
                }
            }
        } 
        else {
            buf.append("The problem is not solved. " +
                    "Maybe you gave wrong data.\n");
        }
    }

    //public static void main(String[] args) {
    	
    //}

} // class
