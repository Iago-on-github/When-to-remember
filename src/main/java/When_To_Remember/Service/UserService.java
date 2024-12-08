package When_To_Remember.Service;

import When_To_Remember.Models.Dtos.UserDto;
import When_To_Remember.Models.Matter;
import When_To_Remember.Models.User;
import When_To_Remember.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getMatters())).toList();
    }

    public UserDto createUser(UserDto dto) {
        User user = new User();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        userRepository.save(user);

        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getMatters());
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
