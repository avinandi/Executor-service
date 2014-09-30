package avirup.runner.main;

import avirup.runner.dao.OpenInvoiceSearchDAO;
import avirup.runner.dao.OpenInvoiceSearchWSDAOImpl;
import avirup.runner.dto.CustomerQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author Avirup
 */
public class LoadTest {
    public static class TestForOneQuery {
        public static void main(String[] args) {
            OpenInvoiceSearchDAO searchDao = new OpenInvoiceSearchWSDAOImpl();
            Map result = searchDao.getOpenInvoice(new CustomerQuery.QueryWithSsId("130386-138E"));
            System.out.print(result);
        }
    }

    public static class TestForMultipleQueries {
        public static void main(String[] args) {
            List<String> faIds = Arrays.asList("260326199", "260390305");
            List<CustomerQuery> customerQueries = new ArrayList<CustomerQuery>();
            for(String fa: faIds) {
                customerQueries.add(new CustomerQuery.QueryWithFaId(fa));
            }
            OpenInvoiceSearchDAO searchDao = new OpenInvoiceSearchWSDAOImpl();
            List<Map> result = searchDao.getOpenInvoices(customerQueries);
            System.out.print(result);
        }
    }
}
