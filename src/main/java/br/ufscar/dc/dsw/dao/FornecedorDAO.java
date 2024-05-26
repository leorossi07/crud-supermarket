package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Fornecedor;

public class FornecedorDAO extends GenericDAO {

    public void insert(Fornecedor fornecedor) {

        String sql = "INSERT INTO Fornecedor (cnpj, nome) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, fornecedor.getCNPJ());
            statement.setString(2, fornecedor.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Fornecedor> getAll() {

        List<Fornecedor> listaFornecedores = new ArrayList<>();

        String sql = "SELECT * from Fornecedor";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Fornecedor fornecedor = new Fornecedor(id, cnpj, nome);
                fornecedor.setQtdeProdutos(new ProdutoDAO().countByFornecedor(id));
                listaFornecedores.add(fornecedor);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFornecedores;
    }

    public void delete(Fornecedor fornecedor) {
        String sql = "DELETE FROM Fornecedor where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, fornecedor.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE Fornecedor SET cnpj = ?, nome = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, fornecedor.getCNPJ());
            statement.setString(2, fornecedor.getNome());
            statement.setLong(3, fornecedor.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Fornecedor get(Long id) {
        Fornecedor fornecedor = null;
        
        String sql = "SELECT * from Fornecedor where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                fornecedor = new Fornecedor(id, cnpj, nome);
                fornecedor.setQtdeProdutos(new ProdutoDAO().countByFornecedor(id));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fornecedor;
    }
}