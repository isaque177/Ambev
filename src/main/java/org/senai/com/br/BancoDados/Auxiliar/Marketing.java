package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.*;
import java.util.Scanner;

public class Marketing extends Conexao {
    private int investimento_id;
    private int campanha_id;
    private Double valor;

    // Construtor
    public Marketing() {
        this.investimento_id = investimento_id;
        this.campanha_id = campanha_id;
        this.valor = valor;
    }

    public void adicionarMarketingViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da campanha: ");
        int campanha_id = scanner.nextInt();
        System.out.print("Digite o valor do investimento: ");
        Double valor = scanner.nextDouble();

        String query = "INSERT INTO Marketing (campanha_id, valor) VALUES (?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, campanha_id);
            pstmt.setDouble(2, valor);

            pstmt.executeUpdate();

            System.out.println("Investimento de marketing adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar investimento de marketing: " + e.getMessage());
        }

        scanner.close();
    }
    public void removerMarketingViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do investimento de marketing que deseja remover: ");
        int investimento_id = scanner.nextInt();

        String query = "DELETE FROM Marketing WHERE investimento_id = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, investimento_id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Investimento de marketing removido com sucesso!");
            } else {
                System.out.println("Nenhum investimento de marketing encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover investimento de marketing: " + e.getMessage());
        }

        scanner.close();
    }
    public void atualizarMarketingViaConsole() {
        Scanner scanner = new Scanner(System.in);

        // Pergunta pelo ID do investimento
        System.out.print("Digite o ID do investimento que deseja atualizar: ");
        int investimento_id = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer

        // Pergunta se o usuário quer alterar o ID da campanha
        System.out.print("Deseja alterar o ID da campanha? (s/n): ");
        Integer campanha_id = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo ID da campanha: ");
            campanha_id = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer
        }

        // Pergunta se o usuário quer alterar o valor do investimento
        System.out.print("Deseja alterar o valor do investimento? (s/n): ");
        Double valor = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo valor do investimento: ");
            valor = scanner.nextDouble();
            scanner.nextLine(); // limpar o buffer
        }

        // Construindo a consulta SQL dinamicamente
        StringBuilder query = new StringBuilder("UPDATE Marketing SET ");
        boolean adicionarVirgula = false;

        if (campanha_id != null) {
            query.append("campanha_id = ?");
            adicionarVirgula = true;
        }
        if (valor != null) {
            if (adicionarVirgula) query.append(", ");
            query.append("valor = ?");
        }

        // Caso nenhum campo tenha sido escolhido para atualização
        if (campanha_id == null && valor == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        query.append(" WHERE investimento_id = ?");

        // Executando a consulta SQL
        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int parametroIndex = 1;

            // Atribuindo os valores dos parâmetros na consulta SQL
            if (campanha_id != null) {
                pstmt.setInt(parametroIndex++, campanha_id);
            }
            if (valor != null) {
                pstmt.setDouble(parametroIndex++, valor);
            }

            // Definindo o ID do investimento
            pstmt.setInt(parametroIndex, investimento_id);

            // Executando a atualização no banco de dados
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Investimento de marketing atualizado com sucesso!");
            } else {
                System.out.println("Investimento de marketing não encontrado ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar investimento de marketing: " + e.getMessage());
        }

        scanner.close();
    }
}
