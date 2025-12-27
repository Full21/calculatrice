package calculatrice;

import java.lang.annotation.ElementType;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
public class MoteurCalculatrice {


	public static double calculer(List expressionTraite) {
		
		Stack<Double> pileNombre = new Stack<>();
		Stack<Operateur> pileOp = new Stack<>();
		
		for(Object element : expressionTraite) {	
			if(element instanceof Operateur operateur) {
				pileOp.push(operateur);															
				if(pileNombre.isEmpty()) continue;
				while (!pileOp.isEmpty()) {
					double b = pileNombre.pop();
					if(operateur.estUnaire()) {
						pileNombre.push(pileOp.pop().execute(b));
					} else {
						double a = 0;
						try {a = pileNombre.pop(); }catch(EmptyStackException e) {}
						pileNombre.push(pileOp.pop().execute(a, b));
					}
				}												
			} else {
				pileNombre.push((Double)element);
			}
		}
		
		return pileNombre.pop();
	}

}
