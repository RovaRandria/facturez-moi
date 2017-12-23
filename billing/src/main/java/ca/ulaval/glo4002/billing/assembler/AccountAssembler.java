package ca.ulaval.glo4002.billing.assembler;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.dto.AccountDto;
import ca.ulaval.glo4002.billing.dto.TransactionDto;

public class AccountAssembler {

  public AccountDto create(AccountId accountId, List<Transaction> entries) {
    List<TransactionDto> entriesDto = new ArrayList<TransactionDto>();
    for (Transaction transaction : entries) {
      entriesDto.add(new TransactionDto(transaction.getDate(), transaction.getTransactionType(),
          transaction.getClient().getClientId(), transaction.getTypeOperation(), transaction.getAmount(),
          transaction.getBalance()));
    }
    return new AccountDto(accountId.getId(), entriesDto);
  }

}
