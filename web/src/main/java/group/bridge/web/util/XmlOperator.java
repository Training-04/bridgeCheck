package group.bridge.web.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlOperator {
    public static Navigation getUrl() throws IOException {
        XmlMapper mapper=new XmlMapper();
        File file =  ResourceUtils.getFile("classpath:static/url.xml");


        Navigation navigation= mapper.readValue(file,Navigation.class);
        return navigation;
    }
}
