package org.generation;

import org.generation.model.AlunoSorteado;
import org.generation.service.ListaService;
import org.generation.service.SorteioService;

public class SorteioOdsMain {
    public static void main(String[] args) {

        SorteioService sorteioService = new SorteioService(new ListaService());
        AlunoSorteado alunoSorteado = sorteioService.sorteioAluno(sorteioService.sorteioTema().getTema());

        System.out.println(alunoSorteado);

    }
}