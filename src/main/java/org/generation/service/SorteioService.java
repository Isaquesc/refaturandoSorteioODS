package org.generation.service;


import org.generation.model.Aluno;
import org.generation.model.AlunoSorteado;
import org.generation.model.Tema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SorteioService {

    ListaService listaService = null;

    public SorteioService(ListaService service) {
        this.listaService = service;
    }

    public AlunoSorteado sorteioAluno(String tema) {

        Optional<List<Aluno>> optionalAlunosList = listaService.gerandoListaAlunos();

        if (optionalAlunosList.isEmpty()) {
            System.err.println("LISTA DE ALUNOS VAZIA");
            return new AlunoSorteado(null, null, null, null);
        }

        List<Aluno> alunoList = optionalAlunosList.get();
        Collections.shuffle(alunoList);

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Aluno aluno = alunoList.get(new Random().nextInt(0, alunoList.size()));

        alunoList.remove(aluno);
        listaService.removendoAlunoSorteado(alunoList);

        return listaService.gerandoArquivoAlunosSorteados(aluno,data, time, tema);
    }

    public Tema sorteioTema() {
        Optional<List<Tema>> optionalTemasList = listaService.gerandoListaTemas();

        if (optionalTemasList.isEmpty()) {
            System.err.println("LISTA DE TEMAS VAZIA");
            return new Tema("Livre");
        }

        List<Tema> temaList = optionalTemasList.get();

        Collections.shuffle(temaList);

        return temaList.get(new Random().nextInt(0, temaList.size()));
    }

}
