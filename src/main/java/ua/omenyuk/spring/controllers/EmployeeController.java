package ua.omenyuk.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.omenyuk.spring.dao.EmployeeDAO;
import ua.omenyuk.spring.models.Employee;

import javax.validation.Valid;

@Controller
@RequestMapping("/editing")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("editing", employeeDAO.index());
        return "editing/index";
    }

    @GetMapping("/{employee_id}")
    public String show(@PathVariable("employee_id") int employee_id, Model model){
        // Отримаємо людину по id з DAO та передамо у відображення
        model.addAttribute("employee", employeeDAO.show(employee_id));
        return "editing/show";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee){
        return "editing/new";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editing/new";
        }
        employeeDAO.save(employee);
        return "redirect:/editing";
    }

    @GetMapping("/{employee_id}/edit")
    public String edit(Model model, @PathVariable("employee_id") int id){
        model.addAttribute("employee", employeeDAO.show(id));
        return "editing/edit";
    }

    @PatchMapping("/{employee_id}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("employee_id")int id){


        if(bindingResult.hasErrors()){
            return "editing/new";
        }
        employeeDAO.update(id, employee);
        return "redirect:/editing";
    }

    @DeleteMapping("/{employee_id}")
    public String delete(@PathVariable("employee_id")int id){
        employeeDAO.delete(id);
        return "redirect:/editing";
    }
}
