package pt.isel.ngspipes.share_core.logic.service.exceptions;

public class NonExistentEntityException extends ServiceException {

    public NonExistentEntityException() {
        super();
    }

    public NonExistentEntityException(String message) {
        super(message);
    }

    public NonExistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonExistentEntityException(Throwable cause) {
        super(cause);
    }

}
