package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Fornecedor;
import br.ufscar.dc.dsw.domain.Produto;

public class ProdutoDAO extends GenericDAO {

    public void insert(Produto produto) {

        String sql = "INSERT INTO Produto (titulo, categoria, quantidade, preco, fornecedor_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, produto.getTitulo());
            statement.setString(2, produto.getCategoria());
            statement.setInt(3, produto.getQuantidade());
            statement.setFloat(4, produto.getPreco());
            statement.setLong(5, produto.getFornecedor().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> getAll() {

        List<Produto> listaProdutos = new ArrayList<>();

        String sql = "SELECT * from Produto p, Fornecedor f where p.FORNECEDOR_ID = f.ID order by p.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("titulo");
                String categoria = resultSet.getString("categoria");
                int quantidade = resultSet.getInt("quantidade");
                float preco = resultSet.getFloat("preco");
                Long fornecedor_id = resultSet.getLong(6);
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Fornecedor fornecedor = new Fornecedor(fornecedor_id, cnpj, nome);
                Produto produto = new Produto(id, titulo, categoria, quantidade, preco, fornecedor);
                listaProdutos.add(produto);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProdutos;
    }

    public void delete(Produto produto) {
        String sql = "DELETE FROM Produto where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, produto.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Produto produto) {
        String sql = "UPDATE Produto SET titulo = ?, categoria = ?, quantidade = ?, preco = ?";
        sql += ", fornecedor_id = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, produto.getTitulo());
            statement.setString(2, produto.getCategoria());
            statement.setInt(3, produto.getQuantidade());
            statement.setFloat(4, produto.getPreco());
            statement.setFloat(5, produto.getFornecedor().getId());
            statement.setLong(6, produto.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto get(Long id) {
        Produto produto = null;

        String sql = "SELECT * from Produto p, Fornecedor f where p.id = ? and p.FORNECEDOR_ID = f.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String categoria = resultSet.getString("categoria");
                int quantidade = resultSet.getInt("quantidade");
                float preco = resultSet.getFloat("preco");

                Long fornecedorID = resultSet.getLong("fornecedor_id");
                Fornecedor fornecedor = new FornecedorDAO().get(fornecedorID);

                produto = new Produto(id, titulo, categoria, quantidade, preco, fornecedor);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produto;
    }

    public int countByFornecedor(Long id) {
        int contador = 0;
        
        String sql = "SELECT count(*) from Produto p where p.FORNECEDOR_ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contador = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
    
    
}