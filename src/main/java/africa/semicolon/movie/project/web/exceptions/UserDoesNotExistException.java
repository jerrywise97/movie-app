package africa.semicolon.movie.project.web.exceptions;

public class UserDoesNotExistException extends Throwable {
    public UserDoesNotExistException(String message){
        super(message);
    }
}
