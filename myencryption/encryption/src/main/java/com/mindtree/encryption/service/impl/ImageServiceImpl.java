package com.mindtree.encryption.service.impl;

import com.mindtree.encryption.controller.ImageController;
import com.mindtree.encryption.exception.EncryptionUtilException;
import com.mindtree.encryption.service.ImageService;
import com.mindtree.encryption.util.DateFormatter;
import com.mindtree.encryption.util.EncryptionDecryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${app.id}")
    private String appId;

    @Value("${app.password}")
    private String appPassword;

    private static String token=EncryptionDecryption.getAlphaNumericString();

    private static String dateTime="";
    private final Path root = Paths.get("../uploads");
    private String UPLOADED_FOLDER = root.toAbsolutePath().toString()+"/";




 //   @Override
 //   public void init() {
  //      try {
 //           Files.createDirectory(root);
 //       } catch (IOException e) {
  //          throw new RuntimeException("Could not initialize folder for upload!");
 //       }
//    }
    
    @Override
public void init() {
    try {
        if (!Files.exists(root)) {
            Files.createDirectory(root);
        }

    } catch (IOException e) {
        throw new RuntimeException("Could not initialize folder for upload!");
    }
}


    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());

    }




    private static boolean checkToken_(String passedToken)
    {
        passedToken = passedToken.trim();

        if(passedToken.compareTo(token)==0
                &&
                DateFormatter.checkTimeDiff(dateTime,DateFormatter.convertUtilDateToString(new Date())))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


//    private String deleteAllPics()
//    {
//        String message = "";
//        File directoryPath = new File(UPLOADED_FOLDER);
//        List<String> fileList =  getAllFileNames();
//        for (String string : directoryPath.list())
//        {
//            try {
//                Files.delete(Paths.get(UPLOADED_FOLDER+string));
//            }
//            catch (IOException e)
//            {
//
//                e.printStackTrace();
//            }
//        }
//        return "All files deleted";
//    }



//    private static List<String> getAllFileNames()
//    {
//        File directoryPath = new File(UPLOADED_FOLDER);
//        ArrayList<String> fileList = new ArrayList<String>();
//        for (String string : directoryPath.list())
//        {
//            fileList.add(string);
//        }
//        return fileList;
//    }



//    private static String saveUploadedFiles(List<MultipartFile> files) throws IOException
//    {
//        for (MultipartFile file : files) {
//
//            if (file==null)
//            {
//                System.out.println("no file selected");
//            }
//            try {
//                byte[] bytes = file.getBytes();
//                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//                Files.write(path, bytes);
//
//            }
//
//
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        return "files uploaded successfully";
//    }



    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }



    @Override
    public ResponseEntity<?> uploadFileMulti(MultipartFile[] images, String passedToken) {
        if(checkToken_(passedToken)==false)
        {
            return new ResponseEntity("Session_Expired", HttpStatus.BAD_REQUEST);
        }

        String message = "";

        try {
            List<String> fileNames=new ArrayList<>();
            Arrays.asList(images).stream().forEach(file->{
                save(file);
                fileNames.add(file.getName());
            });
            message="Uploaded the files sucessfully : "+ fileNames;
        } catch (Exception e) {
            message="fail to upload";
        }
        return new ResponseEntity(message, HttpStatus.OK);
    }




    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }




    @Override
    public ResponseEntity<Map<String, Object>> deleteImageByName(String imgName, String passedToken) {
        Map<String,Object> responseMap = new HashMap<String,Object>();
        responseMap.put("message","image deleted successfully");
        if(checkToken_(passedToken.trim())==false)
        {
            responseMap.put("message","failed to  delete image");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
        imgName = imgName.trim();
        try {
            Files.delete(Paths.get(UPLOADED_FOLDER+imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }




    @Override
    public ResponseEntity<Map<String, Object>> logout(String passedToken) {
        System.err.println("logout..............");
        Map<String,Object> responseMap = new HashMap<String,Object>();
        responseMap.put("date",DateFormatter.convertUtilDateToString(new Date()));
        responseMap.put("message","successfull logout");
        if(checkToken_(passedToken)==false)
        {
            responseMap.put("message","fail to logout");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
        token = EncryptionDecryption.getAlphaNumericString();

        return   ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }




    @Override
    public ResponseEntity<Boolean> checkToken(Map<String, String> map) {
        String passedToken = map.get("passedToken");
        if(passedToken.compareTo(token)==0&&DateFormatter.checkTimeDiff(dateTime,DateFormatter.convertUtilDateToString(new Date())))
        {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        else
        {
            System.err.println("bad request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }





    @Override
    public ResponseEntity<Map<String, String>> login(Map<String, String> map) {
        Map<String, String> responseMap = new HashMap<String, String>();
        Map<String, String> errorResponseMap = new HashMap<String, String>();
        String id = (String)map.get("id");
        String password = (String)map.get("password");
        id=id.trim();
        password=password.trim();
        if(id.equals(appId) && password.equals(appPassword))
        {
            String key=EncryptionDecryption.getAlphaNumericString();
            try
            {
                token=EncryptionDecryption.encryptData(key);
                dateTime = DateFormatter.convertUtilDateToString(new Date());
                responseMap.put("token", token);
            } catch (EncryptionUtilException e)
            {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }
        else
        {
            errorResponseMap.put("message","invalid credential");
            errorResponseMap.put("date_time",DateFormatter.convertUtilDateToString(new Date()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseMap);
        }
    }




    @Override
    public ResponseEntity<Map<String, Object>> deleteAllImage(String passedToken) {
        Map<String, Object> responseMap =  new HashMap<String, Object>();
        if(checkToken_(passedToken)==false)
        {
            responseMap.put("message","failed to delete pics");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }
        FileSystemUtils.deleteRecursively(root.toFile());
        init();
        responseMap.put("message","All files deleted");
        return   ResponseEntity.status(HttpStatus.OK).body(responseMap);

    }

}
