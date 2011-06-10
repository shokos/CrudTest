package jp.ne.hatena.syoko_sasaki;

import java.util.Arrays;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class UpdatePage extends WebPage {

	public UpdatePage(StartPage prevPage) {
		add(new FeedbackPanel("feedback"));
		TestObj testObj = new TestObj();
		add(new UpdateForm("form", testObj));
	}

	class UpdateForm extends Form<TestObj>{

		private static final long serialVersionUID = 1L;
		public UpdateForm(String id, final TestObj obj) {
			super(id);
			setDefaultModel(new CompoundPropertyModel<TestObj>(obj));
			add(new TextField<String>("name"));
			add(new TextField<Integer>("old"));
			add(new DropDownChoice<Blood>("blood", Arrays.asList(Blood.values()), new ChoiceRenderer<Blood>("name", "id")));
			add(new Button("submit") {

				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit() {
					setResponsePage(new ConfirmPage(obj, UpdatePage.this));
				}

			});
			add(new Link<Void>("return") {

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					setResponsePage(new StartPage(new PageParameters()));
				}

			});
		}

	}

}
