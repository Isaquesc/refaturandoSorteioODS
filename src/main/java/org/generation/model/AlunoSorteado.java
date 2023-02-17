package org.generation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoSorteado extends Aluno{

    private String dataSorteio;
    private String horario;
    private String tema;
    public AlunoSorteado(String nome, String dataSorteio, String horario, String tema) {
        super(nome);
        this.dataSorteio = dataSorteio;
        this.horario = horario;
        this.tema = tema;
    }

    @Override
    public String toString() {
        return dataSorteio + "T" + horario
                + " Nome: "+ super.getNome() + " Tema: " + tema;
    }
}
