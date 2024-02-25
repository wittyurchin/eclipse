package tradefinance;
 
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;
 
@DataType()
public final class LetterOfCredit {
 
	@Property()
	private String id;
 
	@Property()
	private String expiryDate;
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Property()
	private String buyer;
 
	@Property()
	private String bank;
	
	@Property()
	private String seller;
	
	
	@Property()
	private String amount;
	
	@Property()
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	public LetterOfCredit(@JsonProperty("id") final String id, @JsonProperty("expiryDate") final String expiryDate, 
			@JsonProperty("buyer") final String buyer,
			@JsonProperty("bank") final String bank, @JsonProperty("seller") final String seller, 
			@JsonProperty("amount") final String amount, @JsonProperty("status") final String status) {
		this.id = id;
		this.expiryDate = expiryDate;
		this.buyer = buyer;
		this.bank = bank;
		this.seller = seller;
		this.amount = amount;
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, expiryDate, buyer, bank, seller, amount, status);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + id 
				+ ", expiryDate=" + expiryDate
				+ ", buyer=" + buyer + ", bank=" + bank 
				+ ", seller=" + seller + ", amount=" + amount
				+ ", status=" + status
				+ "]";
	}
}
