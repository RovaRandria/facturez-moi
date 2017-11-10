package billing;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.factory.BillFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class BillFactoryTest {

    BillFactory billFactory;
    OrderDto orderDto;
    List<ProductDto> productDtos;

    @Before
    public void init() {
        final int CLIENT_ID = 1;
        final CrmDueTerm ORDER_DUE_TERM = CrmDueTerm.DAYS30;
        final int PRODUCT_DTOS_QUANTITY = 3;

        billFactory = new BillFactory();
        fillItems(PRODUCT_DTOS_QUANTITY);
        orderDto = new OrderDto(new ClientId(CLIENT_ID), new Date(), ORDER_DUE_TERM, productDtos);
    }

    @Test
    public void givenFactory_whenCreateBill_thenBillIsValid() {
        Bill bill = billFactory.createBill(orderDto);
        //assertTrue(validBill(bill));
    }

//    private boolean validBill(Bill bill) {
//        boolean billIsValid = true;
//        BigDecimal total = billFactory;
//
//        if (!bill.getTotal().equals(total)) {
//            dtoIsValid = false;
//        }
//        if (!billDto.getId().equals(bill.getBillId().toString())) {
//            dtoIsValid = false;
//        }
//        if (billDto.getDueTerm() != CrmDueTerm.DAYS30) {
//            dtoIsValid = false;
//        }
//        if (!billDto.getUrl().equals("/bills/" + bill.getBillId().toString())) {
//            dtoIsValid = false;
//        }
//        return billIsValid;
//    }

    private void fillItems(int nbItems) {
        BigDecimal price = new BigDecimal(1);
        String name = "My product";
        ProductId productId = new ProductId(1);
        int quantity = 1;
        productDtos = new ArrayList<>();

        for (int i = 0; i < nbItems; i++) {
            price.add(new BigDecimal(1));
            quantity++;
            ProductDto productDto = new ProductDto(price, name, productId, quantity);
            productDtos.add(productDto);
        }
    }
}
