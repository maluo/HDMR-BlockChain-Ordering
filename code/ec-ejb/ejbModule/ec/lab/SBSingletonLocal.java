package ec.lab;

import javax.ejb.Local;

@Local
public interface SBSingletonLocal {
	public int incCounter();
	public int getCounter();
	public String getPredictionPlanWithBudget(int budget);
}