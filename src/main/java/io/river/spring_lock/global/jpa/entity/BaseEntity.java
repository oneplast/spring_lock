package io.river.spring_lock.global.jpa.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	public boolean isNew() {
		return id == null;
	}

	public String getModelName() {
		String simpleName = this.getClass().getSimpleName();

		return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
	}
}
