/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Members;
import facades.MembersFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author emilt
 */
@Path("member")
public class MembersResource {
    
        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1DB",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MembersFacade FACADE =  MembersFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World - Members\"}";
    }
    
    @Path("/id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMember(Members entity, @PathParam("id") Long id) {
        throw new UnsupportedOperationException();
    }
    
    @Path("/name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMember(Members entity, @PathParam("name") String name) {
        throw new UnsupportedOperationException();
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        throw new UnsupportedOperationException();
    }

    
    
}
