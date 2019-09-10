package rest;

import DTO.JokeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import utils.EMF_Creator;
import facades.JokeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA1DB",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Nothing here but us lemmings\"}";
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response populate() {
        FACADE.populateJoke();
        return Response.status(200).entity("{\"msg\":\"populated...\"}").build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String update(Joke entity, @PathParam("id") int id) {
        return GSON.toJson(FACADE.getJokeDTOByID(id));
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDTO() {
        return GSON.toJson(FACADE.getAllJokesDTO());
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        return "{\"count\":" + FACADE.getJokeCount() + "}";
    }
}
