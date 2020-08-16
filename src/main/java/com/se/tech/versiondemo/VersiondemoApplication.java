package com.se.tech.versiondemo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class VersiondemoApplication {
	@Value("${git.closest.tag.name}")
    private String tagName;
 
    @Value("${git.build.version}")
    private String buildVersion;
 
    @Value("${git.commit.id.abbrev}")
    private String commitSHAIdAbbrev;
    private static final Logger LOG = LoggerFactory.getLogger(VersiondemoApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(VersiondemoApplication.class, args);
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer props 
          = new PropertySourcesPlaceholderConfigurer();
        props.setLocation(new ClassPathResource("git.properties"));
        props.setIgnoreResourceNotFound(true);
        props.setIgnoreUnresolvablePlaceholders(true);
        return props;
    }
	
	//This endpoint on HTTP GET request, will respond with a JSON containing injected values from git.properties. 
	//Below should be used if pure JSON format is required, without any additional text outside of JSON { } block.
	@RequestMapping(value = "/version", method = RequestMethod.GET)
    public Map<String, String> getAppInfo() {
        Map<String, String> result = new HashMap<>();
        //If tagname is empty, fall back to show the version number specified in pom.xml,which is used as git build version
        String tag = "";
        if (tagName.isEmpty())
        	tag = buildVersion;
        else
        	tag = tagName;
        result.put("version",tag);
        result.put("lastcommitsha", commitSHAIdAbbrev);
        result.put("description", "Demo to show Application version");
        
        LOG.info("\nversion: " + tag + "\n" + "lastcommitsha: " + commitSHAIdAbbrev + "\n" + 
        		"description: " + "Demo to show Application version");
        return result;
    }
	
	//Create a JSON like string to include additional text outside of JSON { } block, like "myapplication" in below case
	@RequestMapping(value = "/", method = RequestMethod.GET)
	 public String versionInfo() {
		//If tagname is empty, fall back to show the version number specified in pom.xml,which is used as git build version
	    String tag = "";
        if (tagName.isEmpty())
        	tag = buildVersion;
        else
        	tag = tagName;
        
		String json = "\"myapplication\": [\n";
		json = json + " {" + "\n";
		json += "  \"version\": " + "\"" + tag + "\"" + ",\n";
		json += "  \"lastcommitsha\": " + "\"" + commitSHAIdAbbrev + "\"" + ",\n";
		json += "  \"description\": " + "\"Demo to show Application version\"" + "\n";
		json += " }" + "\n]";
		
        LOG.info("\n" + json);
		return json;
		
	 }
	
}