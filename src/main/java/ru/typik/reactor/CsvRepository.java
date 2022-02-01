package ru.typik.reactor;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CsvRepository extends ReactiveCrudRepository<CsvRow, Long> {
}
