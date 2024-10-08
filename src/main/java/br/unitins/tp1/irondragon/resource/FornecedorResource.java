package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.FornecedorRequestDTO;
import br.unitins.tp1.irondragon.dto.response.FornecedorResponseDTO;
import br.unitins.tp1.irondragon.repository.FornecedorRepository;
import br.unitins.tp1.irondragon.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fornecedores")
public class FornecedorResource {
    @Inject
    public FornecedorService fornecedorService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(FornecedorResponseDTO.valueOf(fornecedorService.findById(id))).build();
    }

    @GET
    public Response findAll() {
        return Response
                .ok(fornecedorService.findAll().stream().map(FornecedorResponseDTO::valueOf).toList())
                .build();
    }

    @POST
    public Response create(@Valid FornecedorRequestDTO dto) {
        return Response
                .status(Response.Status.CREATED).entity(FornecedorResponseDTO.valueOf(fornecedorService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id,@Valid FornecedorRequestDTO dto) {
        fornecedorService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.noContent().build();
    }
}
