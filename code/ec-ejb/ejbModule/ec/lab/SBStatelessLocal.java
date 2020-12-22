package ec.lab;

import javax.ejb.Local;

@Local
public interface SBStatelessLocal {
	public String getPrediction(int a);
	public int getCounter();
}