package Farmsupplychain;
 
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;
 
@DataType()
public final class FarmProduct {
 
	@Property()
	private final String productId;
 
	@Property()
	private final String productDescription;
 
	@Property()
	private final String producerName;
 
	@Property()
	private final String producerAddress;
	
	@Property()
	private final String harvestDate;
	
	
	@Property()
	private String distributorName;
	
	@Property()
	private String distributorAddress;
	
	@Property()
	private String prodToDistDate;
	
	@Property()
	private String retailerName;
	
	@Property()
	private String retailerAddress;
	
	@Property()
	private String distToRetaDate;

	public String getProductId() {
		return productId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProducerAddress() {
		return producerAddress;
	}

	public String getHarvestDate() {
		return harvestDate;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public String getDistributorAddress() {
		return distributorAddress;
	}

	public String getProdToDistDate() {
		return prodToDistDate;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public String getRetailerAddress() {
		return retailerAddress;
	}

	public String getDistToRetaDate() {
		return distToRetaDate;
	}
	
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public void setDistributorAddress(String distributorAddress) {
		this.distributorAddress = distributorAddress;
	}

	public void setProdToDistDate(String prodToDistDate) {
		this.prodToDistDate = prodToDistDate;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public void setRetailerAddress(String retailerAddress) {
		this.retailerAddress = retailerAddress;
	}

	public void setDistToRetaDate(String distToRetaDate) {
		this.distToRetaDate = distToRetaDate;
	}

	public FarmProduct(@JsonProperty("id") final String id, @JsonProperty("product-description") final String productDescription, 
			@JsonProperty("producer-name") final String producerName,
			@JsonProperty("producer-address") final String producerAddress, @JsonProperty("harvest-date") final String harvestDate, 
			@JsonProperty("distributor-name") final String distributorName, @JsonProperty("distributor-address") final String distributorAddress, 
			@JsonProperty("prod-to-dist-date") final String prodToDistDate, @JsonProperty("retailer-name") final String retailerName,
			@JsonProperty("retailer-address") final String retailerAddress, @JsonProperty("dist-to-reta-date") final String distToRetaDate) {
		this.productId = id;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;

		this.distributorName = distributorName;

		this.distributorAddress = distributorAddress;
		
		this.prodToDistDate = prodToDistDate;

		this.retailerName = retailerName;

		this.retailerAddress = retailerAddress;

		this.distToRetaDate = distToRetaDate;

	}
	
	public FarmProduct(final String id, final String productDescription, 
			 final String producerName,
			 final String producerAddress, final String harvestDate) {
		this.productId = id;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProductId(), getProductDescription(), getProducerName(), getProducerAddress(), getHarvestDate(), getDistributorName(), 
				getDistributorAddress(), getProdToDistDate(), getRetailerName(), getRetailerAddress(), getDistToRetaDate());
	}
 
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + productId 
				+ ", productDescription=" + productDescription
				+ ", producerName=" + producerName + ", producerAddress=" + producerAddress 
				+ ", harvestDate=" + harvestDate + ", distributorName=" + distributorName
				+ ", distributorAddress=" + distributorAddress + ", prodToDistDate=" + prodToDistDate
				+ ", retailerName=" + retailerName + ", retailerAddress=" + retailerAddress
				+ ", distToRetaDate=" + distToRetaDate
				+ "]";
	}
}
