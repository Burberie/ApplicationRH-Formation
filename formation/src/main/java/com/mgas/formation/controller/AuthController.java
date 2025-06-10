package com.mgas.formation.controller;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.mapper.UserMapper;
import com.mgas.formation.model.User;
import com.mgas.formation.repository.UserRepository;
import com.mgas.formation.service.JWTService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    private JWTService jwtService;

    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String getToken(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        DBUser userDB = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("Invalid"));
        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())) {
            return jwtService.generateToken(user.getUsername()); // Return JWT
        }
        throw new RuntimeException("Invalid credentials");
    }

    @ValidateOnExecution
    @PostMapping(value = "/register")
    public User register(@Valid @RequestBody User user) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserMapper userMapper = new UserMapper();
        DBUser userDB = userMapper.userToDBUser(user);
        return userMapper.dbUserToUser(userRepository.save(userDB));
    }

    /*private static final Logger logger = getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public LoginController(OAuth2AuthorizedClientService authorizedClientService) { this.authorizedClientService = authorizedClientService; }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        DBUser user = new DBUser();
        model.addAttribute("user", user);
        logger.info("register page");
        return "register";
    }
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") DBUser user,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model){
        if(result.hasErrors()){
            logger.error("register form contains errors");
            model.addAttribute("user", user);
            return "/register";
        }

        DBUser isUserAlreadyRegistered = userService.findCurrentUser();
        //isUserAlreadyRegistered should be null
        if(isUserAlreadyRegistered != null && isUserAlreadyRegistered.getEmail() != null && !isUserAlreadyRegistered.getEmail().isEmpty()){
            logger.error("user already exists");
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        logger.info("user successfully registered");
        customUserDetailsService.saveUserAccount(user);
        customUserDetailsService.authenticateUser(user);
        return "redirect:/transfers";
    }

    @GetMapping("/collaborator")
    public String getCollaborator() {
        return "Welcome, Collaborator";
    }

    @GetMapping("/manager")
    public String getManager() {
        return "Welcome, Manager";
    }

    @GetMapping("/trainer")
    public String getTrainer() {
        return "Welcome, Trainer";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome, Admin";
    }

    @GetMapping("/system")
    public String getSystem() {
        return "Welcome, System";
    }

    @GetMapping("/*")
    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUsernamePasswordLoginInfo(user)) ;
        } else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOauth2LoginInfo(user));
        }
        return userInfo.toString();
    }
    private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
        StringBuffer usernameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            usernameInfo.append("Welcome, " + u.getUsername());
        }
        else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }
    private StringBuffer getOauth2LoginInfo(Principal user){

        StringBuffer protectedInfo = new StringBuffer();

        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
        if(authToken.isAuthenticated()){

            Map<String,Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();

            String userToken = authClient.getAccessToken().getTokenValue();
            protectedInfo.append("Welcome, " + userAttributes.get("name")+"<br><br>");
            protectedInfo.append("e-mail: " + userAttributes.get("email")+"<br><br>");
            protectedInfo.append("Access Token: " + userToken+"<br><br>");
        }
        else{
            protectedInfo.append("NA");
        }
        return protectedInfo;
    }*/
}