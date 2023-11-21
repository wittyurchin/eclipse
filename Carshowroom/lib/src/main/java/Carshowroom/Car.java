package Carshowroom;
 
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;
 
@DataType()
public final class Car {
 
	@Property()
	private final String id;
 
	@Property()
	private final String model;
 
	@Property()
	private final String owner;
 
	@Property()
	private final String value;
 
	public String getId() {
		return id;
	}
 
	public String getModel() {
		return model;
	}
	public String getOwner() {
		return owner;
	}
 
	public String getValue() {
		return value;
	}
 
	public Car(@JsonProperty("id") final String id, @JsonProperty("model") final String model, @JsonProperty("owner") final String owner,
			@JsonProperty("value") final String value) {
		this.id = id;
		this.model = model;
		this.owner = owner;
		this.value = value;
	}
 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
 
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
 
		Car other = (Car) obj;
 
		return Objects.deepEquals(new String[] { getId(), getModel(), getOwner(), getValue() },
				new String[] { other.getId(), other.getModel(), other.getOwner(), other.getValue() });
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getModel(), getOwner(), getValue());
	}
 
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + id + ", model=" + model
				+ ", owner=" + owner + ", value=" + value + "]";
	}
 
}
