package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUs", allUsers);
        return "allUsers";
    }

    @GetMapping("/new")
    public String getNewUser(Model model) {
        model.addAttribute("user", new User());

        return "newUser";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String getEditUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("year") int year) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setYear(year);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping ("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
