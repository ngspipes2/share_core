package pt.isel.ngspipes.share_core.dataAccess.image;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.isel.ngspipes.share_core.dataAccess.IRepository;
import pt.isel.ngspipes.share_core.dataAccess.RepositoryException;
import pt.isel.ngspipes.share_core.logic.domain.Image;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

@Repository
public class ImageRepository implements IRepository<Image, String> {

    @Autowired
    private AmazonData amazonData;



    @Override
    public Collection<Image> getAll() throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Image getById(String id) throws RepositoryException {
        AmazonS3 client = getClient();

        if(client.doesObjectExist(this.amazonData.bucketName, id)) {
            GetObjectRequest request = new GetObjectRequest(this.amazonData.bucketName, id);
            S3Object obj = client.getObject(request);
            return new Image(id, getContent(obj));
        }

        return null;
    }

    @Override
    public void insert(Image image) throws RepositoryException {
        AmazonS3 client = getClient();

        File file = createFile(image.getId(), image.getContent());

        try{
            PutObjectRequest request = new PutObjectRequest(this.amazonData.bucketName, image.getId(), file);
            client.putObject(request);
        } finally {
          file.delete();
        }
    }

    @Override
    public void update(Image image) throws RepositoryException {
        insert(image);
    }

    @Override
    public void delete(String id) throws RepositoryException {
        AmazonS3 client = getClient();

        DeleteObjectRequest request = new DeleteObjectRequest(this.amazonData.bucketName,id);

        client.deleteObject(request);
    }

    private AmazonS3 getClient() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.amazonData.accessKey, this.amazonData.secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.amazonData.region)
                .build();
    }

    private byte[] getContent(S3Object file) throws RepositoryException {
        try {
            return IOUtils.toByteArray(file.getObjectContent());
        } catch (IOException e) {
            throw new RepositoryException("Error getting content of S3Object!", e);
        }
    }

    private File createFile(String fileName, byte[] content) throws RepositoryException {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content);
            fos.close();
            return file;
        } catch (IOException e) {
            throw new RepositoryException("Error creating file!", e);
        }
    }

}
