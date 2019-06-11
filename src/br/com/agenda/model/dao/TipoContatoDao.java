/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.dao;

import br.com.agenda.interfaces.DaoI;
import br.com.agenda.model.TipoContato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class TipoContatoDao extends Dao implements DaoI<TipoContato> {

    public TipoContatoDao() {
        super();
    }

    @Override
    public int inserir(TipoContato tipoContato) {
        String queryInsert = "INSERT INTO TIPOCONTATO(NOME, ATIVO) VALUES(?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoContato.getNome());
            stmt.setBoolean(2, tipoContato.getAtivo());
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
    public boolean alterar(TipoContato tipoContato) {
        String queryUpdate = "UPDATE TIPOCONTATO SET NOME = ?, ATIVO = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, tipoContato.getNome());
            stmt.setBoolean(2, tipoContato.getAtivo());
            stmt.setInt(3, tipoContato.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(TipoContato tipoContato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(TipoContato tipoContato) {
        String sql = "UPDATE TIPOCONTATO SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tipoContato.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE TIPOCONTATO SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<TipoContato> pesquisar() {
        String querySelect = "SELECT * FROM TIPOCONTATO WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<TipoContato> lista = new ArrayList<>();
            while (result.next()) {
                TipoContato tipoContato = new TipoContato();
                tipoContato.setId(result.getInt("id"));
                tipoContato.setNome(result.getString("nome"));
                tipoContato.setAtivo(result.getBoolean("ativo"));
                lista.add(tipoContato);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<TipoContato> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM TIPOCONTATO WHERE ATIVO = TRUE AND NOME LIKE ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<TipoContato> lista = new ArrayList<>();
            while (result.next()) {
                TipoContato tipoContato = new TipoContato();
                tipoContato.setId(result.getInt("id"));
                tipoContato.setNome(result.getString("nome"));
                tipoContato.setAtivo(result.getBoolean("ativo"));
                lista.add(tipoContato);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public TipoContato pesquisar(int id) {
        String querySelectPorId = "SELECT * FROM TIPOCONTATO WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                TipoContato tipoContato = new TipoContato();
                tipoContato.setId(result.getInt("id"));
                tipoContato.setNome(result.getString("nome"));
                tipoContato.setAtivo(result.getBoolean("ativo"));
                return tipoContato;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
