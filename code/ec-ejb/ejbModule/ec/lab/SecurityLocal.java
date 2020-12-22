package ec.lab;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface SecurityLocal {
    public Boolean validate(String user);
}