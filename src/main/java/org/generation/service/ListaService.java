package org.generation.service;

import org.generation.model.Aluno;
import org.generation.model.AlunoSorteado;
import org.generation.model.Tema;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ListaService {

    private final String ALUNOS = "C:\\Users\\isaqu\\Downloads\\nomes_alunos.txt";
    private final String TEMAS = "C:\\Users\\isaqu\\Downloads\\nomes_temas.txt";
    private final String ALUNOS_SORTEADOS = "C:\\Users\\isaqu\\Downloads\\alunos_sorteados.txt";

    public Optional<List<Aluno>> gerandoListaAlunos() {

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(ALUNOS)))) {
            List<Aluno> listaALunos = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String nome = scanner.nextLine();
                listaALunos.add(new Aluno(nome));
            }

            if(listaALunos.isEmpty())
                return Optional.empty();

            return Optional.of(listaALunos);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Tema>> gerandoListaTemas() {

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(TEMAS)))) {
            scanner.useDelimiter(";");
            List<Tema> listaTemas = new ArrayList<>();

            while (scanner.hasNext()) {
                String tema = scanner.next();
                scanner.nextLine();
                listaTemas.add(new Tema(tema));
            }

            if(listaTemas.isEmpty())
                return Optional.empty();

            return Optional.of(listaTemas);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public AlunoSorteado gerandoArquivoAlunosSorteados(Aluno aluno, String date, String timer, String tema) {

        AlunoSorteado alunoSorteado = new AlunoSorteado(aluno.getNome(), date, timer, tema);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ALUNOS_SORTEADOS, true))) {
            writer.write(alunoSorteado.toString());
            writer.newLine();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return alunoSorteado;
    }

    public void removendoAlunoSorteado(List<Aluno> listRemove) {

        List<Aluno> list = listRemove;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ALUNOS, false))) {
            for (Aluno aluno : list) {
                writer.write(aluno.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
