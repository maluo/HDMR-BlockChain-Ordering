package ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBStatefulRemote {
	 public String getSBType();
	 public String Predict(int a);
	 public int getCounter();
	 public String Predict(int a, String user);
}