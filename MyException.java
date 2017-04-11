/**
 * 
 */
package FirstPart;

/**
 * This class is responsible
 * for handling exceptions
 *
 * @author Alla
 *
 */
public class MyException extends Exception{
	
	private String message;
	
	public MyException(String message, Exception e){
		this.message = message;
	};
	
	@Override
	public String toString(){
		return (message);
	}
	
}
