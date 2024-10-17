package cr.go.mep.gradingsystem.controller;

import cr.go.mep.gradingsystem.config.auth.TokenProvider;
import cr.go.mep.gradingsystem.dto.SignInRequest;
import cr.go.mep.gradingsystem.dto.SignInTokenResponse;
import cr.go.mep.gradingsystem.dto.SignUpRequest;
import cr.go.mep.gradingsystem.model.User;
import cr.go.mep.gradingsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        this.authService.signUp(signUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        signInRequest.username(),
                        signInRequest.password()
                );

        try {
            Authentication authUser = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            String accessToken = this.tokenService.generateAccessToken((User) authUser.getPrincipal());

            return ResponseEntity.ok(new SignInTokenResponse(accessToken));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
