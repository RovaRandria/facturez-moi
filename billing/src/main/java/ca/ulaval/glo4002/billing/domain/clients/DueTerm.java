package ca.ulaval.glo4002.billing.domain.clients;

import javax.persistence.Embeddable;

@Embeddable
public enum DueTerm {
	IMMEDIATE, DAYS30, DAYS90
}
