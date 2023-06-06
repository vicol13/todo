package com.vvi.todo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;
	private String description;
	private UUID userId;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board;

}
