package com.mindtree.encryption.controller;


import com.mindtree.encryption.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mindtree.encryption.entity.FileInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@CrossOrigin
@RestController
public class ImageController
{

	@Autowired
	 private ImageService imageService;




	 @GetMapping("/delete-all-pics/{passedToken}")
	 public ResponseEntity<Map<String,Object>> deletePics(@PathVariable String passedToken)
	 {
		 
		return imageService.deleteAllImage(passedToken);
	 }

	@GetMapping("/delete-image/{imgName}/{passedToken}")
	public ResponseEntity<Map<String,Object>> deleteImg(@PathVariable String imgName,
			@PathVariable String passedToken) 
	{
		return imageService.deleteImageByName(imgName,passedToken);
	}

	/*
	 * @CrossOrigin(origins = "*", allowedHeaders = "*")
	 * 
	 * @GetMapping("consume-image") public ResponseEntity<byte[]> getImage() throws
	 * IOException{ File img =new File(UPLOADED_FOLDER+getAllFileNames().get(0));
	 * return ResponseEntity. ok().
	 * contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().
	 * getContentType(img))) .body(Files.readAllBytes(img.toPath())); }
	 */
	
	/*
	 * @CrossOrigin(origins = "*", allowedHeaders = "*",methods = RequestMethod.GET)
	 * 
	 * @GetMapping("consume-images") public ResponseEntity<byte[]> getImages()
	 * throws IOException{ File img = null; List<Byte[]> byteList = new
	 * ArrayList<Byte[]>(); for(String fileName:getAllFileNames()) { img = new
	 * File(UPLOADED_FOLDER+fileName); // Byte byte_[] = new Byte[]; //
	 * byteList.add(Files.readAllBytes(img.toPath())); } return ResponseEntity.
	 * ok(). contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().
	 * getContentType(img))) .body(Files.readAllBytes(img.toPath())); }
	 */
	

//	@GetMapping("get-file-list")
//	public ResponseEntity<List<String>> getFileNames() throws IOException
//	{
//		return ResponseEntity.ok().body(imageService.getAllFileNames());
//	}
@GetMapping("/logout/{passedToken}")
public ResponseEntity<Map<String,Object>> logout(@PathVariable String passedToken)
{
	return imageService.logout(passedToken);

}


	@PostMapping(value = "uploadMultipleFiles")
    public ResponseEntity<?> uploadFileMulti(@RequestParam(value = "files1",required = true) MultipartFile[] images,
    		@RequestParam(value = "passedToken") String passedToken) 
	{

		
		 return imageService.uploadFileMulti(images,passedToken);

    }


	

	

	


	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> map)
	{

		return imageService.login(map);
	}

	@PostMapping(value="/token-check",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkToken(@RequestBody Map<String, String> map)
	{
	
		return imageService.checkToken(map);
	}


	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = imageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageController.class, "getFile", path.getFileName().toString()).build().toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = imageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

		/*
		 * @OPTIONS
		 * 
		 * @javax.ws.rs.Path("/") public Response preflight() { return Response.ok()
		 * .header("Access-Control-Allow-Origin", "*")
		 * .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
		 * .header("Access-Control-Allow-Headers",
		 * "Content-Type, Accept, X-Requested-With").build(); }
		 */}
