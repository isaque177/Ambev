package org.senai.com.br.BancoDados.Menu;

import java.sql.*;
import java.util.Scanner;


public class Criar_campanha extends org.senai.com.br.BancoDados.Conexao {
    public void criarCampanha(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.print("Digite o nome da campanha: ");
            String nome = scanner.nextLine();

            System.out.print("Data de início (YYYY-MM-DD): ");
            String inicio = scanner.nextLine();

            System.out.print("Data de término (YYYY-MM-DD): ");
            String fim = scanner.nextLine();

            String sql = "INSERT INTO Campanhas (nome_campanha, inicio, fim) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setDate(2, Date.valueOf(inicio));
                stmt.setDate(3, Date.valueOf(fim));
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("✅ Campanha criada com sucesso!");
                } else {
                    System.out.println("❌ Falha ao criar a campanha.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao conectar ou inserir no banco.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("[!] Formato de data inválido. Use o formato YYYY-MM-DD.");
        }
    }
}
