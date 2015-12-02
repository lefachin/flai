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
	 * Método que pega o valor retornado pelo método <b>getDayOfWeek</b> da classe DayOfWeek e transforma o valor
	 * em português.
	 * @param dia O dia da semana em inglês retornado pelo método getDayOfWeek.
	 * @return <b>dia</b> O valor em português.
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
			dia = "Terça-Feira";
			break;
		case "WEDNESDAY":
			dia = "Quarta-Feira";
			break;
		case "FRIDAY":
			dia = "Sexta-Feira";
			break;
		case "SATURDAY":
			dia = "Sábado";
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
