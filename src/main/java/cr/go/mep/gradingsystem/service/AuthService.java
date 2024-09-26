package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.dto.SignUpRequest;
import cr.go.mep.gradingsystem.exception.InvalidJwtException;
import cr.go.mep.gradingsystem.model.User;
import cr.go.mep.gradingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username);
    }

    public UserDetails signUp(SignUpRequest signUpRequest) {
        if (this.userRepository.findUserByUsername(signUpRequest.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(signUpRequest.password());

        User newUser = new User(
                signUpRequest.name(),
                signUpRequest.lastName(),
                signUpRequest.email(),
                signUpRequest.username(),
                encryptedPassword,
                signUpRequest.role());

        return this.userRepository.save(newUser);
    }
}

