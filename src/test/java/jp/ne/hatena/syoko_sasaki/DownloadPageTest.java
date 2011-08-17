package jp.ne.hatena.syoko_sasaki;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.MockHttpServletResponse;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class DownloadPageTest {

	WicketTester tester = new WicketTester();

	@Before
	public void before() {
		tester.startPage(DownloadPage.class);
	}

	@Test
	public void ページ表示() throws Exception {
		tester.assertRenderedPage(DownloadPage.class);
		tester.assertComponent("download", Link.class);
	}

	@Test
	public void ダウンロードできること() throws Exception {
		tester.clickLink("download");
		String dir = System.getProperty("java.io.tmpdir");
		MockHttpServletResponse response = tester.getServletResponse();
		assertThat(response.getContentType(),
				is("application/octet-stream;charset=MS932"));
		File file = new File(dir + File.separator + "shokos.csv");
		FileOutputStream responceOutputStream = new FileOutputStream(file);
		responceOutputStream.write(response.getBinaryContent());
		responceOutputStream.close();
		assertCsvFile(dir + File.separator + "shokos.csv", "expected.csv");
	}

	private void assertCsvFile(String actualFile, String expectedFile) {
		String actual = null;
		String expected = null;
		try {
			actual = IOUtils.toString(new InputStreamReader(
					new FileInputStream(actualFile), "MS932"));
			expected = IOUtils.toString(
					getClass().getResourceAsStream(expectedFile), "MS932");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, String>> actualList = parse(actual);
		List<Map<String, String>> expectedList = parse(expected);

		assertThat(actualList.size(), is(expectedList.size()));
		for (int i = 0; i < actualList.size(); i++) {
			Map<String, String> acutualMap = actualList.get(i);
			Map<String, String> expectedMap = expectedList.get(i);

			assertThat(acutualMap.size(), is(expectedMap.size()));
			for (String key : expectedMap.keySet()) {
				assertThat(acutualMap.get(key), is(expectedMap.get(key)));
			}
		}
	}

	List<Map<String, String>> parse(String csv) {
		String cammma = ",";
		List<String> lines = Arrays.asList(csv.split("\r"));

		String headLine = lines.get(0);
		String[] header = headLine.split(cammma);
		lines = lines.subList(1, lines.size());

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (String line : lines) {
			Map<String, String> map = new HashMap<String, String>();
			String[] split = line.split(cammma);
			for (int i = 0; i < split.length; i++) {
				map.put(header[i], split[i]);
			}
			list.add(map);
		}
		return list;
	}

}
