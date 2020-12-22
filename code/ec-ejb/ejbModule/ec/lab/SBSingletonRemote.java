package ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBSingletonRemote {
	 public String getSBType();
	 public int getCounter();
}