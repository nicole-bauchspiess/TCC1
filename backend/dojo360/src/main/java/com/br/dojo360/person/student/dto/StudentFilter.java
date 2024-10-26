package com.br.dojo360.person.student.dto;

import com.br.dojo360.belt.Belts;
import com.br.dojo360.person.student.StudentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentFilter {

    private String name;
    private String cpf;
    private Character gender;
    private LocalDate birthdayStart;
    private LocalDate birthdayEnd;
    private List<Belts> belts;
    private Boolean isEnable;
    private Integer page;
    private Integer size;

    public Specification<StudentEntity> toSpec() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            validateAndAddPredicate("name", predicates, builder, root);
            validateAndAddPredicate("cpf", predicates, builder, root);


            if (belts != null && !belts.isEmpty()) {
                predicates.add(
                        root.get("belts").in(belts)
                );
            }

            if (gender != null) {
                predicates.add(
                        builder.equal(root.get("gender"), gender)
                );
            }

            if (isEnable != null) {
                predicates.add(
                        builder.equal(root.get("isEnable"), isEnable)
                );
            }

            if (birthdayStart != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("birthday"), birthdayStart));
            }

            if (birthdayEnd != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("birthday"), birthdayStart));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void validateAndAddPredicate(String value, List<Predicate> predicates, CriteriaBuilder builder, Root root) {
        if (value != null && !value.isBlank()) {
            predicates.add(
                    builder.like(builder.lower(root.get(value)), "%" + value.toLowerCase() + "%")
            );
        }
    }


}
