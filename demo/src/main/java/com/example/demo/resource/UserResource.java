package com.example.demo.resource;

import com.example.demo.Response.ApiResponse;
import com.example.demo.Response.TokenResponse;
import com.example.demo.Utils.JwtUtil;
import com.example.demo.domain.User;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserResource {
    private UserService userService;
    private JwtUtil jwtUtil;



    @GetMapping("/test")
    public ResponseEntity<ApiResponse> getTest() {
        String test = userService.getText();
        return ResponseEntity.ok(new ApiResponse("success", test));
    }

    @PostMapping("/sign-up")

    public ResponseEntity<ApiResponse> addUser(@RequestBody User user) {
        UserResponseDTO user1 = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("success", user1));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> signin(@RequestBody User user) {

        try {
            String text = userService.signin(user);
            return ResponseEntity.ok(new TokenResponse("success", text));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TokenResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/updatepassword")
    public ResponseEntity<ApiResponse> updatePassword(@RequestBody User user) {
        try {
            userService.updateUserPassword(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Password updated successfull", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/updatedetails")
    private ResponseEntity<ApiResponse> updateDetails(@RequestBody User user) {
        try {
            userService.updateDetails(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Details updated successfull", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        return ResponseEntity.ok().body(new ApiResponse("Success", user));
    }

    @GetMapping("/generatetoken")
    public CsrfToken getToken(HttpServletRequest request){
        return userService.generateToken(request);

    }
}
