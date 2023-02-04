
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.connectionFactory;


public class ProjectController {
         
    public void save(Project project){
        String sql = "INSERT INTO projects (name, "
                + "description, "
                + "created_at, "
                + "updated_at) "
                + "VALUES (?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreated_at().getTime()));
            statement.setDate(4, new Date(project.getUpdated_at().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar projeto " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Project project){

        String sql = "UPDATE projects SET "
                + "name = ?,"
                + "description = ?,"
                + "created_at = ?,"
                + "updated_at = ? "
                + "WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreated_at().getTime()));
            statement.setDate(4, new Date(project.getUpdated_at().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar projeto " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar projeto " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public Project getOneById(String name){
        String sql = "SELECT * FROM projects WHERE name LIKE ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Project project = null;
        
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreated_at(resultSet.getDate("created_at"));
                project.setUpdated_at(resultSet.getDate("updated_at"));                
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao resgatar um projeto " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement, resultSet);
        }
        return project;
    }
    
    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreated_at(resultSet.getDate("created_at"));
                project.setUpdated_at(resultSet.getDate("updated_at"));
                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao resgatar uma lista de tarefas " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement, resultSet);
        }
        
        return  projects;
    }
}
