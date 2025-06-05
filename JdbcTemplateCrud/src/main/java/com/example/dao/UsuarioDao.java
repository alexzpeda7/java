package com.example.dao;

import com.example.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UsuarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create: Insert a new user
    public int createUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (login, password) VALUES (?, ?)";
        return jdbcTemplate.update(sql, usuario.getLogin(), usuario.getPassword());
    }

    // Read: Find a user by ID
    public Usuario getUsuarioById(int id) {
        String sql = "SELECT id, login, password FROM usuario WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), id);
    }
    
    public List<Usuario> getUsers() {
        List<Usuario> resultado = new ArrayList<>();
        List<Map<String,Object>> list = 
                jdbcTemplate.queryForList("SELECT id, login, password FROM usuario");
for (Map<String, Object> registro: list) {
    Usuario usuario= new Usuario();
    usuario.setId((int)registro.get("id"));
    usuario.setLogin((String)registro.get("login"));
    usuario.setPassword((String)registro.get("password"));
}
        
        return null;
    }
    

    // Read: Find a user by login
    public Usuario getUsuarioByLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE login = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{login}, new UsuarioRowMapper());
    }

    // Read: Get all users
    public List<Usuario> getAllUsuarios() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }

    // Update: Update a user's login and password
    public int updateUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET login = ?, password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, usuario.getLogin(), usuario.getPassword(), usuario.getId());
    }

    // Delete: Delete a user by ID
    public int deleteUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // RowMapper implementation to map rows from the database to the Usuario object
    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setLogin(rs.getString("login"));
            usuario.setPassword(rs.getString("password"));
            return usuario;
        }
    }
}
