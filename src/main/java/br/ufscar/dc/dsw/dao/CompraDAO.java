package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Produto;
import br.ufscar.dc.dsw.domain.Usuario;

public class CompraDAO extends GenericDAO {

    public void insert(Compra compra) {

        String sql = "INSERT INTO Compra (data, valor, produto_id, usuario_id) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, compra.getData());
            statement.setFloat(2, compra.getValor());
            statement.setLong(3, compra.getProduto().getId());
            statement.setLong(4, compra.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compra> getAll(Usuario usuario) {

        List<Compra> listaCompras = new ArrayList<>();

        String sql = "SELECT * from Compra c where c.USUARIO_ID = ? order by c.ID";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String data = resultSet.getString("data");
                Float valor = resultSet.getFloat("valor");
                Long produtoId = resultSet.getLong("produto_id");
                Produto produto = new ProdutoDAO().get(produtoId);            
                Compra compra = new Compra(id, data, valor, produto, usuario);
                listaCompras.add(compra);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCompras;
    }
}