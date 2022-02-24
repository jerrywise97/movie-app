package africa.semicolon.movie.project.service.User;

import africa.semicolon.movie.project.data.dtos.UserDto;
import africa.semicolon.movie.project.data.models.User;
import africa.semicolon.movie.project.web.exceptions.BusinessLogicException;
import africa.semicolon.movie.project.web.exceptions.UserDoesNotExistException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

public interface UserService {
    User createUser(UserDto userDto) throws BusinessLogicException;
    User updateUserDetails(Long id, JsonPatch userPatch) throws UserDoesNotExistException, JsonPatchException, JsonProcessingException;
    User updateUser(String email, UserDto userDto);
    void deleteUser(String email);
    User findUserByEmail(String email);
    User findUserById(Long id);


}
