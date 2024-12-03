package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.response.processador.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.form.ProcessadorImageForm;
import br.unitins.tp1.irondragon.service.file.ProcessadorFileServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/processadores")
public class ProcessadorResource {
    @Inject
    public ProcessadorService processadorService;

    @Inject
    public ProcessadorFileServiceImpl processadorFileService;

    @GET
    public Response findAll() {
        return Response
                .ok(processadorService.findAll().stream().map(ProcessadorResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response
                .ok(processadorService.findByNome(nome).stream().map(ProcessadorResponseDTO::valueOf).toList())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .ok(ProcessadorResponseDTO.valueOf(processadorService.findById(id)))
                .build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(ProcessadorRequestDTO processador) {
        return Response.status(Status.CREATED)
                .entity(ProcessadorResponseDTO.valueOf(processadorService.create(processador)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid ProcessadorRequestDTO processador) {
        processadorService.update(id, processador);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        processadorService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}/upload/imagem")
    @RolesAllowed({"Super", "Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ProcessadorImageForm form) {
        try {
            String nomeImagem = processadorFileService.save(form.getNomeImagem(), form.getImagem());
            processadorService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("nomeImagem") String nomeImagem) {
        Response.ResponseBuilder response = Response.ok(processadorFileService.find(nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }
}
