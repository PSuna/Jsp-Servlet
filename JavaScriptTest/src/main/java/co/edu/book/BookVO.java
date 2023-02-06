package co.edu.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	private String bookCode;
	private String bookName;
	private String author;
	private String press;
	private int price;
}
