package infinity.mtmx.translator.restController;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "FIToFICstmrCdtTrf")
@XmlAccessorType(XmlAccessType.FIELD)

public class Pacs008 {
    @XmlElement(name = "GrpHdr")
    private GroupHeader grpHdr;

    @XmlElement(name = "CdtTrfTxInf")
    private CreditTransferTransaction cdtTrfTxInf;

    public GroupHeader getGrpHdr() {
        return grpHdr;
    }

    public void setGrpHdr(GroupHeader grpHdr) {
        this.grpHdr = grpHdr;
    }

    public CreditTransferTransaction getCdtTrfTxInf() {
        return cdtTrfTxInf;
    }

    public void setCdtTrfTxInf(CreditTransferTransaction cdtTrfTxInf) {
        this.cdtTrfTxInf = cdtTrfTxInf;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)

class GroupHeader {
    @XmlElement(name = "MsgId")
    private String msgId;

    @XmlElement(name = "CreDtTm")
    private String creDtTm;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCreDtTm() {
        return creDtTm;
    }

    public void setCreDtTm(String creDtTm) {
        this.creDtTm = creDtTm;
    }
}
@Getter @Setter
@XmlAccessorType(XmlAccessType.FIELD)
class CreditTransferTransaction {
    @XmlElement(name = "Dbtr")
    private Debtor dbtr;

    @XmlElement(name = "Cdtr")
    private Creditor cdtr;

    @XmlElement(name = "Amt")
    private Amount amt;

    @XmlElement(name = "RmtInf")
    private RemittanceInformation rmtInf;

    // Getters and setters...

    public Debtor getDbtr() {
        return dbtr;
    }

    public void setDbtr(Debtor dbtr) {
        this.dbtr = dbtr;
    }

    public Creditor getCdtr() {
        return cdtr;
    }

    public void setCdtr(Creditor cdtr) {
        this.cdtr = cdtr;
    }

    public Amount getAmt() {
        return amt;
    }

    public void setAmt(Amount amt) {
        this.amt = amt;
    }

    public RemittanceInformation getRmtInf() {
        return rmtInf;
    }

    public void setRmtInf(RemittanceInformation rmtInf) {
        this.rmtInf = rmtInf;
    }
}

// Define classes for Debtor, Creditor, Amount, etc.
@XmlAccessorType(XmlAccessType.FIELD)
class Debtor{
    @XmlElement(name = "dbtrName")
    private String debtorName;
    @XmlElement(name = "dbtrAcc")
    private String debtorAccNumber;

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorAccNumber() {
        return debtorAccNumber;
    }

    public void setDebtorAccNumber(String debtorAccNumber) {
        this.debtorAccNumber = debtorAccNumber;
    }
}
@XmlAccessorType(XmlAccessType.FIELD)

class Creditor{
    @XmlElement(name = "cdtrName")
    private String creditorName;
    @XmlElement(name = "cdtrAcc")
    private String creditorAccNumber;

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorAccNumber() {
        return creditorAccNumber;
    }

    public void setCreditorAccNumber(String creditorAccNumber) {
        this.creditorAccNumber = creditorAccNumber;
    }
}
@XmlAccessorType(XmlAccessType.FIELD)

class Amount{
    @XmlElement(name = "Currency")
    private String currency;
    @XmlElement(name = "Value")
    private String value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
@XmlAccessorType(XmlAccessType.FIELD)
class RemittanceInformation{

}