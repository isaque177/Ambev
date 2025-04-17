package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Campanhas extends Conexao {

    public void Tabela_Campanha() throws SQLException {

            String query = "SELECT * FROM Campanhas";


            try (Connection conexao = DriverManager.getConnection(url, user, password)) {
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(query);



                System.out.printf("                                                                                     %n");
                System.out.printf("                                     CAMPANHAS                                       %n");
                System.out.printf("+--------------+------------------------------------------+------------+------------+%n");
                System.out.printf("| Campanha_ID  | Nome da Campanha                         | Início     | Fim        |%n");
                System.out.printf("+--------------+------------------------------------------+------------+------------+%n");


                while (rs.next()) {
                    int id = rs.getInt("campanha_id");
                    String nome = rs.getString("nome_campanha");
                    Date inicio = rs.getDate("inicio");
                    Date fim = rs.getDate("fim");

                    System.out.printf("| %-12d | %-40s | %-10s | %-10s |%n",
                            id, nome, inicio.toString(), fim.toString());
                }

                System.out.printf("+--------------+------------------------------------------+------------+------------+%n");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

