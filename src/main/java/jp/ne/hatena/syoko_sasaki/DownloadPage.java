package jp.ne.hatena.syoko_sasaki;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

public class DownloadPage extends WebPage {

	public DownloadPage(PageParameters parameters) {

		Link<Void> downloadLink = new Link<Void>("download") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				this.getRequestCycle().setRequestTarget(
						new ResourceStreamRequestTarget(
								new AbstractResourceStreamWriter() {
									private static final long serialVersionUID = 1L;

									public String getContentType() {
										return "application/octet-stream;charset=MS932";
									}

									public void write(OutputStream output) {
										try {
											IOUtils.copy(
													DownloadPage.class
															.getResourceAsStream("shokos.csv"),
													output);
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}, "shokos.csv"));

			}
		};
		add(downloadLink);
	}

}
