package avirup.runner.dto;

/**
 * @Author Avirup
 */
public class CustomerQuery {
    private String id;

    protected CustomerQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static class QueryWithSsId extends CustomerQuery {
        public QueryWithSsId(String ssId) {
            super(ssId);
        }
    }

    public static class QueryWithFaId extends CustomerQuery {
        public QueryWithFaId(String faId) {
            super(faId);
        }
    }

    public enum QueryType {
        SSID, FA;
    }
}
