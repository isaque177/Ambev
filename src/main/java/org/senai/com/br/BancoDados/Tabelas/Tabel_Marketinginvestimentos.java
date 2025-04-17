package org.senai.com.br.BancoDados.Tabelas;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;

public class Tabel_Marketinginvestimentos extends Conexao {

    public void Tabela_Marketinginvestimentos () throws SQLException {

        String query = "SELECT * FROM Investimentos_Marketing";


        try (Connection conexao = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            System.out.printf("                                                                   %n");
            System.out.println("\n               LISTA DE INVESTIMENTOS EM MARKETING                ");
            System.out.println("+------------------+---------------------+-------------------------+");
            System.out.printf("| %-16s | %-19s | %-23s |%n", "Investimento ID", "Campanha ID", "Valor Investido (R$)");
            System.out.println("+------------------+---------------------+-------------------------+");


            while (rs.next()) {
                int investimentoId = rs.getInt("investimento_id");
                int campanhaId = rs.getInt("campanha_id");
                double valor = rs.getDouble("valor");

                System.out.printf("| %-16d | %-19d | R$ %-20.2f |%n", investimentoId, campanhaId, valor);
            }

            System.out.println("+------------------+---------------------+-------------------------+");


        } catch(Exception e){
            e.printStackTrace();


        }
    }
}
