package hiber.controllers;

import hiber.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import hiber.dao.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final PersonDAO userDAO;

    @Autowired
    public UsersController(PersonDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userDAO.index());
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", userDAO.show(id));
        return "show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userDAO.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")int id) {
        model.addAttribute(userDAO.show(id));
        return "edit";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("person") @Valid Person user, BindingResult bindingResult, @PathVariable("id")int id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userDAO.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}