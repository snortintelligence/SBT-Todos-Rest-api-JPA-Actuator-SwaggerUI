package com.snort.intelli.app.entites;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "tbl_Todos")
public class Todos {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long taskId;
	@Column(name = "title")
	private String taskTitle;
	private String description;
	private String completed; //[NEW, COMPLETED, PENDING]
	private String assignedBy;
	private Integer difficultyLevel; //[1 - 5]
	private Date assignedDate;
	private Date updatedDate;
}
