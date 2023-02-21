package portal.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.YearMonth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    
    @GetMapping
    public String listUploadedFiles(){
        return "upload";
    }

    @PostMapping
    @ResponseBody
	public void handleFileUpload(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal Principal user) throws NoSuchAlgorithmException, IOException{
        String uploadpath="";
        WebApplicationContext webApplicationContext=ContextLoader.getCurrentWebApplicationContext();
        if(webApplicationContext!=null){
            ServletContext servletContext=webApplicationContext.getServletContext();
            if(servletContext!=null){
                uploadpath=servletContext.getRealPath("/upload/");
            }
        }
        File path=new File(uploadpath+YearMonth.now().toString());
        if(!path.exists()){
            path.mkdir();
        }
        System.out.println(user.getName());
        MessageDigest md5=MessageDigest.getInstance("SHA-1");
        md5.update(file.getBytes());
        file.transferTo(new File(uploadpath+YearMonth.now().toString()+"/"+file.getOriginalFilename()));
	}
    
    private String bytes2String(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for(byte b:bytes){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
    
}
