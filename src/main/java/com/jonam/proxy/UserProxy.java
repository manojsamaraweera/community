package com.jonam.proxy;

import com.jonam.model.CommunityUser;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/users/")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "localhost:8088/")
public interface UserProxy {
    @GET
    @Path("/{userId}")
    CommunityUser get(@PathParam("name") String name);
}
