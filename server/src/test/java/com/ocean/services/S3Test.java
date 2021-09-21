/*package com.ocean.services;

import org.junit.jupiter.api.Test;

import java.io.File;

public class S3Test {
    S3Service s3 = new S3Service();

    @Test
    public void uploadImage(){
        File imageTest = new File("ImageTest.jpg");

     //   System.out.println("PathName = " + s3.uploadImage("UploadImageTestUser", imageTest));
    }

    @Test
    public void uploadProfileImage(){
        File profileTest = new File("ProfileTest.png");

        s3.uploadProfileImage("UploadProfileImageTestUser", profileTest);
    }

    @Test
    public void uploadVideo(){
        File videoTest = new File("VideoTest.MOV");

        s3.uploadVideo("UploadVideoTestUser", videoTest);
    }

}*/



/*
David: The above tests functioned when we were still passing files to S3Services, but I couldn't figure out
how to properly mock MultiPartFile objects.


@Test
    public void uploadImage(){
        File imageTest = new File("ImageTest.jpg");

        MultipartFile image = new MockMultipartFile(imageTest.getName(), imageTest.getName(), "image", null);

        assertTrue(s3.uploadImage(image).getSuccess());
    }

    @Test
    public void uploadProfileImage(){
        File profileTest = new File("ProfileTest.png");

        MultipartFile profileImage;

        s3.uploadProfileImage(profileImage);
    }

    //This is the same method in S3Service
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in S3Service.convertMultiPartFileToFile");
        }
        return convertedFile;
    }*/
