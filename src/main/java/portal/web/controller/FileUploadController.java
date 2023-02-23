package portal.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.YearMonth;

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
import jakarta.servlet.ServletRequest;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    
    @GetMapping
    public String listUploadedFiles(){
        return "upload";
    }

    @PostMapping
    @ResponseBody
	public void handleFileUpload(ServletRequest request,@RequestParam("file") MultipartFile file,Principal user) throws NoSuchAlgorithmException, IOException{
        String realPath=request.getServletContext().getRealPath("/upload/");
        String contextPath=request.getServletContext().getContextPath();
        String currentUsername=(user!=null?user.getName():"");
        String yearMonth=YearMonth.now().toString();
        File path=new File(realPath+(currentUsername.length()!=0?currentUsername+"/":"")+yearMonth+"/");
        if(!path.exists()){
            path.mkdirs();
        }
        //file.transferTo(new File(path+file.getOriginalFilename()));
        //return contextPath+(currentUsername.length()!=0?currentUsername+"/":"")+yearMonth+"/"+file.getOriginalFilename();
        System.out.println(path);
        System.out.println(contextPath+"/upload/"+(currentUsername.length()!=0?currentUsername+"/":"")+yearMonth+"/"+file.getOriginalFilename());
	}

    /*
    private String bytes2String(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for(byte b:bytes){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }
    */
    
}
