package org.senai.com.br.BancoDados.Auxiliar;

public class Transportadoras {

        private int transportadoraId;
        private String nomeTransportadora;
        private String tipoVeiculo;
        private int capacidade;

    public Transportadoras(int transportadoraId, int capacidade, String tipoVeiculo, String nomeTransportadora) {
        this.transportadoraId = transportadoraId;
        this.capacidade = capacidade;
        this.tipoVeiculo = tipoVeiculo;
        this.nomeTransportadora = nomeTransportadora;
    }

    // Getters e Setters (para acessar e modificar os valores)
        public int getTransportadoraId() {
            return transportadoraId;
        }

        public void setTransportadoraId(int transportadoraId) {
            this.transportadoraId = transportadoraId;
        }

        public String getNomeTransportadora() {
            return nomeTransportadora;
        }

        public void setNomeTransportadora(String nomeTransportadora) {
            this.nomeTransportadora = nomeTransportadora;
        }

        public String getTipoVeiculo() {
            return tipoVeiculo;
        }

        public void setTipoVeiculo(String tipoVeiculo) {
            this.tipoVeiculo = tipoVeiculo;
        }

        public int getCapacidade() {
            return capacidade;
        }

        public void setCapacidade(int capacidade) {
            this.capacidade = capacidade;
        }

}
