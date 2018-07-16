package pt.isel.ngspipes.share_core.dataAccess.pipelinesRepository;

import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.PipelinesRepository;

import java.util.Collection;

public interface IPipelinesRepositoryRepository extends IRepository<PipelinesRepository, Integer> {

    Collection<PipelinesRepository> getRepositoriesOfUser(String userName) throws RepositoryException;

}
