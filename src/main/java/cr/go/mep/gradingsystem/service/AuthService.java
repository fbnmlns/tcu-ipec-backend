package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.dto.SignUpRequest;
import cr.go.mep.gradingsystem.enums.UserRole;
import cr.go.mep.gradingsystem.exception.InvalidJwtException;
import cr.go.mep.gradingsystem.model.Administrator;
import cr.go.mep.gradingsystem.model.Instructor;
import cr.go.mep.gradingsystem.model.Student;
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
        UserDetails user = this.userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public UserDetails signUp(SignUpRequest signUpRequest) {
        if (this.userRepository.findUserByUsername(signUpRequest.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(signUpRequest.password());

        User newUser = getUserByRoleType(signUpRequest.role());

        newUser.setName(signUpRequest.name());
        newUser.setLastName(signUpRequest.lastName());
        newUser.setEmail(signUpRequest.email());
        newUser.setUsername(signUpRequest.username());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(signUpRequest.role());

        return this.userRepository.save(newUser);
    }

    private User getUserByRoleType(UserRole role) {
        if (role == UserRole.ADMINISTRATOR) {
            return new Administrator();
        }

        if (role == UserRole.INSTRUCTOR) {
            return new Instructor();
        }

        if (role == UserRole.STUDENT) {
            return new Student();
        }

        return new User();
    }
}
