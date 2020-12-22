package ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBStatelessRemote {
	 public String getSBType();
	 public String getPrediction(int a);
}