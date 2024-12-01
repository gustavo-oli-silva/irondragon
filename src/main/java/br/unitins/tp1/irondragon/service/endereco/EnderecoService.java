package br.unitins.tp1.irondragon.service.endereco;

import br.unitins.tp1.irondragon.dto.request.EnderecoRequestDTO;
import br.unitins.tp1.irondragon.model.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco findById(Long id);

    List<Endereco> findAll();

    List<Endereco> listByUsername(String username);

    Endereco create(EnderecoRequestDTO dto, String username);

    Endereco findByIdAndUsername(Long id, String username);

    void update(Long id, EnderecoRequestDTO dto, String username);

    void delete(Long id, String username);
}
