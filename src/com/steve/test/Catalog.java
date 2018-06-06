/*
 * Did not have a database available, so I used a key sorted map.
 */

package com.steve.test;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Catalog {

	public static final int VIEW_ALL_BOOKS = 1;
	public static final int ADD_A_BOOK = 2;
	public static final int EDIT_A_BOOK = 3;
	public static final int SEARCH_FOR_A_BOOK = 4;
	public static final int SAVE_AND_EXIT = 5;
	
	private Map<Integer, Book> books = null;
	private boolean runFlag = false;
	
	public Catalog(String[] args) throws Exception {
		init(args);
	}
	
	public static void main(String[] args) throws Exception {
		
		Catalog catalog  = new Catalog(args);
		catalog.process();
	}
	
	private void process() {
		
		runFlag = true;
		while(runFlag) {
			try {
				clearConsole();
				showMainMenu();
				int i = promptMainMenu();
				switch(i) {
				case VIEW_ALL_BOOKS:
					viewBooks(true);
					break;
				case ADD_A_BOOK:
					addBook();
					break;
				case EDIT_A_BOOK:
					viewBooks(false);
					break;
				case SEARCH_FOR_A_BOOK:
					searchBooks();
					break;
				case SAVE_AND_EXIT:
					save();
					runFlag = false;
					break;
				}
			} catch (Exception e) {
				if (e instanceof SQLException) {
					System.err.println("SQL State: " + ((SQLException) e).getSQLState() + " SQL Error Code: " + ((SQLException) e).getErrorCode());
				} else {
					System.err.println("Message: " + e.getMessage());
				}
				e.printStackTrace();
				runFlag = false;
			}
		}
	}
	
	private void showMainMenu() {
		
		ConsoleHelper.printTitle("Welcome to the Library Catalog");
		ConsoleHelper.printMessage("\n");
		ConsoleHelper.printOption(VIEW_ALL_BOOKS, "View all books");
		ConsoleHelper.printOption(ADD_A_BOOK, "Add a book");
		ConsoleHelper.printOption(EDIT_A_BOOK, "Edit a book");
		ConsoleHelper.printOption(SEARCH_FOR_A_BOOK, "Search for a book");
		ConsoleHelper.printOption(SAVE_AND_EXIT, "Save and exit");
		ConsoleHelper.printMessage("\n");
		
		
	}
	
	private int promptMainMenu() {
		
		ConsoleHelper.printPrompt("Choose [1-5] ");
		
		int i = 0;
		while(true) {
		    i = ConsoleHelper.getInt(false);
		    if (i >= 1 && i <= 5) {
		    	break;
		    } else {
		    	System.err.print("\nEnter a number 1-5 ");
		    }
		}
		return i;
	}
	
	private void viewBooks(boolean readOnly) {
		
		int lastId = 0;
		if (readOnly) {
		    ConsoleHelper.printTitle("View Books");
		} else {
			ConsoleHelper.printTitle("Edit A Book");
		}
		ConsoleHelper.printMessage("\n");
		for (Map.Entry<Integer, Book> book : books.entrySet()) {
			int key = book.getKey();
			String title = book.getValue().getTitle();
			ConsoleHelper.printMessage("[" + key + "] " + title);
			lastId = key;
		}
		ConsoleHelper.printMessage("\n");
		ConsoleHelper.printPrompt("To view details enter the book ID, to return press <Enter>");
		while (true) {
			Integer i = ConsoleHelper.getInt(true);
			if (i == null) {
				return;
			}
			if (i >= 1 && i <= lastId) {
				if (readOnly) {
				   viewBookDetails(i);
				} else {
					editBook(i);
					return;
				}
			} else {
				System.err.print("\nInvalid Book ID [" + i + "]");
			}
		}
		
	}
	
	private void viewBookDetails(int id) {
		
		Book book  = books.get(id);
		if (book == null) {
			throw new NullPointerException("The book is null. Book ID: " + id + " not found in the database");
		}
		
		ConsoleHelper.printMessage("Book ID: " + id + "\n");
		ConsoleHelper.printMessage("ID: " + book.getId());
		ConsoleHelper.printMessage("Title: " + book.getTitle());
		ConsoleHelper.printMessage("Author: " + book.getAuthor());
		ConsoleHelper.printMessage("Description: " + book.getDescription());
		
	}
	
	private void addBook() {
		
		clearConsole();
		String s = "";
		int newBookId = books.size() + 1;
		Book book = new Book();
		book.setId(newBookId);
		ConsoleHelper.printTitle("Add A New Book. ID: " + newBookId);
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Title");
		
		s = ConsoleHelper.getString();
		book.setTitle(s);
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Author");
		
		s = ConsoleHelper.getString();
		book.setAuthor(s);
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Description");
		
		s = ConsoleHelper.getString();
		book.setDescription(s);
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Enter S to Save, or <Enter> to Abort");
		
		s = ConsoleHelper.getString();
		
		if (s.equalsIgnoreCase("S")) {
			books.put(newBookId, book);
			ConsoleHelper.printMessage("\n");
			ConsoleHelper.printMessage("Book saved in library\n");
			try {
			    Thread.currentThread().sleep(1000);
			} catch (Exception e) {}
			
		}
		
		
		
	}
	
	private void editBook(int id) {
		
		Book book = books.get(id);
		
		clearConsole();
		String s = "";
		int bookId = book.getId();
		
		ConsoleHelper.printPrompt("Title [" + book.getTitle() + "]");
		
		s = ConsoleHelper.getString();
		if (s.trim().length() > 0) {
		    book.setTitle(s);
	    }
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Author");
		
		s = ConsoleHelper.getString();
		if (s.trim().length() > 0) {
		   book.setAuthor(s);
		}
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Description");
		
		s = ConsoleHelper.getString();
		if (s.trim().length() > 0) {
		    book.setDescription(s);
		}
		ConsoleHelper.printMessage("\n");
		
		ConsoleHelper.printPrompt("Enter S to Save, or <Enter> to Abort");
		
		s = ConsoleHelper.getString();
		
		if (s.equalsIgnoreCase("S")) {
			books.put(bookId, book);
			ConsoleHelper.printMessage("\n");
			ConsoleHelper.printMessage("Book saved in library\n");
			try {
			    Thread.currentThread().sleep(1000);
			} catch (Exception e) {}
			
		}
		
		
		
	}
	
	private void searchBooks() {
		
		ConsoleHelper.printTitle("Search");
		ConsoleHelper.printMessage("\n");
		ConsoleHelper.printPrompt("Search");
		String s1 = ConsoleHelper.getString();
		String s3 = s1;
		if (s1 == null || s1.length() == 0) {
			return;
		}
		s1 = s1.toUpperCase();
		Map<Integer, Book> results = new TreeMap<Integer, Book>();
		
		for (Map.Entry<Integer, Book> book : books.entrySet()) {
			String s2 = (book.getValue().getTitle() + book.getValue().getAuthor() + book.getValue().getDescription()).toUpperCase();
			if (s2.indexOf(s1) >= 0) {
				results.put(book.getKey(), book.getValue());
			}
		}
		
		if (results.size() > 0) {
			ConsoleHelper.printMessage("\nThe Following books matched your query. Enter the book ID to see more details, or <Enter> to return\n");
			for (Map.Entry<Integer, Book> book : results.entrySet()) {
				ConsoleHelper.printMessage("[" + book.getValue().getId() + "] " + book.getValue().getTitle());
			}
		} else {
			ConsoleHelper.printMessage("\nMatch Not found for " + s3);
			int i = ConsoleHelper.getInt(true);
			return;
		}
		while(true) {
			
			ConsoleHelper.printPrompt("Book ID");
			Integer i = ConsoleHelper.getInt(true);
			if (i == null) {
				break;
			}
			Book book = results.get(i);
			if (book == null) {
				System.err.print("That ID is not in the results");
			} else {
				viewBookDetails(i);
			}
			
		}
		   
	}
	
	private void save() throws Exception {
		// Could save to a database in the future. Using Inserts and Updates. Use csv file for now
		
		FileWriter fw = new FileWriter("books.csv", false);
        BufferedWriter bw = new BufferedWriter(fw);
		String rec = "";
		
		for (Map.Entry<Integer, Book> book : books.entrySet()) {
			rec = "";
			rec += book.getValue().getId() + ",";
			rec += book.getValue().getTitle() + ",";
			rec += book.getValue().getAuthor() + ",";
			rec += book.getValue().getDescription() + "\n";
			bw.write(rec);
		}
		bw.flush();
		
		try {
			bw.close();
			fw.close();
		} catch (Exception e) {
			
		}
		ConsoleHelper.printMessage("\nData Saved, Processing Complete");
	}
	private void init(String[] args) throws Exception {
		loadBooks();
	}
	
	private void loadBooks() throws Exception {
		
		books = new TreeMap<Integer, Book>();
		
		// Could be loaded from a ResultSet in the future. No Relational Database available. Using csv
		/* 
		int key = 0;
		key = books.size() + 1;
		books.put(key, new Book(key, "The Book 1","Tim Knott","A book about Tim Knott"));
		key = books.size() + 1;
		books.put(key, new Book(key, "The Book 2","Steve Knott","A book about Steve Knott"));
		key = books.size() + 1;
		books.put(key, new Book(key, "The Book 3","Chris Knott","A book about Chris Knott"));
		key = books.size() + 1;
		books.put(key, new Book(key, "The Book 4","Joey Knott","A book about Joey Knott"));
		*/
		FileInputStream fis = new FileInputStream("books.csv");
		DataInputStream dis = new DataInputStream(fis);
		String line = "";
		while ((line = dis.readLine()) != null) {
			//System.out.println(line);
			Book book = new Book();
			int key = Integer.parseInt(line.split(",")[0]);
			book.setId(key);
			book.setTitle(line.split(",")[1]);
			book.setAuthor(line.split(",")[2]);
			book.setDescription(line.split(",")[3]);
			books.put(key, book);
		}
		
		try {
			fis.close();
			dis.close();
		} catch (Exception e) {
			
		}
		
	}
	
	public static void clearConsole() {
	    
	    for (int i = 0; i < 2000; i++) {
	    	System.out.print("\n");
	    }
	    System.out.flush();
	}
	
	static class Book {
		
		
		private int id = 0;
		private String title = "";
		private String author = "";
		private String description = "";
		
		Book() {}
		Book(int id, String title
				, String author
				, String description) {
			this.id = id;
			this.title = title;
			this.author = author;
			this.description = description;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		 
	}
}
