package jp.ne.hatena.syoko_sasaki;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Homepage
 */
public class StartPage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here


	public StartPage(final PageParameters parameters) {
		List<TestObj> objList = getObjList();
		add(new Link<Void>("updateLink") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new UpdatePage(StartPage.this));
			}

		});
		add(new TestObjListView("listView", objList));

	}

	private List<TestObj> getObjList() {
		List<TestObj> list = new ArrayList<TestObj>();
		list.add(new TestObj("hoge", 22, Blood.O));
		list.add(new TestObj("foo", 10, Blood.A));
		list.add(new TestObj("piyo", 17, Blood.AB));
		return list;
	}

	class TestObjListView extends ListView<TestObj>{

		private static final long serialVersionUID = 1L;

		public TestObjListView(String id, List<? extends TestObj> list) {
			super(id, list);
		}


		@Override
		protected void populateItem(ListItem<TestObj> item) {
			TestObj obj = item.getModelObject();
			item.setDefaultModel(new CompoundPropertyModel<TestObj>(obj));
			item.add(new Label("name"));
			item.add(new Label("old"));
			item.add(new Label("blood.name"));
		}

	}

}
