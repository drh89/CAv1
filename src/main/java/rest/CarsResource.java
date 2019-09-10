package rest;

import DTO.CarsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Cars;
import entities.Joke;
import facades.CarsFacade;
import utils.EMF_Creator;
import facades.JokeFacade;
import java.util.List;
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
@Path("cars")
public class CarsResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1DB",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final CarsFacade FACADE =  CarsFacade.getCarsFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Cars\"}";
    }
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        long count = FACADE.getRenameMeCount();
//        //System.out.println("--------------->"+count);
//        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
//    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCars() {
        List<Cars> cars = FACADE.getCars();
        List<CarsDTO> carsDTO = null;
        for (Cars car : cars) {
            carsDTO.add(new CarsDTO(car));
            }
        return Response.ok().entity(GSON.toJson(carsDTO)).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Joke entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
