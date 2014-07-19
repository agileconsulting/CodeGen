package it.aive.codeGenerator.descriptor.page;

import java.util.ArrayList;

public class Form extends Widget {
String action;
ArrayList widgets = new ArrayList();

public ArrayList getWidgets() {
	return widgets;
}

public void setWidgets(ArrayList widgets) {
	this.widgets = widgets;
}



}
