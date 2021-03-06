package com.devcoi.cursomc.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.devcoi.cursomc.services.exceptions.FileException;
import com.devcoi.cursomc.utils.FileUtils;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Iniciando upload...");
			s3client.putObject(new PutObjectRequest(bucketName, FileUtils.getNomeSemExtensao(file), file));
			LOG.info("Upload finalizado.");
		} catch (AmazonServiceException e) {
			LOG.warn("AmazonServiceException: " + e.getErrorMessage());
			LOG.warn("Status Code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			LOG.warn("AmazonClientException: " + e.getMessage());
		}
	}

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream input = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(input, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream input, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload...");
			s3client.putObject(new PutObjectRequest(bucketName, fileName, input, meta));
			LOG.info("Upload finalizado.");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao tentar converter URL para URI");
		}
	}
}
