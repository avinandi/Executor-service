package avirup.runner.tasks;

import avirup.runner.dto.CustomerQuery;
import avirup.runner.ws.WSTask;
import avirup.runner.ws.WSType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;

public class BillingRestOpenInvoiceSearchTask<T> extends WSTask<T>{

    public BillingRestOpenInvoiceSearchTask(String methodName, Map<String, Object> paramMap) {
        super(WSType.BILLING_API, methodName, paramMap);
    }

    public Map getOpenInvoice(final Map<String, Object> paramMap) throws IOException {
        String url;
        CustomerQuery query = (CustomerQuery) paramMap.get(WsTaskInputType.WS_INPUT);
        String baseUrl = (String) paramMap.get(WsTaskInputType.BASE_URI);

        if(query instanceof CustomerQuery.QueryWithSsId) {
            url = formUri(CustomerQuery.QueryType.SSID, query.getId(), baseUrl);
        } else {
            url = formUri(CustomerQuery.QueryType.FA, query.getId(), baseUrl);
        }
        return fetchResponseAsMap(url);
    }

    private Map fetchResponseAsMap(String url) throws IOException {
        ClientResponse response = Client.create().resource(url).accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        String entity = response.getEntity(String.class);
        return convertToMap(entity);
    }

    private Map convertToMap(String entity) throws IOException {
        return new ObjectMapper().readValue(entity, Map.class);
    }

    private String formUri(CustomerQuery.QueryType type, String id, String baseUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append(baseUrl);
        builder.append("/search/invoices/open/");
        builder.append(type.toString().toLowerCase());
        builder.append("/");
        builder.append(id);
        return builder.toString();
    }
}
