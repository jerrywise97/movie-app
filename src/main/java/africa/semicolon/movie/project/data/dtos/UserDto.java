package africa.semicolon.movie.project.data.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
