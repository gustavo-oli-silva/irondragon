package br.unitins.tp1.irondragon.resource;

import br.unitins.tp1.irondragon.dto.response.PageResponse;
import br.unitins.tp1.irondragon.dto.response.processador.ProcessadorResponseDTO;
import br.unitins.tp1.irondragon.form.ProcessadorImageForm;
import br.unitins.tp1.irondragon.model.processador.Processador;
import br.unitins.tp1.irondragon.service.file.ProcessadorFileServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.tp1.irondragon.dto.request.ProcessadorFilterRequest;
import br.unitins.tp1.irondragon.dto.request.processador.ProcessadorRequestDTO;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.IOException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/processadores")
public class ProcessadorResource {
    private static final Logger LOGGER = Logger.getLogger(ProcessadorResource.class);

    @Inject
    public ProcessadorService processadorService;

    @Inject
    public ProcessadorFileServiceImpl processadorFileService;

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findAll foi executado!");

        Long count = processadorService.count();

        return Response
                .ok(PageResponse.valueOf(page, pageSize, count,
                        processadorService.findAll(page, pageSize).stream().map(ProcessadorResponseDTO::valueOf)
                                .toList()))
                .build();
    }

    @GET
    @Path("/filtros")
    public Response findByFiltros(
            @QueryParam("nome") String nome,
            @QueryParam("fabricante") String fabricante,
            @QueryParam("precoMin") Double precoMin,
            @QueryParam("precoMax") Double precoMax,
            @QueryParam("sockets") List<String> sockets,
            @QueryParam("graficosIntegrados") String graficosIntegrados,
            @QueryParam("sortBy") @DefaultValue("preco-asc") String sortBy,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {

        ProcessadorFilterRequest filtros = new ProcessadorFilterRequest(nome, fabricante, precoMin, precoMax, sockets,
                graficosIntegrados, sortBy);
        Long count = processadorService.count(filtros);
        return Response
                .ok(PageResponse.valueOf(page, pageSize, count,
                        processadorService.findByFiltros(page, pageSize, filtros).stream()
                                .map(ProcessadorResponseDTO::valueOf)
                                .toList()))
                .build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("page_size") @DefaultValue("10") Integer pageSize) {
        LOGGER.info("Método findByNome foi executado com o parametro " + nome);

        Long count = processadorService.count(nome);

        return Response
                .ok(PageResponse.valueOf(page, pageSize, count,
                        processadorService.findByNome(nome, page, pageSize).stream()
                                .map(ProcessadorResponseDTO::valueOf).toList()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Método findById foi executado com o parametro " + id);

        return Response
                .ok(ProcessadorResponseDTO.valueOf(processadorService.findById(id)))
                .build();
    }

    @POST
    @RolesAllowed({"Super", "Admin"})
    public Response create(ProcessadorRequestDTO processador) {
        LOGGER.info("Método create foi executado, Processador: " + processador);

        return Response.status(Status.CREATED)
                .entity(ProcessadorResponseDTO.valueOf(processadorService.create(processador)))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response update(@PathParam("id") Long id, @Valid ProcessadorRequestDTO processador) {
        LOGGER.info("Método update foi executado com o parametro " + id + ", Processador: " + processador);

        processadorService.update(id, processador);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Super", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Método delete foi executado com o parametro " + id);

        processadorService.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, @MultipartForm ProcessadorImageForm form) {
        LOGGER.info("Método uploadImage foi executado com o parametro " + id);

        try {
            String nomeImagem = processadorFileService.save(id, form.getNomeImagem(), form.getImagem());
            processadorService.updateNomeImagem(id, nomeImagem);
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("{id}/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("id") Long id, @PathParam("nomeImagem") String nomeImagem) {
        LOGGER.info("Método downloadImage foi executado");

        Response.ResponseBuilder response = Response.ok(processadorFileService.find(id, nomeImagem));
        response.header("Content-Disposition", "attachment; filename=" + nomeImagem);
        return response.build();
    }
}
