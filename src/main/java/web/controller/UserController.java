package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }


    @GetMapping(value = "/user_add_form")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user_add_form";
    }

    @GetMapping(value = "/user_edit_form")
    public String editUser(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user_edit_form";
    }

    @PostMapping(value = "/createUser")
    public String add(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @PostMapping(value = "/updateUser")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("userId") Long userId) {
        userService.deleteById(userId);
        return "redirect:/";
    }

}
