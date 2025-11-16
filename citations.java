import java.util.Scanner;
import java.util.Arrays;

public class citations {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("MLA Citation Helper v1 - choose a source type (book, website, journal, video, exit):");

		while (true) {
			System.out.print("Enter source type: ");
			String type = in.nextLine().trim().toLowerCase();
			if (type.equals("exit")) {
				System.out.println("Goodbye!");
				break;
			}

			switch (type) {
				case "book":
					handleBook(in);
					break;
				case "website":
				case "url":
					handleWebsite(in);
					break;
				case "journal":
				case "article":
					handleJournal(in);
					break;
				case "video":
				case "youtube":
					handleVideo(in);
					break;
				default:
					System.out.println("Unknown type. Supported types: book, website, journal, video, exit.");
			}
			System.out.println();
		}

		in.close();
	}

	private static String prompt(Scanner in, String label) {
		System.out.print(label + ": ");
		return in.nextLine().trim();
	}

	private static String formatAuthors(String authorsRaw) {
		if (authorsRaw == null || authorsRaw.isEmpty()) return "";
		String[] parts = authorsRaw.split(",");
		for (int i = 0; i < parts.length; i++) {
			parts[i] = parts[i].trim();
		}

		if (parts.length == 1) {
			return mlalastfirst(parts[0]) + ".";
		} else if (parts.length == 2) {
			String first = mlalastfirst(parts[0]);
			if (first.endsWith(".")) first = first.substring(0, first.length()-1);
			return first + ", and " + parts[1] + ".";
		} else {
			String first = mlalastfirst(parts[0]);
			if (first.endsWith(".")) first = first.substring(0, first.length()-1);
			return first + ", et al.";
		}
	}

	private static String mlalastfirst(String name) {
		if (name.contains(",")) {
			return name;
		}
		String[] tokens = name.split(" ");
		if (tokens.length == 1) return name; // single-word name
		String last = tokens[tokens.length-1];
		String first = String.join(" ", Arrays.copyOf(tokens, tokens.length-1));
		return last + ", " + first;
	}

	private static void handleBook(Scanner in) {
		String authors = prompt(in, "Author(s) (comma-separated, e.g. First Last, Second Last - leave empty if none)");
		String title = prompt(in, "Title of Book (italicize manually if needed)");
		String publisher = prompt(in, "Publisher");
		String year = prompt(in, "Year of publication");

		String authorPart = formatAuthors(authors);
		String citation = "";
		if (!authorPart.isEmpty()) citation += authorPart + " ";
		citation += title + ". " + publisher + ", " + year + ".";
		System.out.println("MLA citation:\n" + citation);
	}

	private static void handleWebsite(Scanner in) {
		String authors = prompt(in, "Author(s) (leave empty if none)");
		String title = prompt(in, "Title of page or article (in quotation marks)");
		String siteName = prompt(in, "Website name");
		String publisher = prompt(in, "Publisher (or same as website name; leave empty if unknown)");
		String pubDate = prompt(in, "Date of publication (e.g., 16 Nov. 2025)");
		String url = prompt(in, "URL");
		String accessDate = prompt(in, "Date you accessed it (e.g., 16 Nov. 2025)");

		String authorPart = formatAuthors(authors);
		String citation = "";
		if (!authorPart.isEmpty()) citation += authorPart + " ";
		citation += "\"" + title + "\". " + siteName;
		if (!publisher.isEmpty() && !publisher.equalsIgnoreCase(siteName)) citation += ", " + publisher;
		if (!pubDate.isEmpty()) citation += ", " + pubDate;
		citation += ", " + url + ".";
		if (!accessDate.isEmpty()) citation += " Accessed " + accessDate + ".";

		System.out.println("MLA citation:\n" + citation);
	}

	private static void handleJournal(Scanner in) {
		String authors = prompt(in, "Author(s)");
		String articleTitle = prompt(in, "Title of article (in quotation marks)");
		String journalTitle = prompt(in, "Journal title (italicized manually)");
		String vol = prompt(in, "Volume (e.g., 10)");
		String issue = prompt(in, "Issue number (e.g., 2)");
		String year = prompt(in, "Year");
		String pages = prompt(in, "Pages (e.g., 12-27)");

		String authorPart = formatAuthors(authors);
		String citation = "";
		if (!authorPart.isEmpty()) citation += authorPart + " ";
		citation += "\"" + articleTitle + "\" " + journalTitle;
		if (!vol.isEmpty()) citation += ", vol. " + vol;
		if (!issue.isEmpty()) citation += ", no. " + issue;
		if (!year.isEmpty()) citation += ", " + year;
		if (!pages.isEmpty()) citation += ", pp. " + pages + ".";

		System.out.println("MLA citation:\n" + citation);
	}

	private static void handleVideo(Scanner in) {
		String author = prompt(in, "Creator/author or uploader");
		String title = prompt(in, "Title of video (in quotation marks)");
		String platform = prompt(in, "Platform (e.g., YouTube)");
		String uploader = prompt(in, "Uploader (leave blank if same as creator)");
		String date = prompt(in, "Date of publication (e.g., 16 Nov. 2025)");
		String url = prompt(in, "URL");

		String authorPart = formatAuthors(author);
		String citation = "";
		if (!authorPart.isEmpty()) citation += authorPart + " ";
		citation += "\"" + title + "\" " + platform;
		if (!uploader.isEmpty() && !uploader.equals(author)) citation += ", uploaded by " + uploader;
		if (!date.isEmpty()) citation += ", " + date;
		citation += ", " + url + ".";

		System.out.println("MLA citation:\n" + citation);
	}
}

