package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Promocoes extends Conexao {
    private int promocaoId;
    private int campanhaId;
    private Double desconto;

    public Promocoes() {
        this.promocaoId = promocaoId;
        this.campanhaId = campanhaId;
        this.desconto = desconto;
    }
    public void adicionarPromocaoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da promoção: ");
        int promocaoId = scanner.nextInt();

        System.out.print("Digite o ID da campanha: ");
        int campanhaId = scanner.nextInt();

        System.out.print("Digite o valor do desconto: ");
        Double desconto = scanner.nextDouble();

        String query = "INSERT INTO Promocoes (promocaoId, campanhaId, desconto) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, promocaoId);
            pstmt.setInt(2, campanhaId);
            pstmt.setDouble(3, desconto);

            pstmt.executeUpdate();
            System.out.println("Promoção adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar promoção: " + e.getMessage());
        }

        scanner.close();
    }

    public void removerPromocaoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da promoção que deseja remover: ");
        int promocaoId = scanner.nextInt();

        String query = "DELETE FROM Promocoes WHERE promocaoId = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, promocaoId);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Promoção removida com sucesso!");
            } else {
                System.out.println("Nenhuma promoção encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover promoção: " + e.getMessage());
        }

        scanner.close();
    }

    public void atualizarPromocaoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da promoção que deseja atualizar: ");
        int promocaoId = scanner.nextInt();
        scanner.nextLine(); // limpa buffer

        Integer novaCampanhaId = null;
        Double novoDesconto = null;

        System.out.print("Deseja alterar o ID da campanha? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID da campanha: ");
            novaCampanhaId = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o desconto? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo valor do desconto: ");
            novoDesconto = scanner.nextDouble();
            scanner.nextLine();
        }

        if (novaCampanhaId == null && novoDesconto == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE Promocoes SET ");
        boolean adicionarVirgula = false;

        if (novaCampanhaId != null) {
            query.append("campanhaId = ?");
            adicionarVirgula = true;
        }
        if (novoDesconto != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("desconto = ?");
        }

        query.append(" WHERE promocaoId = ?");

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int index = 1;

            if (novaCampanhaId != null) pstmt.setInt(index++, novaCampanhaId);
            if (novoDesconto != null) pstmt.setDouble(index++, novoDesconto);

            pstmt.setInt(index, promocaoId);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Promoção atualizada com sucesso!");
            } else {
                System.out.println("Promoção não encontrada ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar promoção: " + e.getMessage());
        }

        scanner.close();
    }


}