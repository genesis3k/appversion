package com.se.tech.versiondemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class VersiondemoApplication {
	@Value("${git.tags}")
    private String tags;
 
    @Value("${git.build.version}")
    private String buildVersion;
 
    @Value("${git.commit.id}")
    private String commitSHAId;
    
	public static void main(String[] args) {
		SpringApplication.run(VersiondemoApplication.class, args);
	}
	
	@RequestMapping(value = "/")
	 public String versionInfo() {
//	    return "Hello World";
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("Application info\n");
		strBuff.append("tags:" + tags + ",\n");
		strBuff.append("build version:" + buildVersion + ",\n");
		strBuff.append("commit SHA Id:" + commitSHAId + "\n");
		return strBuff.toString();
	 }
	
	@RequestMapping("/version")
    public Map<String, String> getCommitId() {
        Map<String, String> result = new HashMap<>();
        if (tags.isEmpty()) {
        	tags = buildVersion;
        }
        result.put("version",tags);
        result.put("lastcommitsha", commitSHAId);
        result.put("description", "Demo to show Application version");
        return result;
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
	
}