package tradefinance;
 
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;
 
 
@Contract(
        name = "TradeFinance",
        info = @Info(
                title = "Trade finance contract",
                description = "A trade finance chaincode",
                version = "0.0.1-SNAPSHOT"))
 
 
@Default
public final class TradeFinance implements ContractInterface {
 
	private final Genson genson = new Genson();
	private enum TradeFinanceErrors {
	        Lettor_Of_Credit_NOT_FOUND,
	        Letter_Of_Credit_ALREADY_EXISTS
	    }
	private enum Status {
        REQUESTED,
        ISSUED,
        ACCEPTED
    }
	
	/**
     * Add some initial properties to the ledger
     *
     * @param ctx the transaction context
     */
    @Transaction()
    public void initLedger(final Context ctx) {
    }
    
    

	
    @Transaction()
    public LetterOfCredit requestLetterOfCredit(final Context ctx, final String id, final String expiryDate,
            final String buyer, final String bank, final String seller, final String status, final String amount ) {
        
    	ChaincodeStub stub = ctx.getStub();
 
        String LetterOfCreditState = stub.getStringState(id);
        
        if (!LetterOfCreditState.isEmpty()) {
            String errorMessage = String.format("Letter of credit with id: %s already exists", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, TradeFinanceErrors.Letter_Of_Credit_ALREADY_EXISTS.toString());
        }
        
        LetterOfCredit LetterOfCredit = new LetterOfCredit(id, expiryDate, buyer, bank, seller, amount, Status.REQUESTED.toString() );
        
        LetterOfCreditState = genson.serialize(LetterOfCredit);
        
        stub.putStringState(id, LetterOfCreditState); 
        
        return LetterOfCredit;
    }
 
 

    	@Transaction()
	    public LetterOfCredit issueLetterOfCredit(final Context ctx, final String id) {
	        ChaincodeStub stub = ctx.getStub();
	        String LetterOfCreditState = stub.getStringState(id);
 
	        if (LetterOfCreditState.isEmpty()) {
	            String errorMessage = String.format("Letter of Credit with id: %s does not exist.", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, TradeFinanceErrors.Lettor_Of_Credit_NOT_FOUND.toString());
	        }
	        LetterOfCredit LetterOfCredit = genson.deserialize(LetterOfCreditState, LetterOfCredit.class);
	        LetterOfCredit.setStatus(Status.ISSUED.toString());
   	        String newState = genson.serialize(LetterOfCredit);
   	        stub.putStringState(id, newState);

	        return LetterOfCredit;
	    }
    	

    	@Transaction()
	    public LetterOfCredit acceptLetterOfCredit(final Context ctx, final String id) {
	        ChaincodeStub stub = ctx.getStub();
	        String LetterOfCreditState = stub.getStringState(id);
 
	        if (LetterOfCreditState.isEmpty()) {
	            String errorMessage = String.format("Letter of Credit with id: %s does not exist.", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, TradeFinanceErrors.Lettor_Of_Credit_NOT_FOUND.toString());
	        }
	        LetterOfCredit LetterOfCredit = genson.deserialize(LetterOfCreditState, LetterOfCredit.class);
	        LetterOfCredit.setStatus(Status.ACCEPTED.toString());
   	        String newState = genson.serialize(LetterOfCredit);
   	        stub.putStringState(id, newState);

	        return LetterOfCredit;
	    }

    	@Transaction()
	    public LetterOfCredit viewLetterOfCreditDetails(final Context ctx, final String id) {
	        ChaincodeStub stub = ctx.getStub();
	        String LetterOfCreditState = stub.getStringState(id);
	        
	        if (LetterOfCreditState.isEmpty()) {
	            String errorMessage = String.format("Letter of Credit with id: %s does not exist.", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, TradeFinanceErrors.Lettor_Of_Credit_NOT_FOUND.toString());
	        }
	        LetterOfCredit LetterOfCredit = genson.deserialize(LetterOfCreditState, LetterOfCredit.class);

	        return LetterOfCredit;
	    }
}
