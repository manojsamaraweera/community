package com.jonam;

import com.jonam.model.Community;
import com.jonam.model.CommunityUser;
import com.jonam.model.ProxyUser;
import com.jonam.proxy.UserProxy;
import com.jonam.repository.CommunityRepository;
import com.jonam.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/communities/{communityId}/members")
public class MembershipResource {
    @Inject
    CommunityRepository repository;

    @Inject
    UserRepository userRepository;

    @RestClient
    UserProxy userProxy;

    @GET
    @RolesAllowed("user")
    public Response getMembers(@PathParam("communityId") String communityId) {
        return Response.ok(repository.findById(new ObjectId(communityId)).getMembers()).build();
    }

    @POST
    @RolesAllowed("user")
    public Response becomeMember(@PathParam("communityId") String communityId, String username) {
        ProxyUser retrievedProxyUser = userProxy.get(username); // validates in keycloak
        CommunityUser retrievedUser = userRepository.findByName(retrievedProxyUser.getName()); // get user from local repo
        repository.findById(new ObjectId(communityId)).getMembers().add(retrievedUser);
        return Response.ok(repository.findById(new ObjectId(communityId)).getMembers()).build();
    }

    @DELETE
    @RolesAllowed("user")
    public Response leaveMembership(@PathParam("communityId") String communityId, String username) {
        ProxyUser retrievedUser = userProxy.get(username);
        repository.findById(new ObjectId(communityId)).getMembers().remove(retrievedUser);
        return Response.ok(repository.findById(new ObjectId(communityId)).getMembers()).build();
    }

    @GET
    @Path("/admins")
    @RolesAllowed("user")
    public Response getAdminUsers(@PathParam("communityId") String communityId) {
        return Response.ok(repository.findById(new ObjectId(communityId)).getAdminUsers()).build();
    }


    @PUT
    @Path("/blacklist")
    @RolesAllowed("user")
    public Response blacklistUser(@PathParam("communityId") String communityId, String username) {
        ProxyUser retrievedProxyUser = userProxy.get(username); // validates in keycloak
        CommunityUser retrievedUser = userRepository.findByName(retrievedProxyUser.getName()); // get user from local repo
        repository.findById(new ObjectId(communityId)).getBlackList().add(retrievedUser);
        return Response.ok(repository.findById(new ObjectId(communityId)).getAdminUsers()).build();
    }

    @DELETE
    @Path("/blacklist")
    @RolesAllowed("user")
    public Response removeUserFromBlacklist(@PathParam("communityId") String communityId, String username) {
        ProxyUser retrievedUser = userProxy.get(username);
        repository.findById(new ObjectId(communityId)).getBlackList().remove(retrievedUser);
        return Response.ok(repository.findById(new ObjectId(communityId)).getAdminUsers()).build();
    }

    @GET
    @Path("/admins/creator")
    @RolesAllowed("user")
    public Response getCreatorUsers(@PathParam("communityId") String communityId) {
        return Response.ok(repository.findById(new ObjectId(communityId)).getCreatedByUser()).build();
    }

    @GET
    @Path("/search/{name}")
    @RolesAllowed("user")
    public Response search(@PathParam("name") String name) {
        Community community = repository.findByName(name);
        return community != null ? Response.ok(community).build() : Response.status(Response.Status.NOT_FOUND).build();
    }
//
//    @POST
//    @RolesAllowed("user")
//    public Response create(Community community) throws URISyntaxException {
//        repository.persist(community);
//        return Response.created(new URI("/" + community.id)).build();
//    }

//    @PUT
//    @Path("/{id}")
//    @RolesAllowed("user")
//    public Response update(@PathParam("id") String id, Community community) {
//        community.id = new ObjectId(id);
//        repository.update(community);
//        return Response.ok(community).build();
//    }

//    @DELETE
//    @Path("/{id}")
//    @RolesAllowed("system_admin")
//    public Response delete(@PathParam("id") String id) {
//        Community community = repository.findById(new ObjectId(id));
//        repository.delete(community);
//        return Response.noContent().build();
//    }
}
