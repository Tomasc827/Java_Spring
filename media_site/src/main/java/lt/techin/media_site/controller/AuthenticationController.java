package lt.techin.media_site.controller;


import lt.techin.media_site.dto.user.UserLoginDTO;
import lt.techin.media_site.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(JWTUtil jwtUtil,AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto) {
  try {
      Authentication authentication =authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(dto.email(),dto.password())
      );
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String token = jwtUtil.generateToken(
              userDetails.getUsername(),
              userDetails.getAuthorities().stream()
                      .map(GrantedAuthority::getAuthority)
                      .toList()
      );
      return ResponseEntity.ok(Map.of("token",token));
  } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
              .body(Map.of("error","Invalid username or password"));
  }
  }


}
