package com.distribuida.books.clients;

import com.distribuida.books.dtos.AutorDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "http://localhost:9090")
@RegisterRestClient(configKey = "AuthorRestClient")
public interface AuthorRestClient {

    @GET
    @Path("/{id}")
    AutorDto findById(@PathParam("id") Integer id);

}
