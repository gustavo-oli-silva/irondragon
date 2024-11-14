package br.unitins.tp1.irondragon.service.lote;

import br.unitins.tp1.irondragon.dto.request.LoteRequestDTO;
import br.unitins.tp1.irondragon.model.pedido.Lote;
import br.unitins.tp1.irondragon.repository.LoteRepository;
import br.unitins.tp1.irondragon.service.processador.ProcessadorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LoteServiceImpl implements LoteService {
    @Inject
    public LoteRepository loteRepository;

    @Inject
    public ProcessadorService processadorService;

    @Override
    public Lote findByCodigo(String codigo) {
        return loteRepository.findByIdCodigo(codigo);
    }

    @Transactional
    @Override
    public Lote findById(Long id) {
        return loteRepository.findById(id);
    }

    @Override
    public Lote findByIdProcessador(Long id) {
        return loteRepository.findByIdProcessador(id);
    }

    @Transactional
    @Override
    public Lote create(LoteRequestDTO dto) {
        Lote lote = new Lote();
        lote.setProcessador(processadorService.findById(dto.idProcessador()));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());

        loteRepository.persist(lote);

        return lote;
    }

    @Transactional
    @Override
    public void update(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id);
        lote.setProcessador(processadorService.findById(id));
        lote.setCodigo(dto.codigo());
        lote.setData(dto.data());
        lote.setEstoque(dto.estoque());
    }
}
