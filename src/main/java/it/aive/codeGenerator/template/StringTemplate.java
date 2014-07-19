package it.aive.codeGenerator.template;

import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class StringTemplate extends GenericTemplate {
	StringBuffer generatedCode = new StringBuffer();

	String templateString;

	public StringBuffer getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(StringBuffer generatedCode) {
		this.generatedCode = generatedCode;
	}

	public void generateCode() throws Exception {
 
		try {
			Properties props = new Properties();
			props.put("input.encoding", "utf-8");
			props.put("resource.loader", "class");
			props.put("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			props.put("directive.foreach.counter.initial.value","0");
			
			Velocity.init(props);

			VelocityContext context = new VelocityContext();
			Hashtable parm = getParametrs();
			Enumeration en = parm.keys();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				Object value = parm.get(key);
				context.put(key, value);
			}

			context.put("utility", utility);

			StringWriter writer = new StringWriter();
			Template template = Velocity.getTemplate(getTemplateFile());
			template.merge(context,writer);
			writer.flush();
			writer.close();
			generatedCode.append(writer.toString());
			
			;
		} catch (Exception e) {

			String errore = "Error " + e.getMessage()
					+ " in generating file code from template file "
					+ getTemplateFile();
			throw new Exception(errore);
		}
	}

	public String getTemplateString() {
		return templateString;
	}

	public void setTemplateString(String templateString) {
		this.templateString = templateString;
	}
}
