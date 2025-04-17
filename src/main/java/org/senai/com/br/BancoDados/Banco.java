package org.senai.com.br.BancoDados;

import org.senai.com.br.BancoDados.Auxiliar.*;
import org.senai.com.br.BancoDados.Menu.Menu_Principal;
import org.senai.com.br.BancoDados.Tabelas.*;

import java.sql.Date;
import java.sql.SQLException;

public class Banco {

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();

        Menu_Principal menu = new Menu_Principal();
        menu.menuPrincipal();

        Tabel_Cliente cliente = new Tabel_Cliente();
        Tabel_Produto produto = new Tabel_Produto();
        Tabel_Pedidos pedidos = new Tabel_Pedidos();
        Tabel_Campanhas campanha = new Tabel_Campanhas();
        Tabel_Promocao promocao = new Tabel_Promocao();
        Tabel_Estoque estoque = new Tabel_Estoque();
        Tabel_Entregas entrega = new Tabel_Entregas();
        Tabel_Vendas vendas = new Tabel_Vendas();
        Tabel_Vendedores vendedores = new Tabel_Vendedores();
        Tabel_Marketinginvestimentos tabelMarketinginvestimentos = new Tabel_Marketinginvestimentos();
        Tabel_Transportadoras transportadoras = new Tabel_Transportadoras();
        Tabel_Armazens armazens = new Tabel_Armazens();
        Tabel_MovimentacaoEstoque movimentacaoEstoque = new Tabel_MovimentacaoEstoque();

        Entregas entrega1 = new Entregas();
        MovimentacaoEstoque movimentacaoEstoque1 = new MovimentacaoEstoque();

        Produtos produtos = new Produtos();
        Clientes clientes1 = new Clientes();
        Estoque estoque1 = new Estoque();
        Marketing marketing1 = new Marketing();
        Pedidos pedidos1 = new Pedidos();
        Promocoes promocoes1 = new Promocoes();
        Vendedores vendedores1 = new Vendedores();
        Vendas vendas1 = new Vendas();;

        conexao.Verif_banco();

      /*  cliente.Tabela_Cliente();
        produto.Tabela_Produto();
        pedidos.Tabela_Pedidos();
        vendedores.Tabela_vendedores();
        vendas.Tabela_Vendas();
        campanha.Tabela_Campanha();
        promocao.Tabela_Promocoes();
        tabelMarketinginvestimentos.Tabela_Marketinginvestimentos();
        transportadoras.Tabela_Transportadoras();
        armazens.Tabela_Armazens();
        estoque.Tabela_Estoque();
        movimentacaoEstoque.Tabela_movimetancaoestoque();
        entrega.Tabela_Entregas(); */

   /*produtos.RemoverProdutoViaConsole();
        produtos.adicionarProdutoViaConsole();
        produtos.upatualizarProdutoViaConsole();

        clientes1.adicionarClienteViaConsole();
        clientes1.removerClienteViaConsole();
        clientes1.atualizarClienteViaConsole();

        estoque1.adicionarEstoqueViaConsole();
        estoque1.atualizarEstoqueViaConsole();
        estoque1.removerEstoqueViaConsole();

        marketing1.adicionarMarketingViaConsole();
        marketing1.removerMarketingViaConsole();
        marketing1.atualizarMarketingViaConsole();

        movimentacaoEstoque1.adicionarMovimentacaoViaConsole();
        movimentacaoEstoque1.removerMovimentacaoViaConsole();
        movimentacaoEstoque1.atualizarMovimentacaoViaConsole();

        pedidos1.adicionarPedidoViaConsole();
        pedidos1.removerPedidoViaConsole();
        pedidos1.atualizarPedidoViaConsole();

        promocoes1.adicionarPromocaoViaConsole();
        promocoes1.removerPromocaoViaConsole();
        promocoes1.atualizarPromocaoViaConsole();

        vendedores1.adicionarVendedorViaConsole();
        vendedores1.removerVendedorViaConsole();
        vendedores1.atualizarVendedorViaConsole();

        vendas1.adicionarVendaViaConsole();
        vendas1.removerVendaViaConsole();
        vendas1.atualizarVendaViaConsole();*/


    }
}
