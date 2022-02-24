package africa.semicolon.movie.project.service.User;

import africa.semicolon.movie.project.data.dtos.UserDto;
import africa.semicolon.movie.project.data.models.User;
import africa.semicolon.movie.project.data.repostories.UserRepository;
import africa.semicolon.movie.project.web.exceptions.BusinessLogicException;
import africa.semicolon.movie.project.web.exceptions.UserDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) throws BusinessLogicException {
        if(userDto == null){
            throw new IllegalArgumentException("Invalid request parameters");
        }
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new BusinessLogicException("User with email " +userDto.getEmail() + "already exits");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPassword(userDto.getPassword());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }

    @Override
    public User updateUserDetails(Long id, JsonPatch userPatch) throws UserDoesNotExistException, JsonPatchException, JsonProcessingException {
        Optional<User> queryResult = userRepository.findById(id);
        if(queryResult.isEmpty()){
            throw new UserDoesNotExistException("User with ID " +id + "Does not exist!!");
        }
        User targetUser = queryResult.get();
        try{
            targetUser = applyPatchToUSer(userPatch, targetUser);
            return userRepository.save(targetUser);
        }catch( JsonProcessingException | JsonPatchException e){
            System.out.println("Update failed!!");
        }
        return null;
    }

    @Override
    public User updateUser(String email, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    private User applyPatchToUSer(JsonPatch userPatch, User targetUser) throws JsonProcessingException, JsonPatchException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = userPatch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}
