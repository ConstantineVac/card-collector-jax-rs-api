package gr.konvas.cardcollector.cardcollectorrestapijaxrs.controller;

import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardInsertDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardReadOnlyDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto.CardUpdateDTO;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.model.Card;
import gr.konvas.cardcollector.cardcollectorrestapijaxrs.service.ICardService;
import jakarta.persistence.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/cards")
public class CardsController {

    @Inject
    ICardService cardService;

    //Insert Card
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCard(CardInsertDTO dto, @Context UriInfo uriInfo) {

        try {
            Card card = cardService.insertCard(dto);
            CardReadOnlyDTO readOnlyDTO = map(card);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(readOnlyDTO.getId())).build())
                    .entity(readOnlyDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Card error in insert")
                    .build();
        }
    }

    // Map user to readonly
    private CardReadOnlyDTO map(Card card) {
        CardReadOnlyDTO readOnlyDTO = new CardReadOnlyDTO ();
        readOnlyDTO.setId(card.getId());
        readOnlyDTO.setCardName(card.getCardName());
        readOnlyDTO.setSetName(card.getSetName());
        readOnlyDTO.setSetNumber(card.getSetNumber());
        readOnlyDTO.setCopies(card.getCopies());
        readOnlyDTO.setPrice(card.getPrice());
        readOnlyDTO.setRarity(card.getRarity());
        return readOnlyDTO;
    }

    // Update Card
    @Path("/{cardId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCard(@PathParam("cardId") Long cardId, CardUpdateDTO dto) {
        dto.setId(cardId);
        Card card = cardService.updateCard(dto);
        CardReadOnlyDTO readOnlyDTO = map(card);
        return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
    }

    // Delete User
    @Path("/{cardId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCard(@PathParam("cardId") Long cardId) {
        Card card = cardService.getCardById(cardId);
        cardService.deleteCard(cardId);
        CardReadOnlyDTO readOnlyDTO = map(card);
        return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
    }

    // Get Card By ID
    @Path("/{cardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardById(@PathParam("cardId") Long cardId) {
        Card card = cardService.getCardById(cardId);
        if (card != null) {
            return Response.status(Response.Status.OK).entity(card).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Card not found!").build();
    }

    // Get Cards By Set name.
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardsBySet(@QueryParam("setName") String setName) {
        List<Card> cards = cardService.getCardsBySet(setName);
        if (!cards.isEmpty()) {
            return Response.status(Response.Status.OK).entity(cards).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("No Cards found from this set.").build();
    }

    // Get All Cards
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCards() {
        try {
            List<Card> cards = cardService.getAllCards();
            if (cards.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("No cards found.").build();
            }

            return Response.status(Response.Status.OK).entity(cards).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cards not found.").build();
        }
    }
}
