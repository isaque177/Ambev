package org.senai.com.br.BancoDados.Auxiliar;

public class Armazen {
    private int armazemId;
    private String nomeArmazem;
    private String localizacao;
    private int capacidade;

    public Armazen(int armazemId, int capacidade, String nomeArmazem, String localizacao) {
        this.armazemId = armazemId;
        this.capacidade = capacidade;
        this.nomeArmazem = nomeArmazem;
        this.localizacao = localizacao;
    }

    // Getters e Setters (para acessar e modificar os valores)
    public int getArmazemId() {
        return armazemId;
    }

    public void setArmazemId(int armazemId) {
        this.armazemId = armazemId;
    }

    public String getNomeArmazem() {
        return nomeArmazem;
    }

    public void setNomeArmazem(String nomeArmazem) {
        this.nomeArmazem = nomeArmazem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
