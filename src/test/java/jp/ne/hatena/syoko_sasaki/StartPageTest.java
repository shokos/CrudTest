package jp.ne.hatena.syoko_sasaki;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class StartPageTest {

	WicketTester tester = new WicketTester();

	@Before
	public void before() throws Exception {
		tester.startPage(StartPage.class);
		tester.assertRenderedPage(StartPage.class);
	}

	@Test
	public void newPage() throws Exception {
		tester.clickLink("updateLink");
		tester.assertRenderedPage(UpdatePage.class);
	}

	@Test
	public void filePage() throws Exception {
		tester.clickLink("fileLink");
	}

}
