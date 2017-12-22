package app.nbp.model;

import lombok.Getter;

import java.util.List;

@Getter
public class RateDTO {
	private String effectiveDate;
	private List<Rate> rates;
}
