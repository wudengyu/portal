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

}