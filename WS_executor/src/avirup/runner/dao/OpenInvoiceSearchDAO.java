package avirup.runner.dao;

import avirup.runner.dto.CustomerQuery;

import java.util.List;
import java.util.Map;

/**
 * @Author Avirup
 */
public interface OpenInvoiceSearchDAO {
    Map getOpenInvoice(final CustomerQuery query);

    List<Map> getOpenInvoices(final List<CustomerQuery> queries);
}
