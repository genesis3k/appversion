package com.se.tech.versiondemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = VersiondemoApplication.class)
@TestPropertySource(properties = { "spring.jmx.default-domain=test" })
public class VersiondemoApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(VersiondemoApplicationTests.class);

    @Value("${git.closest.tag.name:UNKNOWN}")
    private String tagName;

    @Value("${git.build.version:UNKNOWN}")
    private String buildVersion;

    @Value("${git.commit.id.abbrev:UNKNOWN}")
    private String commitSHAIdAbbrev;
    
    @Test
    public void whenInjecting_shouldDisplay() throws Exception {
    	LOG.info("version:" + tagName);
        LOG.info("lastcommitsha:" + commitSHAIdAbbrev);
        LOG.info("pom buildVersion:" + buildVersion);
        
        assertThat(tagName).isNotEqualTo("UNKNOWN");

        assertThat(commitSHAIdAbbrev).isNotEqualTo("UNKNOWN");

        assertThat(buildVersion).isNotEqualTo("UNKNOWN");
    }
}