package pt.isel.ngspipes.share_core.logic.service.repositoryInfo;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.repository.GithubPipelinesRepository;
import pt.isel.ngspipes.dsl_core.descriptors.tool.repository.GithubToolsRepository;
import pt.isel.ngspipes.dsl_core.descriptors.utils.GithubAPI;
import pt.isel.ngspipes.pipeline_repository.IPipelinesRepository;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_repository.interfaces.IToolsRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RepositoryService implements IRepositoryService {

    @Value("${github.user}")
    private String githubUser;
    @Value("${github.token}")
    private String githubToken;
    @Autowired
    private GitHub github;



    @Override
    public void createRepository(RepositoryInfo repository) throws ServiceException {
        String githubRepositoryName = getGithubRepositoryName(repository);

        try {
            github.createRepository(githubRepositoryName).create();
        } catch (IOException e) {
            throw new ServiceException("Error creating repository!", e);
        }
    }

    @Override
    public void deleteRepository(RepositoryInfo repository) throws ServiceException {
        try {
            String location = getGithubRepositoryLocation(repository);
            GHRepository githubRepo = GithubAPI.getGHRepository(github, location);
            githubRepo.delete();
        } catch (IOException e) {
            throw new ServiceException("Error deleting repository!", e);
        }
    }

    @Override
    public IToolsRepository getToolsRepository(RepositoryInfo repository) throws ServiceException {
        String location = getGithubRepositoryLocation(repository);
        Map<String, Object> config = getGithubRepositoryConfig();

        return new GithubToolsRepository(location, config);
    }

    @Override
    public IPipelinesRepository getPipelinesRepository(RepositoryInfo repository) throws ServiceException {
        String location = getGithubRepositoryLocation(repository);
        Map<String, Object> config = getGithubRepositoryConfig();

        return new GithubPipelinesRepository(location, config);
    }


    private String getGithubRepositoryLocation(RepositoryInfo repository) {
        String githubRepositoryName = getGithubRepositoryName(repository);
        return "https://github.com/" + githubUser + "/" + githubRepositoryName;
    }

    private Map<String, Object> getGithubRepositoryConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(GithubToolsRepository.USER_NAME_CONFIG_KEY, githubUser);
        config.put(GithubToolsRepository.ACCESS_TOKEN_CONFIG_KEY, githubToken);

        return config;
    }

    private String getGithubRepositoryName(RepositoryInfo repository) {
        String repositoryName = repository.getRepositoryName();
        RepositoryInfo.EntityType type = repository.getEntityType();
        return getGithubRepositoryName(type, repositoryName);
    }

    private String getGithubRepositoryName(RepositoryInfo.EntityType entityType, String repositoryName) {
        return entityType + "_" + repositoryName;
    }

}
