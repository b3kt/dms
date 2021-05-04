package id.alinea.dms;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import id.alinea.dms.entity.SystemFile;
import id.alinea.dms.service.impl.DbSystemFileService;

@AutoConfigureMockMvc
@SpringBootTest
public class DBConnectionTests {

	@MockBean
	private DbSystemFileService storageService;

	@Test
	public void testFetchData() throws Exception {
		SystemFile sysFile = this.storageService.findSystemFileByUuid("123123123123132");
		Assert.isNull(sysFile, "should not null");
	}

}
