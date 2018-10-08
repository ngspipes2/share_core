package pt.isel.ngspipes.share_core.logic.service;

import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@FunctionalInterface
public interface ICurrentUserSupplier {

    User get() throws ServiceException;

}
