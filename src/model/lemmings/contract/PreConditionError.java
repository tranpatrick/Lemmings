package model.lemmings.contract;

public class PreConditionError extends ContractError {
	private static final long serialVersionUID = 1989924888153266862L;

	public PreConditionError(String message) {
		super("Precondition failed: "+message);
	}
}
