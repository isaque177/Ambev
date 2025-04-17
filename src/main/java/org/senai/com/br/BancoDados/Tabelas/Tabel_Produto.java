package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Produto extends Conexao {

    public void Tabela_Produto() throws SQLException {

        String query = "SELECT * FROM Produtos";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


                // Cabeçalho
            System.out.printf("                                                                                                                 %n");
                System.out.printf("                                                      PRODUTOS                                               %n");
                System.out.printf("+----+----------------------+--------------------------++-----------------++-----------------++--------------%n");
                System.out.printf("| ID |             PRODUTOS                 |           PREÇO             |           CATEGORIA             |%n");
                System.out.printf("+----+----------------------+--------------------------++-----------------++-----------------++--------------%n");

                // Linhas da tabela
                while (rs.next()) {
                    int id = rs.getInt("produto_id");
                    String nome_produto = rs.getString("nome_produto");
                    String preco = rs.getString("preco");
                    String categoria = rs.getString("categoria");

                    System.out.printf("| %-2d | %-33s | %-28s | %-33s |%n", id, nome_produto, preco, categoria);
                }

                System.out.printf("+----+----------------------+--------------------------++----------------+----+---------+----+---------+---+%n%n");

            } catch(Exception e){
                e.printStackTrace();


            }
        }
    }
