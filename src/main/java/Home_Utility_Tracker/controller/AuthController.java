package Home_Utility_Tracker.controller;

import Home_Utility_Tracker.model.User;
import Home_Utility_Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  Model model) {
        boolean hasError = false;

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("emailError", "Email already registered.");
            hasError = true;
        }

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordMatchError", "Passwords do not match.");
            hasError = true;
        }

        if (user.getMobile() != null && (!user.getMobile().matches("\\d{10}"))) {
            model.addAttribute("mobileNumberError", "Invalid mobile number. Must be 10 digits.");
            hasError = true;
        }

        if (hasError){
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}
