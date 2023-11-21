package Farmsupplychain;
 
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
        name = "SupplyChain",
        info = @Info(
                title = "Supply chain contract",
                description = "A Sample supply chain chaincode example",
                version = "0.0.1-SNAPSHOT"))
 
 
@Default
public final class SupplyChain implements ContractInterface {
 
	private final Genson genson = new Genson();
	private enum SupplyChainErrors {
	        Farm_Product_NOT_FOUND,
	        Farm_Product_ALREADY_EXISTS
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
    public FarmProduct addNewFarmProduct(final Context ctx, final String id, final String description,
            final String producerName, final String producerAddress, final String harvestDate) {
        
    	ChaincodeStub stub = ctx.getStub();
 
        String FarmProductState = stub.getStringState(id);
        
        if (!FarmProductState.isEmpty()) {
            String errorMessage = String.format("Car %s already exists", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, SupplyChainErrors.Farm_Product_NOT_FOUND.toString());
        }
        
        FarmProduct FarmProduct = new FarmProduct(id, description, producerName,producerAddress, harvestDate);
        
        FarmProductState = genson.serialize(FarmProduct);
        
        stub.putStringState(id, FarmProductState); 
        
        return FarmProduct;
    }
 
 

    	@Transaction()
	    public String transferToDistributor(final Context ctx, final String id, final String distributorName, final String distributorAddress, final String transferDate) {
	        ChaincodeStub stub = ctx.getStub();
	        String FarmProductState = stub.getStringState(id);
 
	        if (FarmProductState.isEmpty()) {
	            String errorMessage = String.format("Farm product %s does not exist4", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, SupplyChainErrors.Farm_Product_NOT_FOUND.toString());
	        }
	        FarmProduct FarmProduct = genson.deserialize(FarmProductState, FarmProduct.class);
	        FarmProduct.setDistributorName(distributorName);
	        FarmProduct.setDistributorAddress(distributorAddress);
	        FarmProduct.setProdToDistDate(transferDate);
   	        String newState = genson.serialize(FarmProduct);
   	        stub.putStringState(id, newState);

	        return id;
	    }
    	
    	@Transaction()
	    public String transferToRetailer(final Context ctx, final String id, final String retailerName, final String retailerAddress, final String transferDate) {
	        ChaincodeStub stub = ctx.getStub();
	        String FarmProductState = stub.getStringState(id);
 
	        if (FarmProductState.isEmpty()) {
	            String errorMessage = String.format("Farm product %s does not exist4", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, SupplyChainErrors.Farm_Product_NOT_FOUND.toString());
	        }
	        FarmProduct FarmProduct = genson.deserialize(FarmProductState, FarmProduct.class);
	        FarmProduct.setRetailerName(retailerName);
	        FarmProduct.setRetailerAddress(retailerAddress);
	        FarmProduct.setDistToRetaDate(transferDate);
   	        String newState = genson.serialize(FarmProduct);
   	        stub.putStringState(id, newState);

	        return id;
	    }

    	@Transaction()
	    public FarmProduct viewFarmProductDetails(final Context ctx, final String id) {
	        ChaincodeStub stub = ctx.getStub();
	        String FarmProductState = stub.getStringState(id);
 
	        if (FarmProductState.isEmpty()) {
	            String errorMessage = String.format("Farm product %s does not exist4", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, SupplyChainErrors.Farm_Product_NOT_FOUND.toString());
	        }
	        FarmProduct FarmProduct = genson.deserialize(FarmProductState, FarmProduct.class);

	        return FarmProduct;
	    }
}
