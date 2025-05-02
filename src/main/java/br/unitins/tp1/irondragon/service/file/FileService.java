package br.unitins.tp1.irondragon.service.file;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String save(Long id, String nomeArquivo, byte[] arquivo) throws IOException;

    File find(Long id, String nomeArquivo);
}
