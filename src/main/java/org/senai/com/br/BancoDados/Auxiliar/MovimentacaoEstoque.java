package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class MovimentacaoEstoque extends Conexao {
    private int movimentacao_id;
    private int estoqueId;
    private int armazemId;
    private int produtoId;
    private int quantidade;

    public MovimentacaoEstoque() {
        this.movimentacao_id = movimentacao_id;
        this.estoqueId = estoqueId;
        this.armazemId = armazemId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public void rastrearEstoque() {

        Scanner scanner = new Scanner(System.in);
        boolean idValido = false; // Flag para verificar se o ID é válido

        // Pergunta pelo ID da entrega até o usuário fornecer um ID válido
        while (!idValido) {
            System.out.print("Digite o ID do Estoque que deseja rastrear: ");
            int  movimentacao_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            // Verificar se o ID existe no banco de dados
            String query = "SELECT entrega_id, status_entrega FROM Entregas WHERE entrega_id = ?";
            try (Connection conexao = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conexao.prepareStatement(query)) {
                pstmt.setInt(1, movimentacao_id);
                ResultSet rs = pstmt.executeQuery();

                // Verificar se o ID foi encontrado no banco
                if (rs.next()) {
                    idValido = true; // ID válido encontrado
                    this.movimentacao_id = movimentacao_id; // Armazenar o ID
                    String statusAtual = rs.getString("status_entrega"); // Obter o status da entrega
                    System.out.println("Status atual da entrega ID " + movimentacao_id + ": " + statusAtual);
                } else {
                    System.out.println("ID inválido! Tente novamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionarMovimentacaoViaConsole () {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do estoque: ");
        int estoqueId = scanner.nextInt();

        System.out.print("Digite o ID do armazém: ");
        int armazemId = scanner.nextInt();

        System.out.print("Digite o ID do produto: ");
        int produtoId = scanner.nextInt();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        String query = "INSERT INTO MovimentacaoEstoque (estoqueId, armazemId, produtoId, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, estoqueId);
            pstmt.setInt(2, armazemId);
            pstmt.setInt(3, produtoId);
            pstmt.setInt(4, quantidade);

            pstmt.executeUpdate();
            System.out.println("Movimentação adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar movimentação: " + e.getMessage());
        }

        scanner.close();
    }

    public void removerMovimentacaoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do estoque para remover a movimentação: ");
        int estoqueId = scanner.nextInt();

        String query = "DELETE FROM MovimentacaoEstoque WHERE estoqueId = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, estoqueId);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Movimentação removida com sucesso!");
            } else {
                System.out.println("Nenhuma movimentação encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover movimentação: " + e.getMessage());
        }
    }

    public void atualizarMovimentacaoViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do estoque que deseja atualizar: ");
        int estoqueId = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Integer novoArmazemId = null;
        Integer novoProdutoId = null;
        Integer novaQuantidade = null;

        System.out.print("Deseja alterar o ID do armazém? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID do armazém: ");
            novoArmazemId = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o ID do produto? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID do produto: ");
            novoProdutoId = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar a quantidade? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova quantidade: ");
            novaQuantidade = scanner.nextInt();
            scanner.nextLine();
        }

        if (novoArmazemId == null && novoProdutoId == null && novaQuantidade == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE MovimentacaoEstoque SET ");
        boolean adicionarVirgula = false;

        if (novoArmazemId != null) {
            query.append("armazemId = ?");
            adicionarVirgula = true;
        }
        if (novoProdutoId != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("produtoId = ?");
            adicionarVirgula = true;
        }
        if (novaQuantidade != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("quantidade = ?");
        }

        query.append(" WHERE estoqueId = ?");

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int parametroIndex = 1;

            if (novoArmazemId != null) {
                pstmt.setInt(parametroIndex++, novoArmazemId);
            }
            if (novoProdutoId != null) {
                pstmt.setInt(parametroIndex++, novoProdutoId);
            }
            if (novaQuantidade != null) {
                pstmt.setInt(parametroIndex++, novaQuantidade);
            }

            pstmt.setInt(parametroIndex, estoqueId);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Movimentação atualizada com sucesso!");
            } else {
                System.out.println("Movimentação não encontrada ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar movimentação: " + e.getMessage());
        }

        scanner.close();
    }
}

