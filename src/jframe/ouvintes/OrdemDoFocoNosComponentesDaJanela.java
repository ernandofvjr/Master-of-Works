package jframe.ouvintes;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

public class OrdemDoFocoNosComponentesDaJanela extends FocusTraversalPolicy {
	
	private ArrayList<Component> ordem;
	
	public OrdemDoFocoNosComponentesDaJanela(ArrayList<Component> ordem) {
		this.ordem = ordem;
		this.ordem.addAll(ordem);
	}	
	public Component getComponentAfter(Container aContainer, Component aComponent) {
		int index = (ordem.indexOf(aComponent) + 1) % ordem.size();		
		return ordem.get(index);
	}	
	public Component getComponentBefore(Container aContainer, Component aComponent) {
		int index = ordem.indexOf(aComponent) - 1;
		if(index < 0){
			index = ordem.size() - 1;
		}
		return ordem.get(index);
	}	
	public Component getDefaultComponent(Container aContainer) {		
		return ordem.get(0);
	}	
	public Component getFirstComponent(Container aContainer) {		
		return ordem.get(0);
	}	
	public Component getLastComponent(Container aContainer) {		
		return ordem.get(ordem.size() - 1);
	}
}