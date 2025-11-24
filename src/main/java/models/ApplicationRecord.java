package models;

public class ApplicationRecord {
    private final String status;
    private final String referenceNumber;
    private final String applicationNumber;
    private final String permitType;
    private final String address;
    private final String date;
    private final String owner;
    private final String action;

    // Constructor sets the field
    public ApplicationRecord(String status, String referenceNumber, String applicationNumber,
            String permitType, String address, String date, String owner, String action) {
        this.status = status;
        this.referenceNumber = referenceNumber;
        this.applicationNumber = applicationNumber;
        this.permitType = permitType;
        this.address = address;
        this.date = date;
        this.owner = owner;
        this.action = action;
    }

    // public getters to access fields
    public String getStatus() {
        return status;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getPermitType() {
        return permitType;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public String getAction() {
        return action;
    }
}
