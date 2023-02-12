
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.connectionFactory;


public class TaskController {
    
    public void save(Task task) {
        String sql = "INSERT INTO task (projectId,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "created_at,"
                + "updated_at) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreated_at().getTime()));
            statement.setDate(8, new Date(task.getUpdated_at().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tarefa " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Task task){
        String sql = "UPDATE task SET "
                + "projectId = ?,"
                + "name = ?,"
                + "description = ?,"
                + "completed = ?,"
                + "notes = ?,"
                + "deadline = ?,"
                + "created_at = ?,"
                + "updated_at = ? "
                + "WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreated_at().getTime()));
            statement.setDate(8, new Date(task.getUpdated_at().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tarefa " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int taskId) throws SQLException{
        String sql = "DELETE FROM task WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tarefa " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        String sql = "SELECT * FROM task WHERE projectId = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<Task>();
        try {
            conn= connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Task task = new Task();
                task.setIdProject(idProject);
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreated_at(resultSet.getDate("created_at"));
                task.setUpdated_at(resultSet.getDate("updated_at"));
                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao resgatar uma lista de tarefas " + e.getMessage());
        } finally {
            connectionFactory.closeConnection(conn, statement, resultSet);
        }
        return tasks;
    }
}
