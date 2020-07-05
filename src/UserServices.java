
import java.util.Collections;
import java.util.List;

public class UserServices {
	// Fibonatti
	public Integer execute(Integer n) throws Exception{
		if (n <= 1) return 1;
        else return execute(n-1) + execute(n-2);
    }
	
	// PA
	public static Integer execute(Integer a0, Integer an, Integer numElementos){
		if(numElementos==null) numElementos = new Integer(1);
		Integer soma = (((a0 + an)*(numElementos))/2);
		
		return soma;
	}
	
	// Sorting
	public List<Integer> ordena(List<Integer> valores){
		Collections.sort(valores);
		
		return valores;
	}
}
