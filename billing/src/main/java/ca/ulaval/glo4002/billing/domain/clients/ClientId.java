package ca.ulaval.glo4002.billing.domain.clients;

public class ClientId {
	private long id;

	public ClientId(long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return Long.toString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientId clientId = (ClientId) o;

        return id == clientId.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
