package com.revature.feed.controller;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.revature.feed.models.Response;
import com.revature.feed.services.S3Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FileUploadControllerTest {

    FileUploadController fileUploadController;

    @Mock
    S3Service s3Service;

    @BeforeEach
    void setUp() {
        this.fileUploadController = new FileUploadController(s3Service);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadProfileImage() {
        //uploading an image that will be the user's profile image

        //ASSIGN
        byte[] testBytes = {};
        MultipartFile testFile = new MockMultipartFile("Test", testBytes);

        String testFileName = testFile.getName();
        String pathName = "users/images/profile/" + testFileName;
        String bucketUrl = "https://teamwaterbucket.s3.us-east-2.amazonaws.com/";

        Response expectedResult = new Response(true, "image uploaded",bucketUrl+pathName);

        //since we don't want to upload images during the test, we'll just return the uploadFile Response
        Mockito.when(s3Service.uploadProfileImage(testFile)).thenReturn(new Response(true, "image uploaded",bucketUrl+pathName));

        //ACT
        Response actualResult = fileUploadController.uploadProfileImage(testFile).getBody();

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void uploadFile() {
        //uploading an image for posts

        //ASSIGN
        byte[] testBytes = {};
        MultipartFile testFile = new MockMultipartFile("Test", testBytes);

        String testFileName = testFile.getName();
        String pathName = "users/images/" + testFileName;
        String bucketUrl = "https://teamwaterbucket.s3.us-east-2.amazonaws.com/";

        Response expectedResult = new Response(true, "image uploaded",bucketUrl+pathName);

        //since we don't want to upload images during the test, we'll just return the uploadFile Response
        Mockito.when(s3Service.uploadImage(testFile)).thenReturn(new Response(true, "image uploaded",bucketUrl+pathName));

        //ACT
        Response actualResult = fileUploadController.uploadFile(testFile).getBody();

        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
}