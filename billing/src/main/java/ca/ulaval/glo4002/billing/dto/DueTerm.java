package ca.ulaval.glo4002.billing.dto;

public enum DueTerm {
	IMMEDIATE("IMMEDIATE"), DAYS30("DAYS30"), DAYS90("DAYS90");

	private final String name;

	private DueTerm(String _name) {
		this.name = _name;
	}

	public String toString() {
		return this.name;
	}

	public static DueTerm getDueTermFromString(String _dueTerm) {
		DueTerm dueTerm;
		switch (_dueTerm) {
		case "IMMENDIATE":
			dueTerm = DueTerm.IMMEDIATE;
			break;
		case "DAYS30":
			dueTerm = DueTerm.DAYS30;
			break;
		case "DAYS90":
			dueTerm = DueTerm.DAYS90;
			break;
		default:
			dueTerm = DueTerm.IMMEDIATE;
			break;
		}
		return dueTerm;
	}
}
