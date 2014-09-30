package avirup.runner.dto;

/**
 * @Author Avirup
 */
public class Invoice {
    Double invoiceNumber;
    Double faId;

    public Invoice(String invoiceNumber, String faId) {
        this.invoiceNumber = Double.parseDouble(invoiceNumber);
        this.faId = Double.parseDouble(faId);
    }
}
