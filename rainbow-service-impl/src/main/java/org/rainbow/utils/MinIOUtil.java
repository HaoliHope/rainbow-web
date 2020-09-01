package org.rainbow.utils;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinIO 工具类
 *
 * @author lihao3
 * @Date 2020/9/1 9:05
 */
@Slf4j
@Component
public class MinIOUtil {

    @Value("${minio.url}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件URL路径
     * @throws Exception
     */
    public String uploadFile(@NotNull MultipartFile file) throws Exception {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        // 检查文件桶
        this.bucketExists();
        // 获取文件名
        String filename = file.getOriginalFilename();
        // 获取时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置存储对象名称
        String objectName = sdf.format(new Date()) + "/" + filename;
        // 使用putObject上传一个文件到存储桶中
        minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
        // 获取URL
        return ENDPOINT + "/" + BUCKET_NAME + "/" + objectName;
    }

    /**
     * 删除文件
     *
     * @param objectName
     * @throws Exception
     */
    public void deleteFile(@NotNull String objectName) throws Exception {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
        // 删除文件
        minioClient.removeObject(BUCKET_NAME, objectName);
    }

    /**
     * 检查文件桶是否存在
     */
    private void bucketExists() {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            // 插件文件桶是否存在
            boolean bucketExists = minioClient.bucketExists(BUCKET_NAME);
            // 如果文件桶已存在则不创建，反之
            if (bucketExists) {
                log.info("文件桶{}已存在！", BUCKET_NAME);
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
        } catch (Exception e) {
            log.error("检查文件桶错误：{}！", e.getMessage());
        }
    }
}
