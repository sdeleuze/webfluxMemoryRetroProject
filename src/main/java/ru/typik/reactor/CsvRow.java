package ru.typik.reactor;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("csv")
public class CsvRow {

	@Id
	private long id;
	private String column1;
	private String column2;
	private String column3;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CsvRow csvRow = (CsvRow) o;
		return id == csvRow.id && Objects.equals(column1, csvRow.column1) && Objects.equals(column2, csvRow.column2) && Objects.equals(column3, csvRow.column3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, column1, column2, column3);
	}
}
