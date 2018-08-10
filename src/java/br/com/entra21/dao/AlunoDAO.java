/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entra21.dao;

import br.com.entra21.bean.AlunoBean;
import br.com.entra21.database.ConexaoBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class AlunoDAO {
    
    public List<AlunoBean> obterTodos() {
        List<AlunoBean> alunos = new ArrayList<>();
        
        String sql = "SELECT * FROM alunos";
        
        try {
            Statement st = ConexaoBanco.obterConexao().createStatement();
            st.execute(sql);
            ResultSet resultSet = st.getResultSet();
            
            while(resultSet.next()) {
                AlunoBean aluno  = new AlunoBean();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCodigo(resultSet.getString("codigo_matricula"));
                aluno.setNota1(resultSet.getFloat("nota_1"));
                aluno.setNota2(resultSet.getFloat("nota_2"));
                aluno.setNota3(resultSet.getFloat("nota_3"));
                aluno.setMedia(resultSet.getFloat("media"));
                aluno.setFrequencia(resultSet.getByte("frequencia"));
                alunos.add(aluno);
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoBanco.fecharConexao();
        }
        return alunos;
    }
   
    public int adicionar(AlunoBean aluno) {
        String sql = "INSERT INTO alunos (nome, codigoMatricula, nota_1, "
                + "nota_2, nota_3, media,frequencia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
        PreparedStatement ps = ConexaoBanco.obterConexao().prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
        
        int quantidade = 1;
        ps.setString(quantidade++, aluno.getNome());
        ps.setString(quantidade++, aluno.getCodigo());
        ps.setFloat(quantidade++, aluno.getNota1());
        ps.setFloat(quantidade++, aluno.getNota2());
        ps.setFloat(quantidade++, aluno.getNota3());
        ps.setFloat(quantidade++, aluno.getMedia());
        ps.setByte(quantidade++, aluno.getFrequencia());
        ps.execute();
        ResultSet resultSet = ps.getGeneratedKeys();
        
        if(resultSet.next()) {
            return resultSet.getInt(1);
        }
        
        }catch (SQLException e) {
        e.printStackTrace();
      }finally {
            ConexaoBanco.fecharConexao();
        }
        
        return -1;
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM alunos WHERE id";
        
        try {
          PreparedStatement ps = ConexaoBanco.obterConexao().prepareStatement(sql);
           ps.setInt(1, id);
           return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoBanco.fecharConexao();
        }
        return false;
    }
    
    public AlunoBean obterPeloId(int id) {
        String sql = "SELECT FROM alunos WHERE id = ?";
        try {
            PreparedStatement ps = ConexaoBanco.obterConexao()
                    .prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            if(resultSet.next()) {
                AlunoBean aluno = new AlunoBean();
                aluno.setId(id);
                aluno.setNome(resultSet.getString("nome"));
                aluno.setCodigo(resultSet.getString("codigo"));
                aluno.setNota1(resultSet.getFloat("nota_1"));
                aluno.setNota2(resultSet.getFloat("nota_2"));
                aluno.setNota3(resultSet.getFloat("nota_3"));
                aluno.setMedia(resultSet.getFloat("media"));
                aluno.setFrequencia(resultSet.getByte("frequencia"));
                return aluno;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConexaoBanco.fecharConexao();
        }
        return null;
    }
    
    public boolean alterar(AlunoBean aluno) {
        String sql = "UPDATE alunos SET nome = ?, codigoMatricula = ?,"
                + "nota_1 = ?, nota_2 = ?, nota_3 = ?,"
                + "media = ?, frequencia = ?"
                + "WHERE id = ?";
        
        try {
            PreparedStatement ps = ConexaoBanco.obterConexao().
                    prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCodigo());
            ps.setFloat(3, aluno.getNota1());
            ps.setFloat(4, aluno.getNota2());
            ps.setFloat(5, aluno.getNota3());
            ps.setFloat(6, aluno.getMedia());
            ps.setByte(7, aluno.getFrequencia());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoBanco.fecharConexao();
        }
        return false;
    }
}
