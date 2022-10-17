package com.mcb.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_type")
    private String  customerType;

    @Column(name = "customer_reference")
    private String customerReference;

    @Column(name = "customer_number")
    private long customerNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone_no")
    private long customerPhoneNumber;

    @Column(name = "customer_transfer_amount")
    private  double customerTransferAmount;

    @Column(name = "customer_transfer_currency")
    private String customerTransferCurrency;

    @Column(name = "beneficiary_bank")
    private String beneficiaryBank;

    @Column(name = "beneficiary_acc_no")
    private  long beneficiaryAccNo;

    @Column(name = "payment_details")
    private String paymentDetails;

    @Column(name = "credit_debit_card_details")
    private String creditDebitCardDetails;

    @Column(name = "region")
    private String region;

   

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(long customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public double getCustomerTransferAmount() {
        return customerTransferAmount;
    }

    public void setCustomerTransferAmount(double customerTransferAmount) {
        this.customerTransferAmount = customerTransferAmount;
    }

    public String getCustomerTransferCurrency() {
        return customerTransferCurrency;
    }

    public void setCustomerTransferCurrency(String customerTransferCurrency) {
        this.customerTransferCurrency = customerTransferCurrency;
    }

    public String getBeneficiaryBank() {
        return beneficiaryBank;
    }

    public void setBeneficiaryBank(String beneficiaryBank) {
        this.beneficiaryBank = beneficiaryBank;
    }

    public long getBeneficiaryAccNo() {
        return beneficiaryAccNo;
    }

    public void setBeneficiaryAccNo(long beneficiaryAccNo) {
        this.beneficiaryAccNo = beneficiaryAccNo;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getCreditDebitCardDetails() {
        return creditDebitCardDetails;
    }

    public void setCreditDebitCardDetails(String creditDebitCardDetails) {
        this.creditDebitCardDetails = creditDebitCardDetails;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customerType='" + customerType + '\'' +
                ", customerReference='" + customerReference + '\'' +
                ", customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber=" + customerPhoneNumber +
                ", customerTransferAmount=" + customerTransferAmount +
                ", customerTransferCurrency='" + customerTransferCurrency + '\'' +
                ", beneficiaryBank='" + beneficiaryBank + '\'' +
                ", beneficiaryAccNo=" + beneficiaryAccNo +
                ", paymentDetails='" + paymentDetails + '\'' +
                ", creditDebitCardDetails='" + creditDebitCardDetails + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

	public Transaction(int id, String customerType, String customerReference, long customerNumber, String customerName,
			long customerPhoneNumber, double customerTransferAmount, String customerTransferCurrency,
			String beneficiaryBank, long beneficiaryAccNo, String paymentDetails, String creditDebitCardDetails,
			String region) {
		super();
		this.id = id;
		this.customerType = customerType;
		this.customerReference = customerReference;
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerTransferAmount = customerTransferAmount;
		this.customerTransferCurrency = customerTransferCurrency;
		this.beneficiaryBank = beneficiaryBank;
		this.beneficiaryAccNo = beneficiaryAccNo;
		this.paymentDetails = paymentDetails;
		this.creditDebitCardDetails = creditDebitCardDetails;
		this.region = region;
	}
    
}