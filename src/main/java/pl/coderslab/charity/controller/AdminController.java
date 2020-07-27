package pl.coderslab.charity.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.security.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public AdminController(UserService userService,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //
    @GetMapping(value = "/all")
    public String getAll(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/userAdd";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/userAdd";
        }
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @RequestMapping(value = "editForm/{id}", method = RequestMethod.GET)
    public String getEditForm(Model model, @PathVariable long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(Exception::new);
            model.addAttribute("user", user);
            return "admin/userUpdate";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "editForm/{id}", method = RequestMethod.POST)
    public RedirectView update(@ModelAttribute User user, @PathVariable long id) {
        try {
            User oldUser = userRepository.findById(id).orElseThrow(Exception::new);
            oldUser.setFirstName(user.getFirstName());
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setPosition(user.getPosition());
            oldUser.setEmail(user.getEmail());
            oldUser.setRoles(user.getRoles());
            userRepository.save(oldUser);
            return new RedirectView("/admin/all");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public RedirectView delete(@PathVariable long id) throws Exception {
        try {
            User user = userRepository.findById(id).orElseThrow(Exception::new);
            userRepository.delete(user);
            return new RedirectView("/admin/all");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ModelAttribute("roles")
    public List<Role> roles() { return this.roleRepository.findAll(); }


}