package jp.ne.hatena.syoko_sasaki;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

public class ConfirmPage extends WebPage {

	public ConfirmPage(TestObj obj, final UpdatePage prevPage) {
		setDefaultModel(new CompoundPropertyModel<TestObj>(obj));
		add(new Label("name"));
		add(new Label("old"));
		add(new Label("blood.name"));
		add(new Link<Void>("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
			}

		});
		add(new Link<Void>("return") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(prevPage);
			}

		});

	}

}
