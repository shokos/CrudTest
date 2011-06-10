package jp.ne.hatena.syoko_sasaki;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class UpdatePageTest {

	WicketTester tester = new WicketTester();

	@Before
	public void before() throws Exception {
		tester.startPage(StartPage.class);
		tester.assertRenderedPage(StartPage.class);
		tester.clickLink("updateLink");
		tester.assertRenderedPage(UpdatePage.class);
	}

	@Test
	public void formTest() throws Exception {
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "shokos");
		formTester.setValue("old", "17");
		formTester.select("blood", 1);
		formTester.submit("submit");
		tester.assertRenderedPage(ConfirmPage.class);
	}

	@Test
	public void feedbackTest() throws Exception {
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "shokos");
		formTester.setValue("old", "hoge");
		formTester.select("blood", 1);
		formTester.submit("submit");
		tester.assertErrorMessages(new String[] {"'hoge' は Integer 型として正しくありません。"});

	}

}
