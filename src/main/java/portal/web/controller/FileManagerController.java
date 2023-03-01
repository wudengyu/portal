package portal.web.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import portal.business.FileInfo;

@Controller
@RequestMapping("/file")
public class FileManagerController {
    
    @GetMapping("/browse")
    public ModelAndView browser(HttpServletRequest request,@RequestParam("CKEditorFuncNum") String funcNum,Principal user) throws IOException{
        ModelAndView mv=new ModelAndView("filemanager/browse");
        String home=user!=null?"/"+user.getName():"";
        String queryString=request.getQueryString();
        String path=request.getParameter("path");
        if(path==null){
            path="/";
            queryString+="&path=/";
        }
        String relativepath="/upload"+home+path;
        File currentpath=new File(request.getServletContext().getRealPath(relativepath));
        ArrayList<FileInfo> files=new ArrayList<>();
        for(File file:currentpath.listFiles()){
            if(!path.equals("/")){
                FileInfo fileInfo=new FileInfo("返回上级目录", relativepath, 99);
                Pattern p = Pattern.compile("(?<=path=).*?(?=&|$)");
                Matcher m = p.matcher(queryString);
                if(path.lastIndexOf("/")!=0)
                    fileInfo.setAnchor("?"+m.replaceAll(path.substring(0,path.lastIndexOf('/'))));
                else
                    fileInfo.setAnchor("?path=/");
                files.add(fileInfo);
            }
            if(file.isDirectory()){
                Pattern p = Pattern.compile("(?<=path=).*?(?=&|$)");
                Matcher m = p.matcher(queryString);
                if(path.endsWith("/")){
                    queryString = m.replaceAll(path+file.getName());
                }else{
                    queryString = m.replaceAll(path+"/"+file.getName());
                }
                FileInfo fileInfo=new FileInfo(file.getName(), relativepath, 0);
                fileInfo.setAnchor("?"+queryString);
                files.add(fileInfo);
            }
            else{
                files.add(new FileInfo(file.getName(),relativepath,1));
            }
        }
        mv.addObject("funcNum",funcNum);
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
