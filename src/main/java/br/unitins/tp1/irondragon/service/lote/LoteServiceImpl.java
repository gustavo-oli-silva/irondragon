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
    public List<Lote> findAll(Integer page, Integer pageSize) {
        return loteRepository.findAll().page(page, pageSize).list();
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
        verificarCodigoNovo(dto.codigo());

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
        verificarCodigoEdicao(dto.codigo(), id);

        Lote lote = loteRepository.findById(id);

        if(lote == null) {
            throw new ValidationException("id", "Lote não existe!");
        }

        lote.setProcessador(processadorService.findById(dto.idProcessador()));
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

    public void verificarCodigoNovo(String codigo) {
        Lote existente = loteRepository.findByIdCodigo(codigo);
        if (existente != null) {
            throw new ValidationException("codigo", "Código fornecido já existente!");
        }
    }
    
    public void verificarCodigoEdicao(String codigo, Long idLote) {
        Lote existente = loteRepository.findByIdCodigo(codigo);
        if (existente != null && !existente.getId().equals(idLote)) {
            throw new ValidationException("codigo", "Código fornecido já existente!");
        }
    }

    
    @Override
    public Long count() {
        return loteRepository.count();
    }

    @Override
    public List<Lote> findLastLotesByProcessadorDistinctOn() {
       return loteRepository.findLastLotesByProcessadorDistinctOn();
    }

    
}
