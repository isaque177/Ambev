package org.senai.com.br.BancoDados.Auxiliar;

import org.senai.com.br.BancoDados.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class Vendas extends Conexao {

    public class Venda {
        private int vendaId;
        private int pedidoId;
        private int vendedorId;
        private int produtoId;
        private int clienteId;
        private int quantidade;
        private int precoUnitario;
        private double desconto;
        private int valorTotal;
        private Date datas;


        public Venda() {
            this.vendaId = vendaId;
            this.pedidoId = pedidoId;
            this.vendedorId = vendedorId;
            this.produtoId = produtoId;
            this.clienteId = clienteId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.desconto = desconto;
            this.valorTotal = valorTotal;
            this.datas = datas;
        }


        public int getVendaId() { return vendaId; }
        public void setVendaId(int vendaId) { this.vendaId = vendaId; }

        public int getPedidoId() { return pedidoId; }
        public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }

        public int getVendedorId() { return vendedorId; }
        public void setVendedorId(int vendedorId) { this.vendedorId = vendedorId; }

        public int getProdutoId() { return produtoId; }
        public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

        public int getClienteId() { return clienteId; }
        public void setClienteId(int clienteId) { this.clienteId = clienteId; }

        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

        public int getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(int precoUnitario) { this.precoUnitario = precoUnitario; }

        public double getDesconto() { return desconto; }
        public void setDesconto(double desconto) { this.desconto = desconto; }

        public int getValorTotal() { return valorTotal; }
        public void setValorTotal(int valorTotal) { this.valorTotal = valorTotal; }

        public Date getDatas() { return datas; }
        public void setDatas(Date datas) { this.datas = datas; }
    }
    public void adicionarVendaViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da venda: ");
        int vendaId = scanner.nextInt();

        System.out.print("Digite o ID do pedido: ");
        int pedidoId = scanner.nextInt();

        System.out.print("Digite o ID do vendedor: ");
        int vendedorId = scanner.nextInt();

        System.out.print("Digite o ID do produto: ");
        int produtoId = scanner.nextInt();

        System.out.print("Digite o ID do cliente: ");
        int clienteId = scanner.nextInt();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o preço unitário: ");
        int precoUnitario = scanner.nextInt();

        System.out.print("Digite o desconto: ");
        double desconto = scanner.nextDouble();

        System.out.print("Digite o valor total: ");
        int valorTotal = scanner.nextInt();

        System.out.print("Digite a data (yyyy-mm-dd): ");
        scanner.nextLine(); // limpa buffer
        Date datas = Date.valueOf(scanner.nextLine());

        String query = "INSERT INTO Vendas (vendaId, pedidoId, vendedorId, produtoId, clienteId, quantidade, precoUnitario, desconto, valorTotal, datas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, vendaId);
            pstmt.setInt(2, pedidoId);
            pstmt.setInt(3, vendedorId);
            pstmt.setInt(4, produtoId);
            pstmt.setInt(5, clienteId);
            pstmt.setInt(6, quantidade);
            pstmt.setInt(7, precoUnitario);
            pstmt.setDouble(8, desconto);
            pstmt.setInt(9, valorTotal);
            pstmt.setDate(10, datas);

            pstmt.executeUpdate();
            System.out.println("Venda adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar venda: " + e.getMessage());
        }

        scanner.close();
    }

    public void removerVendaViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da venda que deseja remover: ");
        int vendaId = scanner.nextInt();

        String query = "DELETE FROM Vendas WHERE vendaId = ?";

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query)) {

            pstmt.setInt(1, vendaId);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Venda removida com sucesso!");
            } else {
                System.out.println("Nenhuma venda encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover venda: " + e.getMessage());
        }

        scanner.close();
    }

    public void atualizarVendaViaConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID da venda que deseja atualizar: ");
        int vendaId = scanner.nextInt();
        scanner.nextLine();

        Integer novaQuantidade = null;
        Integer novoPrecoUnitario = null;
        Double novoDesconto = null;
        Integer novoValorTotal = null;
        Date novaData = null;

        System.out.print("Deseja alterar a quantidade? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova quantidade: ");
            novaQuantidade = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o preço unitário? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo preço unitário: ");
            novoPrecoUnitario = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o desconto? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo desconto: ");
            novoDesconto = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar o valor total? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo valor total: ");
            novoValorTotal = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Deseja alterar a data? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a nova data (yyyy-mm-dd): ");
            novaData = Date.valueOf(scanner.nextLine());
        }

        if (novaQuantidade == null && novoPrecoUnitario == null && novoDesconto == null && novoValorTotal == null && novaData == null) {
            System.out.println("Nenhuma alteração foi feita.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE Vendas SET ");
        boolean precisaVirgula = false;

        if (novaQuantidade != null) {
            query.append("quantidade = ?");
            precisaVirgula = true;
        }
        if (novoPrecoUnitario != null) {
            if (precisaVirgula) query.append(", ");
            query.append("precoUnitario = ?");
            precisaVirgula = true;
        }
        if (novoDesconto != null) {
            if (precisaVirgula) query.append(", ");
            query.append("desconto = ?");
            precisaVirgula = true;
        }
        if (novoValorTotal != null) {
            if (precisaVirgula) query.append(", ");
            query.append("valorTotal = ?");
            precisaVirgula = true;
        }
        if (novaData != null) {
            if (precisaVirgula) query.append(", ");
            query.append("datas = ?");
        }

        query.append(" WHERE vendaId = ?");

        try (Connection conexao = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conexao.prepareStatement(query.toString())) {

            int index = 1;

            if (novaQuantidade != null) pstmt.setInt(index++, novaQuantidade);
            if (novoPrecoUnitario != null) pstmt.setInt(index++, novoPrecoUnitario);
            if (novoDesconto != null) pstmt.setDouble(index++, novoDesconto);
            if (novoValorTotal != null) pstmt.setInt(index++, novoValorTotal);
            if (novaData != null) pstmt.setDate(index++, (java.sql.Date) novaData);

            pstmt.setInt(index, vendaId);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Venda atualizada com sucesso!");
            } else {
                System.out.println("Venda não encontrada ou nada foi alterado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar venda: " + e.getMessage());
        }

        scanner.close();
    }

}
