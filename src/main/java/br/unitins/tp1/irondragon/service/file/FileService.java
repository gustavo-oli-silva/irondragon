package br.unitins.tp1.irondragon.service.file;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String save(String nomeArquivo, byte[] arquivo) throws IOException;

    File find(String nomeArquivo);
}
