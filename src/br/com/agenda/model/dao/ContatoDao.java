/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.dao;

import br.com.agenda.interfaces.DaoI;
import br.com.agenda.model.Contato;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class ContatoDao extends Dao implements DaoI<Contato> {

    TipoContatoDao tipoContatoDao;
    TelefoneDao telefoneDao;

    public ContatoDao() {
        super();
        tipoContatoDao = new TipoContatoDao();
        telefoneDao = new TelefoneDao();
    }

    @Override
    public int inserir(Contato contato) {
        String queryInsert = "INSERT INTO CONTATO (NOME, NASCIMENTO, EMAIL, FK_TELEFONE, FK_TIPOCONTATO, ATIVO) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, contato.getNome());
            stmt.setDate(2, new Date(contato.getNascimento().getTime()));
            stmt.setString(3, contato.getEmail());
            stmt.setInt(4, contato.getTelefone().getId());
            stmt.setInt(5, contato.getTipoContato().getId());
            stmt.setBoolean(6, contato.getAtivo());
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
    public boolean alterar(Contato contato) {
        String queryUpdate = "UPDATE CONTATO SET nome = ?, nascimento = ?, email = ?, fk_telefone = ? , fk_tipoContato = ?, ativo = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, contato.getNome());
            stmt.setDate(2, new Date(contato.getNascimento().getTime()));
            stmt.setString(3, contato.getEmail());
            stmt.setInt(4, contato.getTelefone().getId());
            stmt.setInt(5, contato.getTipoContato().getId());
            stmt.setBoolean(6, contato.getAtivo());
            stmt.setInt(7, contato.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Contato contato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Contato contato) {
        String sql = "UPDATE CONTATO SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, contato.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE CONTATO SET ativo = false WHERE id = ?";
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
    public List<Contato> pesquisar() {
        String querySelect = "SELECT * FROM CONTATO WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Contato> lista = new ArrayList<>();
            while (result.next()) {
                Contato contato = new Contato();
                contato.setId(result.getInt("id"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setNascimento(result.getDate("nascimento"));
                contato.setTelefone(telefoneDao.pesquisar(result.getInt("fk_telefone")));
                contato.setTipoContato(tipoContatoDao.pesquisar(result.getInt("fk_tipoContato")));
                contato.setAtivo(result.getBoolean("ativo"));
                lista.add(contato);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Contato> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM CONTATO WHERE (nome LIKE ? or email LIKE ? or fk_telefone LIKE ?) and ativo = true";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Contato> lista = new ArrayList<>();
            while (result.next()) {
                Contato contato = new Contato();
                contato.setId(result.getInt("id"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setNascimento(result.getDate("nascimento"));
                contato.setTelefone(telefoneDao.pesquisar(result.getInt("fk_telefone")));
                contato.setTipoContato(tipoContatoDao.pesquisar(result.getInt("fk_tipoContato")));
                contato.setAtivo(result.getBoolean("ativo"));
                lista.add(contato);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Contato pesquisar(int id) {
        String querySelectPorId = "SELECT * FROM CONTATO WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Contato contato = new Contato();
                contato.setId(result.getInt("id"));
                contato.setNome(result.getString("nome"));
                contato.setEmail(result.getString("email"));
                contato.setNascimento(result.getDate("nascimento"));
                contato.setTelefone(telefoneDao.pesquisar(result.getInt("fk_telefone")));
                contato.setTipoContato(tipoContatoDao.pesquisar(result.getInt("fk_tipoContato")));
                contato.setAtivo(result.getBoolean("ativo"));
                return contato;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
