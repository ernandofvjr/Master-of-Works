package jframe.componentes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import classes.Atividade;
import classes.Semana;

public class ModeloDeArvore implements TreeModel {
	
	private String raiz = "ATIVIDADES";
    
    private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();

    private List<Semana> semanas;

    public ModeloDeArvore(List<Semana> semanas) {
    	this.semanas = semanas;
    }

	public void addTreeModelListener(TreeModelListener l) {	
	}
	public Object getChild(Object parent, int index) {
		
		if (parent == raiz) // É o nó principal?
			return semanas.get(index); // Pegamos da lista de livro

		if (parent instanceof Semana) // O pai é um livro?
		{
			// Devolvemos um autor
			return ((Semana) parent).getAtividades().get(index);
		}

		// Se o pai não é nenhum desses. Melhor dar erro.
		throw new IllegalArgumentException("Invalid parent class"
				+ parent.getClass().getSimpleName());
	}
	public int getChildCount(Object parent) {
		// Mesma lógica.
				if (parent == raiz)
					return semanas.size();

				if (parent instanceof Semana) // O pai é um livro?
					return ((Semana) parent).getAtividades().size();

				// Se o pai não é nenhum desses. Melhor dar erro.
				throw new IllegalArgumentException("Invalid parent class"
						+ parent.getClass().getSimpleName());
	}
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == raiz)
			return semanas.indexOf(child);
		if (parent instanceof Semana)
			return ((Semana) parent).getAtividades().indexOf(child);

		return 0;
	}
	public Object getRoot() {
		return raiz;
	}
	public boolean isLeaf(Object node) {
		return node instanceof Atividade;
	}
	public void removeTreeModelListener(TreeModelListener l) {
	}
	public void valueForPathChanged(TreePath path, Object newValue) {
	}
}