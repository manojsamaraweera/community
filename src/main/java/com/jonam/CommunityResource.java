package com.jonam;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.ws.rs.*;
import org.bson.types.ObjectId;

import com.jonam.community.Community;
import com.jonam.repository.CommunityRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/communities")
public class CommunityResource {
    @Inject
    CommunityRepository repository;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id) {
        Community community = repository.findById(new ObjectId(id));
        return Response.ok(community).build();
    }

    @GET
    public Response get() {
        return Response.ok(repository.listAll()).build();
    }

    @GET
    @Path("/search/{name}")
    public Response search(@PathParam("name") String name) {
        Community community = repository.findByName(name);
        return community != null ? Response.ok(community).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Community community) throws URISyntaxException {
        repository.persist(community);
        return Response.created(new URI("/" + community.id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Community community) {
        community.id = new ObjectId(id);
        repository.update(community);
        return Response.ok(community).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Community community = repository.findById(new ObjectId(id));
        repository.delete(community);
        return Response.noContent().build();
    }
}
