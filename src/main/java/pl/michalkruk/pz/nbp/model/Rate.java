package pl.michalkruk.pz.nbp.model;

import lombok.*;

import javax.persistence.Entity;
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
    private String code;

	private boolean favourite;

	@Transient
	private Date effectiveDate;

	private double mid;

	private double change;
}
