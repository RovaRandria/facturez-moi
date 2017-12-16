package ca.ulaval.glo4002.billing.domain.clients;

public enum DueTerm {
  IMMEDIATE, DAYS30, DAYS90;

  public static int toInt(DueTerm dueTerm) {
    int day = 0;

    switch (dueTerm) {
    case IMMEDIATE:
      day = 0;
    case DAYS30:
      day = 30;
      break;
    case DAYS90:
      day = 90;
      break;
    default:
      break;
    }
    return day;
  }
}
