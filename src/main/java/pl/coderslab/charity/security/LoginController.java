package pl.coderslab.charity.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.domain.CurrentUser;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserService userService;
    private final UserRepository userRepository;

    public LoginController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public String logoutGet(){
        //System.out.println("logout user page shown--------------------");
        return "logout";
    }
    @RequestMapping(value = "/logout" , method = RequestMethod.POST)
    public String logoutPost(ModelMap model, HttpServletRequest request){
        request.getSession(true).invalidate();
        //System.out.println("logout user page shown--------------------");
        return "redirect:/";
    }
    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String getViewUserHome(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User newCurrentUser = userRepository.findByUsername(customUser.getUser().getUsername());
        model.addAttribute("currentUser", newCurrentUser);

        return "form";
    }
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String getViewAdminHome(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("currentUser", customUser.getUser());

        return "admin/home";
    }
    //@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    //@ResponseBody
    //public String admin(@AuthenticationPrincipal CurrentUser customUser) {
    //    User entityUser = customUser.getUser();
    //    return "Hello " + entityUser.getUsername();
    //}

}