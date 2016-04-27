package model.lemmings.contract;

public class PostConditionError extends ContractError {
	private static final long serialVersionUID = 9050050491786738283L;

	public PostConditionError(String message) {
		super("Postcondition failed: "+message);
	}
}
