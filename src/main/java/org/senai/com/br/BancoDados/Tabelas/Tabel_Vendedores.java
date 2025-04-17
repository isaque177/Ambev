package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Vendedores extends Conexao {

    public void Tabela_vendedores () throws SQLException {

        String query = "SELECT * FROM Vendedores";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            // Cabeçalho formatado
            System.out.printf("                                                                            %n");
            System.out.println("\n                            LISTA DE VENDEDORES                            ");
            System.out.println("+------------+-------------------------------+-------------------------------+");
            System.out.printf("| %-10s | %-30s | %-30s |%n", "ID", "Nome", "Email");
            System.out.println("+------------+-------------------------------+-------------------------------+");

            // Exibe os resultados
            while (rs.next()) {
                int id = rs.getInt("vendedor_id");
                String nome = rs.getString("nome_vendedor");
                String email = rs.getString("email");

                System.out.printf("| %-10d | %-30s | %-30s |%n", id, nome, email);
            }

            System.out.println("+------------+-------------------------------+-------------------------------+");


        } catch(Exception e){
            e.printStackTrace();


        }
    }
}
