package ratechecker.shared.models;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Rate implements Serializable {

	private static final long serialVersionUID = -4415279469780082174L;

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private RateType type;

	@Persistent
	private Date timeFetched;

	/**
	 * The duration of the request to fetch the latest rate,
	 * for record keeping purpose.
	 */
	@Persistent
	private Long fetchDuration;

	@Persistent
	private Double rate;

	public Rate() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public RateType getType() {
		return type;
	}

	public void setType(final RateType type) {
		this.type = type;
	}

	public Date getTimeFetched() {
		return timeFetched;
	}

	public void setTimeFetched(final Date timeFetched) {
		this.timeFetched = timeFetched;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(final Double rate) {
		this.rate = rate;
	}

	public Long getFetchDuration() {
		return fetchDuration;
	}

	public void setFetchDuration(final long fetchDuration) {
		this.fetchDuration = fetchDuration;
	}
}
