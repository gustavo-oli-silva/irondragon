package br.unitins.tp1.irondragon.service.lote;

import br.unitins.tp1.irondragon.dto.request.LoteRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.Lote;
import br.unitins.tp1.irondragon.repository.LoteRepository;
import br.unitins.tp1.irondragon.service.fornecedor.FornecedorService;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import br.unitins.tp1.irondragon.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {
    @Inject
    public LoteRepository loteRepository;

    @Inject
    public ProcessadorService processadorService;

    @Inject
    public FornecedorService fornecedorService;

    @Override
    public List<Lote> findAll() {
        return loteRepository.findAll().list();
    }

    @Override
    public Lote findByCodigo(String codigo) {
        Lote lote = loteRepository.findByIdCodigo(codigo);

        if(lote == null) {
            throw new ValidationException("codigo", "Lote não existe!");
        }

        return lote;
    }

    @Transactional
    @Override
    public Lote findById(Long id) {
        Lote lote = loteRepository.findById(id);

        return lote;
    }

    @Override
    public Lote findByIdProcessador(Long id) {
        Lote lote = loteRepository.findByIdProcessador(id);

        if(lote == null) {
            throw new ValidationException("id", "Lote não existe!");
        }

        return lote;
    }

    @Transactional
    @Override
    public Lote create(LoteRequestDTO dto) {
        verificarCodigo(dto.codigo());

        Lote lote = new Lote();
        lote.setProcessador(processadorService.findById(dto.idProcessador()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());
        lote.setFornecedor(fornecedorService.findById(dto.idFornecedor()));

        loteRepository.persist(lote);

        return lote;
    }

    @Override
    public Integer findEstoqueByIdProcessador(Long idProcessador) {
        return loteRepository.findEstoqueByIdProcessador(idProcessador);
    }

    @Transactional
    @Override
    public void update(Long id, LoteRequestDTO dto) {
        verificarCodigo(dto.codigo());

        Lote lote = loteRepository.findById(id);

        if(lote == null) {
            throw new ValidationException("id", "Lote não existe!");
        }

        lote.setProcessador(processadorService.findById(id));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());
        lote.setFornecedor(fornecedorService.findById(dto.idFornecedor()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Lote lote = loteRepository.findById(id);

        if(lote == null) {
            throw new ValidationException("id", "Lote não existe!");
        }

        loteRepository.deleteById(id);
    }

    public void verificarCodigo(String codigo) {
        Lote verificacao = loteRepository.findByIdCodigo(codigo);

        if(verificacao != null) {
            throw new ValidationException("codigo", "Código fornecido já existente!");
        }
    }
}
