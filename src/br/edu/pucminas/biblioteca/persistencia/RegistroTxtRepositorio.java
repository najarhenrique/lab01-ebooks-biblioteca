package br.edu.pucminas.biblioteca.persistencia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RegistroTxtRepositorio {
    private final Path arquivo;

    public RegistroTxtRepositorio(String caminhoArquivo) {
        this.arquivo = Paths.get(caminhoArquivo);
    }

    public void salvarLinha(String linha) {
        try {
            if (arquivo.getParent() != null) {
                Files.createDirectories(arquivo.getParent());
            }

            try (BufferedWriter escritor = Files.newBufferedWriter(
                    arquivo,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                escritor.write(linha);
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel salvar o registro em arquivo", e);
        }
    }

    public List<String> carregarLinhas() {
        try {
            if (!Files.exists(arquivo)) {
                return new ArrayList<>();
            }

            return Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel ler os registros salvos", e);
        }
    }
}