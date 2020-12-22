package ec.lab;

import javax.ejb.Local;

@Local
public interface SBStatefulLocal {
	public String Predict(int a);	
}