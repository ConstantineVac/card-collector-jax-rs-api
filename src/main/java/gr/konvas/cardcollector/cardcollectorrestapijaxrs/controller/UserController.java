package gr.konvas.cardcollector.cardcollectorrestapijaxrs.controller;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserReadOnlyDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserUpdateDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.User;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.IUserService;
import jakarta.persistence.EntityNotFoundException;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@Path("/users")
public class UserController {
    @Inject
    private IUserService userService;

    // Insert User
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserInsertDTO dto, @Context UriInfo uriInfo) {

        try {
            User user = userService.insertUser(dto);
            UserReadOnlyDTO readOnlyDTO = map(user);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(readOnlyDTO.getId())).build())
                    .entity(readOnlyDTO).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User error in insert")
                    .build();
        }
    }

    // Map user to readonly
    private UserReadOnlyDTO map(User user) {
        UserReadOnlyDTO readOnlyDTO = new UserReadOnlyDTO();
        readOnlyDTO.setId(user.getId());
        readOnlyDTO.setEmail(user.getEmail());
        readOnlyDTO.setUsername(user.getUsername());
        readOnlyDTO.setPassword(user.getPassword());
        readOnlyDTO.setFirstname(user.getFirstname());
        readOnlyDTO.setLastname(user.getLastname());
        return readOnlyDTO;
    }

    // Update User
    @Path("/{userId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Long userId, UserUpdateDTO dto) {
        dto.setId(userId);
        User user = userService.updateUser(dto);
        UserReadOnlyDTO readOnlyDTO = map(user);
        return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }

    @Path("/{userId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        userService.deleteUser(userId);
        UserReadOnlyDTO readOnlyDTO = map(user);
        return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@QueryParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return Response.status(Response.Status.OK).entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        try {
            // Call the service to get all users
            List<User> users = userService.getAllUsers();
            // Check if the list of users is empty
            if (users.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("No users found.").build();
            }

            // Return the list of users as JSON
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Users not found.").build();
        }
    }
}

