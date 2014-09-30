package avirup.runner.dao;

import avirup.runner.dto.CustomerQuery;
import avirup.runner.tasks.BillingRestOpenInvoiceSearchTask;
import avirup.runner.ws.WSExecutor;
import avirup.runner.ws.WSTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Avirup
 */
public class OpenInvoiceSearchWSDAOImpl implements OpenInvoiceSearchDAO {
    private static final String BASE_URI = "http://localhost:33333";

    /**
     * Example for single search query
     * @param query
     * @return
     */
    @Override
    public Map getOpenInvoice(final CustomerQuery query) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(WSTask.WsTaskInputType.WS_INPUT, query);
        paramMap.put(WSTask.WsTaskInputType.BASE_URI, BASE_URI);

        WSTask<Map> task = new BillingRestOpenInvoiceSearchTask<Map>("getOpenInvoice", paramMap);

        try {
            WSExecutor.INSTANCE.addTaskToQueueAndWait(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return task.getResult();
    }

    /**
     * Example for multiple search query
     * @param queries
     * @return
     */
    @Override
    public List<Map> getOpenInvoices(final List<CustomerQuery> queries) {
        WSTask<Map>[] taskArray = new BillingRestOpenInvoiceSearchTask[queries.size()];

        int counter = 0;
        for(CustomerQuery query: queries) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(WSTask.WsTaskInputType.WS_INPUT, query);
            paramMap.put(WSTask.WsTaskInputType.BASE_URI, BASE_URI);

            WSTask<Map> task = new BillingRestOpenInvoiceSearchTask<Map>("getOpenInvoice", paramMap);
            taskArray[counter++] = task;
        }

        try {
            WSExecutor.INSTANCE.addTaskToQueueAndWait(taskArray);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        counter = 0;
        List<Map> listOfInvoice = new ArrayList<Map>();
        for(CustomerQuery query: queries) {
            Map invoice = taskArray[counter++].getResult();
            listOfInvoice.add(invoice);
        }
        return listOfInvoice;
    }
}
