package animal.meeting.global.s3;

import static java.util.UUID.*;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import animal.meeting.global.error.CustomException;
import animal.meeting.global.error.constants.ErrorCode;

@Service
public class S3Service {
    private final AmazonS3 s3client;
    private final String bucketName;

    @Autowired
    public S3Service(@Value("${cloud.aws.s3.bucket}") String bucketName, AmazonS3 s3client) {
        this.bucketName = bucketName;
        this.s3client = s3client;
    }

    public String uploadMultipartFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_MULTIPARTFILE);
        }

        String fileName = randomUUID().toString() + ".jpg";
        InputStream inputStream = multipartFile.getInputStream();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            uploadFile(fileName, inputStream, metadata);
            return getFileUrl(fileName);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            throw new IOException("Failed to upload file to Amazon S3: " + e.getMessage());
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new IOException("Amazon S3 client error: " + e.getMessage());
        } finally {
            inputStream.close();
        }
    }

    private void uploadFile(String fileName, InputStream inputStream, ObjectMetadata metadata) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
    }

    private String getFileUrl(String fileName) {
        return s3client.getUrl(bucketName, fileName).toString();
    }
}
