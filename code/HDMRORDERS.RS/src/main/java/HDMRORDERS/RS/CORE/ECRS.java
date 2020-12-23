package HDMRORDERS.RS.CORE;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.lab.SBStatelessLocal;

@Path("/")
@RequestScoped
public class ECRS {  
    @EJB SBStatelessLocal sbsl;
    //@Inject SBStatelessLocal sbsl;
    
    @GET
    @Path("/predict")
    @Produces("text/plain")
    public String predict(@QueryParam("value") String value) {
         return sbsl.getPrediction(Integer.valueOf(value));
    } 
     
    @GET
    @Path("/predictjson")
    @Produces({ "application/json" })
    public String predictJSon(@QueryParam("value") String value) {
        return "{\"result\":\"" + sbsl.getPrediction(Integer.valueOf(value)) + "\"}";
    }

    @POST
    @Path("/predictxml")
    @Produces({ "application/xml" })
    public String predictXML(@FormParam("value") String value) {
         return "<xml><result>" + sbsl.getPrediction(Integer.valueOf(value)) + "</result></xml>";
    }
}