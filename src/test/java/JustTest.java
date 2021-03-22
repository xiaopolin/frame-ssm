import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JustTest {

    @Test
    public void testProperties() throws Exception {
        File file = new File(getResource("template/emailHint.html"));

        StringBuffer stringBuffer = new StringBuffer("");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int len = 0;
        byte[] temp = new byte[1024];
        while ((len = bis.read(temp)) != -1){
            stringBuffer.append(new String(temp, 0, len));
        }

        System.out.println(stringBuffer);
    }

    public URI getResource(String url){
        URI result = null;
        try {
            result = JustTest.class.getClassLoader().getResource(url).toURI();
        }catch (Exception e){
            System.out.println("shibai");
        }

        return result;
    }



    @Test
    public void TestCalendar() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = sdf.parse("2019-01");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int a = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(a);


        for (int i = 0; i < 12 ; i++){
            if (0 == i){
                calendar.add(Calendar.MONTH, -1);
            }
            calendar.add(Calendar.MONTH, 1);
            System.out.println(sdf.format(calendar.getTime()));
        }
    }



}
