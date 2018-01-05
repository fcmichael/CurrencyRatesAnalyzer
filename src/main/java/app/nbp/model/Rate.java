package app.nbp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rate {

    @Id
    @GeneratedValue
    private Integer id;

	private String code;

	@Transient
	private Date effectiveDate;

	@Transient
	private double mid;

	@Transient
	private double change;
}
