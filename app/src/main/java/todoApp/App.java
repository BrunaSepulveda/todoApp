/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package todoApp;

import controller.ProjectController;
import java.util.Date;
import model.Project;

public class App {

    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();    
        Project project = new Project();
            project.setName("Name test");
            project.setDescription("desc test");
            project.setUpdated_at(new Date());
         projectController.save(project);
    }
}
