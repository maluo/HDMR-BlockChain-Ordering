package ec.lab;

import javax.ejb.LocalBean;

import ec.CALCULATION.ContinousKnapsackWrapper;
import ec.REPOSITORY.OrderRepo;

import javax.ejb.Singleton;

/**
 * Session Bean implementation class SBSingleton
 */
@Singleton
@LocalBean
public class SBSingleton implements SBSingletonRemote, SBSingletonLocal {
	private int counter = 0;
	OrderRepo repo = null;

    /**
     * Default constructor. 
     */
    public SBSingleton() {
    	repo = new OrderRepo();
    }

	@Override
	public int getCounter() {
		//orderImpl.OrderProfitService();
		return counter;
	}

	@Override
	public int incCounter() {		
		counter++;
		return counter;
	}

	@Override
	public String getSBType() {
		return "singleton";
	}
	
	@Override
	public String getPredictionPlanWithBudget(int budget) {
		// TODO Auto-generated method stub
    	ContinousKnapsackWrapper o = new ContinousKnapsackWrapper(budget,repo.getKnapsackInput());
		return o.getBuf();
	}
}