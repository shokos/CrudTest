package jp.ne.hatena.syoko_sasaki;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;

public class FilePage {

	FilePage() {

	}

	class UploadForm extends Form<UploadForm> {

		private static final long serialVersionUID = 1L;

		transient FileUpload fileUplode;

		public UploadForm(String id) {
			super(id);
			setDefaultModel(new CompoundPropertyModel<UploadForm>(this));
			add(new FileUploadField("fileUplode").setRequired(true));
			add(new Button("submit"));
		}

		@Override
		protected void onSubmit() {
			super.onSubmit();
			String path = "path";
			File file = new File(path, fileUplode.getClientFileName());
			if (path.equals("test")) {
				try {
					file = File.createTempFile(fileUplode.getClientFileName(),
							".csv");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				fileUplode.writeTo(file);
			} catch (IOException e) {
				error("アップロードに失敗しました。");
				fileUplode.closeStreams();
				return;
			}
			fileUplode.closeStreams();
			info("アップロードしました。");
		}

	}

}
