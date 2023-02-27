package portal.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileManagerController {
    
    @GetMapping("/browse")
    public ModelAndView browser(HttpServletRequest request,Principal user,@RequestParam(name="path",required = false) String path){
        ModelAndView mv=new ModelAndView("filemanager/browse");
        LocalDate ld=LocalDate.now();
        //List<Map<String,String>> nav=new ArrayList<>();
        String home=user!=null?"/"+user.getName():"";
        StringBuilder sb=new StringBuilder();
        if(path==null)
            sb.append("/").append(ld.getYear()).append("/").append(ld.getMonthValue());
        else
            sb.append(path);
        File directory=new File(request.getServletContext().getRealPath("/upload")+home+sb.toString());
        System.out.println(directory.getAbsolutePath());
        File[] files=directory.listFiles();
        ArrayList<String> urls=new ArrayList<>();
        String relativepath="/upload"+home+sb.toString();
        urls.add(relativepath.substring(0,relativepath.lastIndexOf('/')));
        for(File file:files){
            urls.add(file.getName());
        }
        mv.addObject("relativepath",relativepath);
        mv.addObject("files", files);
        return mv;
    }

    @PostMapping("/upload")
    @ResponseBody
	public Map<String,String> handleFileUpload(HttpServletRequest request,@RequestParam("upload") MultipartFile file,Principal user) throws IllegalStateException, IOException{
        Map<String,String> response=new HashMap<String,String>();
        LocalDate ld=LocalDate.now();
        StringBuilder sb=new StringBuilder("/upload");
        if(user!=null){
            sb.append("/");
            sb.append(user.getName());
        }
        sb.append("/").append(ld.getYear()).append("/").append(ld.getMonthValue()).append("/");
        File path=new File(request.getServletContext().getRealPath(sb.toString()));
        if(!path.exists()){
            path.mkdirs();
        }
        file.transferTo(new File(request.getServletContext().getRealPath(sb.toString())+"/"+file.getOriginalFilename()));
        response.put("uploaded","1");
        response.put("fileName",file.getOriginalFilename());
        response.put("url",request.getServletContext().getContextPath()+sb.toString()+file.getOriginalFilename());
        return response;
	}
}
