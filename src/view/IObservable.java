package view;

public interface IObservable {

	public void addObserver(IObserver obs);
	
	public void deleteObserver(IObserver obs);
	
	public void notifierObservateurs();
}
