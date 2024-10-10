package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.request.PessoaFisicaRequestDTO;
import br.unitins.tp1.irondragon.dto.request.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.dto.response.PessoaFisicaResponseDTO;
import br.unitins.tp1.irondragon.dto.response.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.service.PessoaFisicaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pessoasfisicas")
public class PessoaFisicaResource {
    @Inject
    public PessoaFisicaService pessoaFisicaService;

    @GET
    public Response findAll() {
        return Response
                .ok(pessoaFisicaService.findAll().stream().map(PessoaFisicaResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response
                .ok(pessoaFisicaService.findByNome(nome).stream().map(PessoaFisicaResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .ok(PessoaFisicaResponseDTO.valueOf(pessoaFisicaService.findById(id)))
                .build();
    }

    @POST
    public Response create(PessoaFisicaRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(PessoaFisicaResponseDTO.valueOf(pessoaFisicaService.create(dto)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PessoaFisicaRequestDTO dto) {
        pessoaFisicaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pessoaFisicaService.delete(id);
        return Response.noContent().build();
    }
}

