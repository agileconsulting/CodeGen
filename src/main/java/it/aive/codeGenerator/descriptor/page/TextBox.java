package it.aive.codeGenerator.descriptor.page;

public class TextBox extends Widget {
 String maxLength;
 String size;
 String readOnly;
public String getMaxLength() {
	return maxLength;
}
public void setMaxLength(String maxLength) {
	this.maxLength = maxLength;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public String getReadOnly() {
	return readOnly;
}
public void setReadOnly(String readOnly) {
	this.readOnly = readOnly;
}
 
}
