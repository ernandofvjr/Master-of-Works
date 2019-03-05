package classes;

import java.awt.Font;

public class Fonte extends Font {
	
	protected Fonte(Font font) {
		super(font);
	}
	
	public static Font tituloNegrito(){
		return new Font("Arial", Font.BOLD, 22);
	}
	public static Font tituloNormal(){
		return new Font("Arial", Font.PLAIN, 22);
	}
	public static Font textoNegrito(){
		return new Font("Arial", Font.BOLD, 14);
	}
	public static Font textoNormal(){
		return new Font("Arial", Font.PLAIN, 14);
	}
	public static Font personalizadaNormal(int tamanho){
		return new Font("Arial", Font.PLAIN, tamanho);
	}
	public static Font personalizadaNegrito(int tamanho){
		return new Font("Arial", Font.BOLD, tamanho);
	}
}
