import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest{
    @Test
    public void testEncoder(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
    @Test
    public void testchar(){
        //byte[] bytes;
        byte b=0x1f;
        System.out.print(b);
    }
    @Test
    public void testregular(){
        String content = "http://localhost:8080/portal/file/browse?path=/2023&CKEditor=editor1&CKEditorFuncNum=3&langCode=zh-cn";
        String pattern = ".*path=.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
        Pattern p = Pattern.compile("(?<=path=).*?(?=&|$)");
        Matcher m = p.matcher(content);
        System.out.println(m.toString());
        content = m.replaceAll("/2023/2");
        System.out.println(content);
    }

}