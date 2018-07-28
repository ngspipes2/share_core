package pt.isel.ngspipes.share_core.logic.service.image;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;

@Service
public class ImageService extends pt.isel.ngspipes.share_core.logic.service.Service<Image, String> {

    protected ImageService() {
        super("Images","Image");
    }



    @Override
    protected void validateInsert(Image element) throws ServiceException { }

    @Override
    protected void validateDelete(String key) throws ServiceException { }

    @Override
    protected void validateUpdate(Image element) throws ServiceException { }

    @Override
    protected String getId(Image image) throws ServiceException { return image.getId(); }

}
