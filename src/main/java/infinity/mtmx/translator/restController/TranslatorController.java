package infinity.mtmx.translator.restController;

import com.prowidesoftware.swift.io.parser.SwiftParser;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import com.prowidesoftware.swift.utils.Lib;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
public class TranslatorController {

//    private SwiftMessageParser swiftMessageParser;
//
//    TranslatorController(SwiftMessageParser swiftMessageParser) {
//
//        this.swiftMessageParser = swiftMessageParser;
//
//    }

    @PostMapping("/translate")
    public ResponseEntity<String> getTranslatedMessage(@RequestBody String message) throws IOException {
        String swiftMessage = "{1:F01BANKBEBBAXXX1234567890}{2:O1031130050901BANKBEBBAXXX12345678900509011311N}{3:{108:MT103}}"
                + "{4:"
                + ":20:REFERENCE12345"
                + ":23B:CRED"
                + ":32A:230501EUR123456,78"
                + ":50A:/12345678901234567890\nMR. JOHN DOE"
                + ":59:/23456789012345678901\nMS. JANE SMITH"
                + ":70:INVOICE 987654"
                + ":71A:SHA"
                + "}{5:{CHK:123456789ABC}}";
try{
    testMT103();
}catch (Exception e){
    System.out.println(e.getMessage());
}



            SwiftParser parser = new SwiftParser(swiftMessage);
        SwiftMessage message1 = parser.message();
//        System.out.println("Block 1: " + message1.getBlock1());
//        System.out.println("Block 2: " +  message1.getBlock2());
//        System.out.println("Block 3: " + message1.getBlock3());
//        System.out.println("Block 4: "+ message1.getBlock4());
////        message1.parseBlock4Fields();
//        System.out.println("Block 5: " + message1.getBlock5());
        return ResponseEntity.ok(message1.toJson());

    }

    public void testMT103() throws IOException, JAXBException, JAXBException {
        /*
         * Read and parse the file content into a SWIFT message object
         * Parse from File could also be used here
         */
        MT103 mt = MT103.parse(Lib.readResource("mt103.txt", null));

        /*
         * Print header information
         */
//        System.out.println("Sender: " + mt.getSender());
//        System.out.println("Receiver: " + mt.getReceiver());

        /*
         * Print details of a specific fields
         */
//        Field20 ref = mt.getField20();
//        System.out.println(Field.getLabel(ref.getName(), mt.getMessageType(), null) + ": " + ref.getComponent(Field20.REFERENCE));


        //creating pacs message
        Pacs008 pacs008 = new Pacs008();
        GroupHeader grpHdr = new GroupHeader();
        Field20 ref = mt.getField20();
        grpHdr.setMsgId( ref.getComponent(Field20.REFERENCE));
        grpHdr.setCreDtTm("2023-05-01T11:30:00");
        pacs008.setGrpHdr(grpHdr);

        CreditTransferTransaction cdtTrfTxInf = new CreditTransferTransaction();

        Field32A f = mt.getField32A();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        System.out.println("Value Date: " + sdf.format(f.getDateAsCalendar().getTime()));
//        System.out.println("Amount: " + f.getCurrency() + " " + f.getAmount());
        Amount amt = new Amount();
        amt.setCurrency(f.getCurrency());
        amt.setValue(f.getAmount());
        cdtTrfTxInf.setAmt(amt);


        Debtor dbtr = new Debtor();
        Field59 field59 = mt.getField59();

        dbtr.setDebtorName(field59.getName());
        dbtr.setDebtorAccNumber(field59.getAccount());
        cdtTrfTxInf.setDbtr(dbtr);

        Creditor cdtr = new Creditor();
        cdtr.setCreditorName("MS. JANE SMITH");
        cdtr.setCreditorAccNumber("23456789012345678901");
        cdtTrfTxInf.setCdtr(cdtr);

        pacs008.setCdtTrfTxInf(cdtTrfTxInf);

        // Serialize to XML
        JAXBContext context = JAXBContext.newInstance(Pacs008.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(pacs008, System.out);
    }

}
