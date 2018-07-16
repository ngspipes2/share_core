package pt.isel.ngspipes.share_core.logic.service.exceptions;

public class DuplicatedEntityException extends ServiceException {

    public DuplicatedEntityException() {
        super();
    }

    public DuplicatedEntityException(String message) {
        super(message);
    }

    public DuplicatedEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedEntityException(Throwable cause) {
        super(cause);
    }

}
