package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAdminPage(Model model) {
        User user = new User();
        User authorizedUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());
        model.addAttribute("authUser", authorizedUser);
        return "admin";
    }




//    @PostMapping
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.addUser(user);
//        return "redirect:/admin";
//    }
//
//    @PutMapping("/{id}")
//    public String updateUser(@ModelAttribute("user") User user, @PathVariable long id) {
//        userService.updateUser(user, id);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }

    @GetMapping("/sample")
    public String getSamplePage() {
        return "sample";
    }
}
