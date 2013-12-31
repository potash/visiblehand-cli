import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import visiblehand.VisibleHand;
import visiblehand.entity.air.AirReceipt;
import visiblehand.entity.air.Flight;
import visiblehand.parser.air.AirParser;

public class VisibleHandCLI {
	static final Logger logger = LoggerFactory.getLogger(VisibleHandCLI.class);

	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException {
		VisibleHand.initEbean();
		Folder inbox = VisibleHand.getInbox();
		List<Flight> flights = new ArrayList<Flight>();

		for (AirParser parser : VisibleHand.airParsers) {
			if (parser.isActive()) {
				for (Message message : inbox.search(parser.getSearchTerm())) {
					logger.info(message.getSubject());
					try {
						AirReceipt receipt = parser.parse(message);
						flights.addAll(receipt.getFlights());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		VisibleHand.printStatistics(flights);
		inbox.close(false);
	}
}
