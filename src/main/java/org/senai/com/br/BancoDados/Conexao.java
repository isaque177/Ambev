package org.senai.com.br.BancoDados;

import java.sql.*;

public class Conexao {

        protected String url = "jdbc:mysql://localhost:3306/Ambev";

        protected String user = "root";
        protected String password = "1234";

        public void Verif_banco () {

        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão realizada com sucesso!");


        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
