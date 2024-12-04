package br.unitins.tp1.irondragon.service.file;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UsuarioFileServiceImpl implements FileService {
    private final String PATH_PROCESSADOR =
            System.getProperty("user.home") +
                    File.separator + "quarkus" +
                    File.separator + "usuario" +
                    File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES =
            List.of(
                    "image/jpeg",
                    "image/png",
                    "image/jpeg"
            );

    //10 mb
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10;

    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {
        Path dir = Paths.get(PATH_PROCESSADOR);

        Files.createDirectories(dir);

        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));

        isImage(mimeType);

        String extensao = mimeType.substring(mimeType.lastIndexOf('/') + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        Path filePath = dir.resolve(novoNomeArquivo);

        while(filePath.toFile().exists()) {
            novoNomeArquivo = UUID.randomUUID() + "." + extensao;
            filePath = dir.resolve(novoNomeArquivo);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return novoNomeArquivo;
    }

    @Override
    public File find(String nomeArquivo) {
        File file = new File(PATH_PROCESSADOR + nomeArquivo);

        if(file.exists()) {
            return file;
        }

        return null;
    }

    public void imageIsHigherThanLimit(byte[] arquivo) throws IOException {
        if(arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("O Arquivo excede 10Mb de tamanho");
        }
    }

    public void isImage(String mimeType) throws IOException {
        if(!(SUPPORTED_MIME_TYPES.contains(mimeType))) {
            throw new IOException("Formato de arquivo não suportado!");
        }
    }
}
