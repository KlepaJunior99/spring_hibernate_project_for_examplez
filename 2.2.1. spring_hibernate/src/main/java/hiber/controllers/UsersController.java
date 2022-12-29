package hiber.controllers;

import hiber.model.Person;
import hiber.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final PersonService personService;

    @Autowired
    public UsersController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.index());
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.show(id));
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
        personService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")int id) {
        model.addAttribute(personService.show(id));
        return "edit";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("person") @Valid Person user, BindingResult bindingResult, @PathVariable("id")int id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        personService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id) {
        personService.delete(id);
        return "redirect:/users";
    }
}