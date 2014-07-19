package it.aive.codeGenerator.descriptor.page;

import java.util.ArrayList;

public class Table  extends Widget{
 ArrayList colonne = new ArrayList();

public ArrayList getColonne() {
	return colonne;
}

public void setColonne(ArrayList colonne) {
	this.colonne = colonne;
}
}
