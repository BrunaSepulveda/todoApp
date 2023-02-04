/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Task {
    private int id;
    private int idProject;
    private String name;
    private String notes;
    private String description;
    private boolean completed;
    private Date deadline;
    private Date created_at;
    private Date updated_at;

    public Task(int id, int idProject, String name, String notes, String description, boolean completed, Date deadline, Date created_at, Date updated_at) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.notes = notes;
        this.description = description;
        this.completed = completed;
        this.deadline = deadline;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    public Task(){
        this.created_at = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", notes=" + notes + ", description=" + description + ", completed=" + completed + ", deadline=" + deadline + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
