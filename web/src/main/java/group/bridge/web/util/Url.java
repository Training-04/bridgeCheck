package group.bridge.web.util;

import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.util.List;
@JacksonXmlRootElement(localName = "Url")
public class Url {
    @JacksonXmlProperty(isAttribute = true,localName = "text")
    String text;
    @JacksonXmlProperty(isAttribute = true,localName = "path")
    String path;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Url")
    List<Url> urls;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }
}
