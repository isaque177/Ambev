package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Promocao extends Conexao {

    public void Tabela_Promocoes() throws SQLException {

            String query = "SELECT * FROM Promocoes";


            try (Connection conexao = DriverManager.getConnection(url, user, password)) {
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(query);



                System.out.printf("                                                %n");
                System.out.printf("                   PROMOÇÕES                    %n");
                System.out.printf("+--------------+--------------+----------------+%n");
                System.out.printf("| Promocao_ID  | Campanha_ID  | Desconto (%%)  |%n");
                System.out.printf("+--------------+--------------+----------------+%n");

                while (rs.next()) {
                    int promocaoId = rs.getInt("promocao_id");
                    int campanhaId = rs.getInt("campanha_id");
                    double desconto = rs.getDouble("desconto");

                    System.out.printf("| %-12d | %-12d | %-14.1f |%n", promocaoId, campanhaId, desconto);
                }

                System.out.printf("+--------------+--------------+----------------+%n");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
