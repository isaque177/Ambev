package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Vendedores extends Conexao {
    private int id;
    private String nome_vendedor;
    private String email;

    public Vendedores() {
        this.id = id;
        this.nome_vendedor = nome_vendedor;
        this.email = email;
    }
    public void adicionarVendedorViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do vendedor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer

        System.out.print("Digite o nome do vendedor: ");
        String nome_vendedor = scanner.nextLine();

        System.out.print("Digite o email do vendedor: ");
        String email = scanner.nextLine();

        String query = "INSERT INTO Vendedores (id, nome_vendedor, email) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, nome_vendedor);
            pstmt.setString(3, email);

            pstmt.executeUpdate();
            System.out.println("Vendedor adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar vendedor: " + e.getMessage());
        }

        scanner.close();
    }

    public void removerVendedorViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do vendedor que deseja remover: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM Vendedores WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Vendedor removido com sucesso!");
            } else {
                System.out.println("Nenhum vendedor encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover vendedor: " + e.getMessage());
        }

        scanner.close();
    }

    public void atualizarVendedorViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do vendedor que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer

        String novoNome = null;
        String novoEmail = null;

        System.out.print("Deseja alterar o nome do vendedor? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo nome: ");
            novoNome = scanner.nextLine();
        }

        System.out.print("Deseja alterar o email do vendedor? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo email: ");
            novoEmail = scanner.nextLine();
        }

        if (novoNome == null && novoEmail == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE Vendedores SET ");
        boolean precisaVirgula = false;

        if (novoNome != null) {
            query.append("nome_vendedor = ?");
            precisaVirgula = true;
        }
        if (novoEmail != null) {
            if (precisaVirgula) query.append(", ");
            query.append("email = ?");
        }

        query.append(" WHERE id = ?");

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int index = 1;
            if (novoNome != null) pstmt.setString(index++, novoNome);
            if (novoEmail != null) pstmt.setString(index++, novoEmail);
            pstmt.setInt(index, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Vendedor atualizado com sucesso!");
            } else {
                System.out.println("Vendedor não encontrado ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar vendedor: " + e.getMessage());
        }

        scanner.close();
    }

}

