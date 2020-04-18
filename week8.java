import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//don't forget to download and build-path to jsoup!!!

public class ReadingHTMLAssignment {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		//getHoroscope();
		getStockPrice();
		System.out.println("\nProgram ended.");
	}
	public static void getHoroscope() {
		// Disclaimer: I personally don't believe i astrology,
		//   just thought it would be a fun exercise
		// use this webpage if you don't know what your zodiac sign is:
		// https://www.astrology.com/horoscopes.html
		// Horoscope will be coming from this webpage:
		//    https://www.astrology.com/horoscope/daily-quickie/scorpio.html
		//        that is if you sign is scorpio. we will be inserting your sign.
		System.out.println("Your Horoscope for today:");
		System.out.print("What is your zodiac sign? (e.g. scorpio) ");
		String sign = in.nextLine();
		try {
			Document document = Jsoup.connect("https://www.astrology.com/horoscope/daily-quickie/" + sign + ".html").get();// horoscope
			//this is an easy one!  if you look at the line where the horoscope appears,
			//   you will see that it is proceeded by a tag <p>.
			Elements list = document.getElementsByTag("p");
			//for(Element element: list) {
				//System.out.println(element.text());
			//}
			System.out.println(list.get(0).text());
			//   It just so happens that it is the first one so,
			//        Create a variable of Elements type and fill with
			//            document  getElementsByTag   = "p"
			//        Print out the text of the first element.


			//System.out.println("Your horoscope should appear here!");
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getStockPrice() {
		//getStockPrice() will ask you for a stock symbol (e.g. gm = General Motors)
		//You will only be able to access New York Stock Exchange
		//   it will then ask if you want to see current price or web page
		//    if current price: load a webpage into DOCUMENT and get and display info
		//    if web page:  load webpage into BROWSER showing stock price

		//required variables:
		String name = "";
		double high = 0d;
		double low = 0d;
		double last = 0d;
		double change = 0d;
		double percentChange = 0d;
		int volume = 0;

		//get stock symbol
		System.out.println("Your stock prices:");
		System.out.print("Type stock code (e.g. gm) : ");
		String symbol = in.next().trim().toUpperCase();
		//get input for stock prices or show stock page
		System.out.print("Would you like to see the stock website? (y/n)");
		try {
			if (!in.next().toLowerCase().trim().equals("y")) { // just get data

				//I found that the data is readily accessible in this webpage
				//		that references the first letter of the stock symbol
				// http://eoddata.com/stocklist/NYSE/g.html  gives all stock that start with "g"

				// create Document using jsoup
				Document document = Jsoup
						.connect("http://eoddata.com/stocklist/NYSE/" + symbol.substring(0, 1) + ".htm").get();
                //---"http://eoddata.com/stocklist/NYSE/" + symbol.substring(0, 1) + ".htm"
				//The document stores all the lines of stocks in even and odd lines.
				//   The even lines have a class = "re"
				//       odd lines have a class = "ro"
				// so, create a variable of Elements type and get all elements from document
				//			where class = "re"
				Elements elements = document.getElementsByClass("re");
				elements.addAll(document.getElementsByClass("ro"));

				for(Element element: elements) {
					//System.out.println(element.text());
				   if(element.text().substring(0, symbol.length()+1).equals(symbol + " ")) {
					   String data = element.text();
					   data = data.substring(symbol.length()).trim();
					   char[] chars = data.toCharArray();
					   for(char chr: chars) {
						   if(Character.isDigit(chr)){
							   name = data.substring(0, data.indexOf(chr)).trim();
							   //System.out.println(name);
							   data = data.substring(name.length()).trim();
							   //System.out.println(data);
							   break;
						   }
					   }
					   String[] numbers = data.split(" ");
					   high = Double.parseDouble(numbers[0]);
					   low = Double.parseDouble(numbers[1]);
					   last = Double.parseDouble(numbers[2]);
					   volume = Integer.parseInt(numbers[3].replace(",", ""));

					   change = Double.parseDouble(numbers[4]);
					   percentChange = Double.parseDouble(numbers[5]);
					   //for(String num: numbers) {
						 //  System.out.println(num);
					   //}
				   }
				}
				// then use the elements.addAll to add all elements where class = "ro"
				//  e.g. Elements elements = document.getElementsByClass("re");
				//                elements.addAdd(document.getElementsByClass("ro"));
				// Now, we have a list of elements where each line has a line of text resembling this:
				//GM General Motors Company  37.22    36.45    36.99   6,890,105     0.73     2.01
				//symbol  -  name   -        high   -  low  -  close -  volume    - change - % change
				//
				//Now, we loop through the elements and look for the element's text
				//     String data = element.text();
				//     if elements.text() first letters = symbol  (e.g. "GM")
				//  when we find the correct one, String data = element.text();
				//e.g. data = "GM General Motors Company 37.22 36.45 36.99 6,890,105 0.73 2.01"
				//    remove the symbol from data
				//result: General Motors Company 37.22 36.45 36.99 6,890,105 0.73 2.01
				//    now we have to look for the first numeric value.
				//        Convert the text into an array of char[]  e.g.  char[] characters = data.toCharArray();
				//        Loop through the char array and look for digit  e.g. if(Character.isDigit(chr))
				//            now we know we have the first number (3)
				//            name = data.substring(0, data.indexOf(current char));
				//   remove name from data e.g. data = data.subString(name.length()).trim();
				//result: 37.22 36.45 36.99 6,890,105 0.73 2.01
				//   now we have a series of numbers separated by a single space
				//     so we can use split to create an array of strings String[] items = data.split(" ");
				//     each one of these items can be converted into a double and assigned to a variable created above
				//        e.g.  high = Double.parseDouble(items[0]);
				//     Volume is an issue because (1) it is an integer and (2) it has commas.
				//         first, remove the commas  e.g.  numbers[3] = numbers[3].replaceAll(",", "")
				//         next, convert into int type  e.g. volume = Integer.parseInteger(items[3]
				//   now you have all the variables filled, print statements below will print the results
				System.out.println(
						"symbol  name                                      high      low     last    volume     change  %change");
				System.out.printf("%-7s %-37s %8.2f %,8.2f %8.2f %,11d %8.2f %8.2f\n", symbol, name, high, low, last,
						volume, change, percentChange);
			} else { // open url for stock
				System.out.println("Opening up your browser!");
				Desktop desktop = java.awt.Desktop.getDesktop();
				URI uri = new URI("http://eoddata.com/stockquote/NYSE/" + symbol + ".htm");
				desktop.browse(uri);
			}
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
