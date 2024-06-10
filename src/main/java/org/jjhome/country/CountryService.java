package org.jjhome.country;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class CountryService{

    private Set<Country> countries = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public CountryService(){
        countries.add(new Country("IN", "India"));
        countries.add(new Country("SG", "Singapore"));
    }

    @Path("country")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String country(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(countries);
        } catch (Exception e) {
            throw new RuntimeException("Error converting countries to JSON", e);
        }
    }

    @Path("country")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Country country) {
        countries.add(country);
        return Response.status(Response.Status.CREATED)
                .entity("{\"status\":\"Success\",\"message\":\"Country added successfully\"}")
                .build();
    }
}
