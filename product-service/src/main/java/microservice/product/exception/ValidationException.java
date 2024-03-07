package microservice.product.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<ObjectError> errors;

    public ValidationException(List<ObjectError> errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Validation failed with the following errors:");
        for (ObjectError error : errors) {
            sb.append("\n- ").append(error.getDefaultMessage());
        }
        return sb.toString();
    }
}