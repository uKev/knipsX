/**
* Here the classes are created and marshalled. 
* The Class Hello.java writes the data to 'output.xml'
*
*/

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hello h = new Hello();
		h.make( "Bonjour, madame", "fr" ); 
		h.make( "Hey, you", "en" ); 
		h.marshal();

	}

}

