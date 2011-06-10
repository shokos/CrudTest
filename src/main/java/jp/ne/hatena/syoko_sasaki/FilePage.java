package jp.ne.hatena.syoko_sasaki;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;



public class FilePage {

	class UploadForm extends Form<UploadForm>{

		private static final long serialVersionUID = 1L;

		transient FileUpload logFile;

		public UploadForm(String id) {
			super(id);
			setDefaultModel(new CompoundPropertyModel<UploadForm>(this));
			add(new FileUploadField("logFile").add(new LogFileNameValidator()).setRequired(true));
			add(new Button("submit"));
		}

		@Override
		protected void onSubmit() {
			super.onSubmit();
			String path = "path";
			File file = new File(path,logFile.getClientFileName());
			if(path.equals("test")){
				try {
					file = File.createTempFile(logFile.getClientFileName(),".csv");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			try {
				logFile.writeTo(file);
			} catch (IOException e) {
				error("アップロードに失敗しました。");
				logFile.closeStreams();
				return;
			}
			logFile.closeStreams();
			info("アップロードしました。");
		}

	}

	class LogFileNameValidator extends AbstractValidator<FileUpload>{

		private static final long serialVersionUID = 1L;

		@Override
		protected void onValidate(IValidatable<FileUpload> validatable) {
			String fileName = validatable.getValue().getClientFileName();
			List<LogConfiguration>  list = logConfigurationDao.findHandMadeLogConfiguration();
			for(LogConfiguration logConfiguration : list){
				String prefix = getPrefix(logConfiguration);
				if(fileName.startsWith(prefix)){
					return;
				}
			}
			error(validatable);
		}

		private String getPrefix(LogConfiguration logConfiguration) {
			String filename = FilenameUtils.getName(logConfiguration.getLogFilePath());
			return filename.split("_")[0];
		}

	}

}
