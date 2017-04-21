package com.leekyoungil;

import com.leekyoungil.service.HttpService;
import com.leekyoungil.service.SpringCampTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudStreamSampleApplicationTests {

	private SpringCampTestService springCampTestService;
	private HttpService httpService;

	@Inject
	@Required
	public void setSpringCampTestService (SpringCampTestService springCampTestService) {
		this.springCampTestService = springCampTestService;
	}

	@Inject
	@Required
	public void setHttpService (HttpService httpService) {
		this.httpService = httpService;
	}

	@Test
	public void getHttpTest () {
		String url = "http://www.daum.net";
		assertNotNull(httpService.getHttpHtmlByUrl(url));
	}

	@Test
	public void publishTest () {
		assertTrue(springCampTestService.publishToStream());
	}
}
