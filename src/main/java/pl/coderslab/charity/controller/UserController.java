package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.security.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserController(UserService userService,
                          UserRepository userRepository,
                          RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String getEditForm(Model model, @PathVariable long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(Exception::new);
            model.addAttribute("user", user);
            return "user/update";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public RedirectView update(@ModelAttribute User user, @PathVariable long id) {
        try {
            User oldUser = userRepository.findById(id).orElseThrow(Exception::new);
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setPosition(user.getPosition());
            oldUser.setEmail(user.getEmail());
            userRepository.save(oldUser);
            return new RedirectView("/user/home");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public RedirectView delete(@PathVariable long id, HttpServletRequest request) throws Exception {
        try {
            User user = userRepository.findById(id).orElseThrow(Exception::new);
            userRepository.delete(user);
            request.getSession(true).invalidate();
            return new RedirectView("/home");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ModelAttribute("roles")
    public List<Role> roles() { return this.roleRepository.findAll(); }


}