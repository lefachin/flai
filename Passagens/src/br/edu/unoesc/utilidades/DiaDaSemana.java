/**
 * 
 */
package br.edu.unoesc.utilidades;

/**
 * @author Fachin
 *
 */
public class DiaDaSemana {
	
	/**
	 * M�todo que pega o valor retornado pelo m�todo <b>getDayOfWeek</b> da classe DayOfWeek e transforma o valor
	 * em portugu�s.
	 * @param dia O dia da semana em ingl�s retornado pelo m�todo getDayOfWeek.
	 * @return <b>dia</b> O valor em portugu�s.
	 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/DayOfWeek.html">DayOfWeek</a>
	 * @author Luiz Eduardo Fachin
	 */
	
	public static String getDiaSemana(String dia) {
		switch (dia) {
		case "MONDAY":
			dia = "Segunda-Feira";
			break;
		case "THURSDAY":
			dia = "Quinta-Feira";
			break;
		case "TUESDAY":
			dia = "Ter�a-Feira";
			break;
		case "WEDNESDAY":
			dia = "Quarta-Feira";
			break;
		case "FRIDAY":
			dia = "Sexta-Feira";
			break;
		case "SATURDAY":
			dia = "S�bado";
			break;
		case "SUNDAY":
			dia = "Domingo";
			break;
		default:
			break;
		}
		return dia;
	}

}
