package jp.ne.hatena.syoko_sasaki;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;


public class ConfirmPageTest {

	WicketTester tester = new WicketTester();

	@Before
	public void before() throws Exception {
		tester.startPage(StartPage.class);
		tester.assertRenderedPage(StartPage.class);
		tester.clickLink("updateLink");
		tester.assertRenderedPage(UpdatePage.class);
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "shokos");
		formTester.setValue("old", "17");
		formTester.select("blood", 1);
		formTester.submit("submit");
		tester.assertRenderedPage(ConfirmPage.class);
	}

	@Test
	public void test() throws Exception {
		tester.assertRenderedPage(ConfirmPage.class);
		tester.assertLabel("name", "shokos");

	}

}
