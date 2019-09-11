/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.MembersDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Members;
import facades.MembersFacade;
import java.util.ArrayList;
import java.util.List;
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
                "jdbc:mysql://localhost:3307/CAv1DB",
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

    @Path("/name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMember(Members entity, @PathParam("name") String name) {
        ArrayList<MembersDTO> l = FACADE.getMembersDTOByName(name);
            return GSON.toJson(l);
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        ArrayList<MembersDTO> l = FACADE.getAllMembersDTO();
        if (l.isEmpty()) {
            return "";
        } else {
            return GSON.toJson(l);
        }
    }
    
    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populateMembers() {
        FACADE.populateMembers();
        return "Your database has been populated";
    }
    
    @Path("/deleteAll")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteAllMembers() {
        FACADE.deleteAllMembers();
        FACADE.addMember(new Members("","",0));
        return "Your database has been cleared";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMembersCount() {
        long count = FACADE.getAllMembers().size();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
}
