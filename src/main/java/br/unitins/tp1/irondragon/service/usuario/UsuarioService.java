package br.unitins.tp1.irondragon.service.usuario;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.model.Usuario;

import java.util.List;

public interface UsuarioService {
    public Usuario findById(Long id);

    public List<Usuario> findByNome(String nome);

    public Usuario findByUsernameAndSenha(String username, String senha);

    public List<Usuario> findAll();

//    public Estado create(EstadoRequestDTO dto);
//
//    public void update(Long id, EstadoRequestDTO dto);

    public void delete(Long id);
}
