import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import javax.print.PrintService;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Destination;
import javax.swing.*;

public class BillTest extends JFrame{

    String bookingId="";
    String senderName = "";
    Long senderPincode = 0L;
    String senderDistrict ="";
    String senderPostOffice = "";
    String receiverName = "";
    Long receiverPincode = 0L;
    String receiverDistrict ="";
    String receiverPostOffice = "";
    float weight = 0.0f;
    float totalAmount = 0.0f;


    public void run() throws IOException {
        initComponents();
        PrinterJob pj = PrinterJob.getPrinterJob();
        try {
            Path outputFile = Files.createTempFile(Paths.get(System.getProperty("user.home")), bookingId, ".pdf");
            String fileName = String.valueOf(outputFile);
            Doc doc = new SimpleDoc(new BillPrintable(), DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new Destination(outputFile.toUri()));
            PrintService service = pj.getPrintService();
            DocPrintJob job = service.createPrintJob();
            job.print(doc, attributes);
            File myFile = new File(fileName);
            Desktop.getDesktop().open(myFile);

        } catch (PrintException ex) {

            ex.printStackTrace();
        }
    }

    private void initComponents() {
    }


    public static PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }


    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }


    public class BillPrintable implements Printable {


        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            ImageIcon icon = new ImageIcon("C:\\Users\\SWAROOP\\IdeaProjects\\Post Office Management System\\india_post_logo (1).jpg");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());


                  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;


                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawImage(icon.getImage(), 70, 20, 90, 30, rootPane);
                    y += yShift + 30;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("         Indian Post Service        ", 12, y);
                    y += yShift;
                    g2d.drawString("    No 00000 Address Line One ****", 12, y);
                    y += yShift;
                    g2d.drawString("    Address Line 02 India abc ****", 12, y);
                    y += yShift;
                    g2d.drawString("    <Track on www.indiapost.gov.in> ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;


                    g2d.drawString( "Booking Id: " + bookingId, 20, y);
                    y += yShift;
                    g2d.drawString("To: " + receiverName + ", "+receiverDistrict+", "+ "PIN: " + receiverPincode, 20, y);
                    y += yShift;
                    g2d.drawString(  "Post Office: " + receiverPostOffice + ", "+receiverDistrict, 20, y);
                    y += yShift;
                    g2d.drawString("From: " + senderName + ", "+senderDistrict+", "+ "PIN: " + senderPincode, 20, y);
                    y += yShift;
                    g2d.drawString( "Post Office: " + senderPostOffice + ", "+senderDistrict, 20, y);
                    y += yShift;
                    g2d.drawString("Weight : " + weight +"gram", 20, y);
                    y += yShift;

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Total amount:               " + totalAmount + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("          <Dial 18002666868>     ", 12, y);
                    y += yShift;
                    g2d.drawString("         <Wear Mask,Stay Safe>     ", 12, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("            THANK YOU                ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("   CONTACT: contact@postservice.com       ", 10, y);
                    y += yShift;


                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }
}