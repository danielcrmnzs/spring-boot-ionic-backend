package com.devcoi.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

}