package r6guidebackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlreadyExistException extends Exception{
    private String message;
}
