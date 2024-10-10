package br.unitins.tp1.irondragon.service;

import br.unitins.tp1.irondragon.dto.request.PessoaFisicaRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.model.PessoaFisica;
import br.unitins.tp1.irondragon.model.Sexo;
import br.unitins.tp1.irondragon.repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService {
    @Inject
    public PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public PessoaFisica findById(Long id) {
        return pessoaFisicaRepository.findById(id);
    }

    @Override
    public List<PessoaFisica> findAll() {
        return pessoaFisicaRepository.findAll().list();
    }

    @Override
    public List<PessoaFisica> findByNome(String nome) {
        return pessoaFisicaRepository.findByNome(nome);
    }

    @Transactional
    @Override
    public PessoaFisica create(PessoaFisicaRequestDTO dto) {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.nome());
        pf.setCpf(dto.cpf());
        pf.setSexo(Sexo.valueOf(dto.idSexo()));

        pessoaFisicaRepository.persist(pf);

        return pf;
    }

    @Transactional
    @Override
    public void update(Long id, PessoaFisicaRequestDTO dto) {
        PessoaFisica pf = pessoaFisicaRepository.findById(id);

        pf.setNome(dto.nome());
        pf.setCpf(dto.cpf());
        pf.setSexo(Sexo.valueOf(dto.idSexo()));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
