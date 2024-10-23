package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.ClienteRequestDTO;
import br.unitins.tp1.irondragon.dto.response.CidadeResponseDTO;
import br.unitins.tp1.irondragon.dto.response.ClienteResponseDTO;
import br.unitins.tp1.irondragon.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/clientes")
public class ClienteResource {
    @Inject
    public ClienteService clienteService;

    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll().stream().map(ClienteResponseDTO::valueOf).toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(ClienteResponseDTO.valueOf(clienteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response
                .ok(
                        clienteService.findByNome(nome).stream().map(ClienteResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    public Response create(@Valid ClienteRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(clienteService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid ClienteRequestDTO dto) {
        clienteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.noContent().build();
    }
}
