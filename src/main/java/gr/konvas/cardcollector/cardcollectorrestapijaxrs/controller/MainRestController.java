package gr.konvas.cardcollector.cardcollectorrestapijaxrs.controller;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.UserReadOnlyDTO;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Path("/dashboard")
public class MainRestController {

    private final Validator validator;

    public MainRestController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Path("/say-hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloMsg() {
        return "Welcome to PokeBinder";
    }

}
