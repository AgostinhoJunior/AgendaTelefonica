/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.dao;

import br.com.agenda.interfaces.DaoI;
import br.com.agenda.model.Telefone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class TelefoneDao extends Dao implements DaoI<Telefone> {

    ContatoDao contatoDao;

    public TelefoneDao() {
        super();
        contatoDao = new ContatoDao();
    }

    @Override
    public int inserir(Telefone telefone) {
        String queryInsert = "INSERT INTO TELEFONE (DDD, NUMERO, FK_CONTATO) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setInt(3, telefone.getContato().getId());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Telefone telefone) {
        String queryUpdate = "UPDATE TELEFONE SET DDD = ?, NUMERO = ?, FK_CONTATO = ?  WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getNumero());
            stmt.setInt(3, telefone.getContato().getId());
            stmt.setInt(4, telefone.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Telefone telefone) {
        String queryDelete = "DELETE FROM TELEFONE WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, telefone.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM TELEFONE WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(Telefone telefone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telefone> pesquisar() {
        String querySelect = "SELECT * FROM TELEFONE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Telefone> lista = new ArrayList<>();
            while (result.next()) {
                Telefone cliente = new Telefone();
                cliente.setId(result.getInt("id"));
                cliente.setDdd(result.getString("ddd"));
                cliente.setNumero(result.getString("numero"));
                cliente.setContato(contatoDao.pesquisar(result.getInt("fk_contato")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Telefone> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM TELEFONE (ddd LIKE ? or numero LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Telefone> lista = new ArrayList<>();
            while (result.next()) {
                Telefone cliente = new Telefone();
                cliente.setId(result.getInt("id"));
                cliente.setDdd(result.getString("ddd"));
                cliente.setNumero(result.getString("numero"));
                cliente.setContato(contatoDao.pesquisar(result.getInt("fk_contato")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Telefone pesquisar(int id) {
        String querySelectPorId = "SELECT * FROM TELEFONE WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Telefone cliente = new Telefone();
                cliente.setId(result.getInt("id"));
                cliente.setDdd(result.getString("ddd"));
                cliente.setNumero(result.getString("numero"));
                cliente.setContato(contatoDao.pesquisar(result.getInt("fk_contato")));
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
