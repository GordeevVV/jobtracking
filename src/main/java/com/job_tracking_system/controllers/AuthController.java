package com.job_tracking_system.controllers;

import com.job_tracking_system.entity.ERole;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.payload.request.LoginRequest;
import com.job_tracking_system.payload.request.SignupRequest;
import com.job_tracking_system.payload.responce.MessageResponse;
import com.job_tracking_system.payload.responce.UserInfoResponse;
import com.job_tracking_system.repository.PersonJpaRepository;
import com.job_tracking_system.security.jwt.JwtUtils;
import com.job_tracking_system.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonJpaRepository personJpaRepository;


    @Qualifier("passwordEncoder")
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (personJpaRepository.existsByLogin(signUpRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        Person person = new Person(signUpRequest.getLogin(),
                encoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();
        ERole role;

        if (strRole == null) {
            role = ERole.ROLE_IMPLEMENTER;
        } else {
            if ("manager".equals(strRole)) {
                role = ERole.ROLE_MANAGER;
            } else {
                if ("implementer".equals(strRole)) {
                    role = ERole.ROLE_IMPLEMENTER;
                }
                else throw new RuntimeException("Error: Role is not found.");
            }
        }

        person.setRole(role);
        personJpaRepository.save(person);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}