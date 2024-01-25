package com.example.popolamento.Entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Season {
	@Id
	@Column(name = "id", columnDefinition = "int")
	private int Id;

	@Column(name = "year", columnDefinition = "int")
	private int year;

}
