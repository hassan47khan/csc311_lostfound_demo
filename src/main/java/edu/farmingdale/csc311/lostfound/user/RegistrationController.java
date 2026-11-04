package edu.farmingdale.csc311.lostfound.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    static final int MIN_USERNAME_LENGTH = 3;
    static final int MIN_PASSWORD_LENGTH = 6;

    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String password,
                           Model model) {
        String name = username.trim();

        String error = validate(name, password);
        if (error != null) {
            return fail(model, name, error);
        }

        try {
            users.save(new AppUser(name, passwordEncoder.encode(password)));
        } catch (DataIntegrityViolationException e) {
            // two people registered the same name at the same moment
            return fail(model, name, "That username is already taken.");
        }
        return "redirect:/login?registered";
    }

    private String validate(String username, String password) {
        if (username.length() < MIN_USERNAME_LENGTH) {
            return "Username must be at least " + MIN_USERNAME_LENGTH + " characters.";
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters.";
        }
        if (users.existsByUsername(username)) {
            return "That username is already taken.";
        }
        return null;
    }

    private String fail(Model model, String username, String error) {
        model.addAttribute("error", error);
        model.addAttribute("username", username);
        return "register";
    }
}
