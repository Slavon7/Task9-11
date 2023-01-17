package ua.omenyuk.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.omenyuk.spring.dao.ProjectDAO;
import ua.omenyuk.spring.models.Project;

import javax.validation.Valid;

@Controller
@RequestMapping("/showProject")
public class ProjectController {
    private final ProjectDAO projectDAO;

    @Autowired
    public ProjectController(ProjectDAO projectDAO){
        this.projectDAO = projectDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("showProject", projectDAO.indexProject());
        return "editing/showProject";
    }

    @GetMapping("/{project_id}")
    public String showProject(@PathVariable("project_id") int project_id, Model model){
        model.addAttribute("project", projectDAO.showProject(project_id));
        return "editing/showProject";
    }
}
