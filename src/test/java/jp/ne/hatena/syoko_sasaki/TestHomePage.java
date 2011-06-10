package jp.ne.hatena.syoko_sasaki;

import junit.framework.TestCase;
import org.apache.wicket.util.tester.WicketTester;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase
{
	private WicketTester tester;

	public void setUp()
	{
		tester = new WicketTester();
	}

	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(StartPage.class);

		//assert rendered page class
		tester.assertRenderedPage(StartPage.class);

		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
}
