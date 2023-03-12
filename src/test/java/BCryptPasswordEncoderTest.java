import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import portal.business.ArticleStatus;

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

    @Test
    public void testenum(){
        for(ArticleStatus s:ArticleStatus.values()){
            System.out.print(s.ordinal());
            System.out.println(s.getStatus());
        }
        
    }

    @Test
    public void testuser(){
        ArrayList<SimpleGrantedAuthority> au=new ArrayList<>();
        au.add(new SimpleGrantedAuthority("admin"));
        User user=new User("admin", "admin",au);
        System.out.printf("AccountNonExpired %s\n",user.isAccountNonExpired());
        System.out.printf("AccountNonLocked %s\n",user.isAccountNonLocked());
        System.out.printf("CredentialsNonExpired %s\n",user.isCredentialsNonExpired());
        System.out.printf("Enabled %s\n",user.isEnabled());
    }

}