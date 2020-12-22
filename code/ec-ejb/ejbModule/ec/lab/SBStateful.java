package ec.lab;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class SBStateful
 */
@Stateful

public class SBStateful implements SBStatefulRemote, SBStatefulLocal {
		
	@EJB
	private SBStatelessLocal sbsl;
	@EJB 
	private SecurityLocal guard;
		
    /**
     * Default constructor. 
     */
    public SBStateful() {
    }

	@Override
	public String getSBType() {
		return "stateful " + sbsl.getCounter();
	}

	@Override
	public String Predict(int a) {		
		return sbsl.getPrediction(a);
	}

	@Override
	public int getCounter() {
		return sbsl.getCounter();
	}

	@Override
	public String Predict(int a, String user) {     
	    if (guard.validate(user) == true) {         
	      return sbsl.getPrediction(a);
	    } 
	    else 
	      return "invalid user";
	}

}