package com.olmez.myamango.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.myamango.MyamangoTestApplication;
import com.olmez.myamango.model.CurrencyRate;
import com.olmez.myamango.services.TestRepoCleanerService;
import com.olmez.myamango.utility.TestUtility;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = MyamangoTestApplication.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
class CurrencyInfoRepositoryTest {

	@Autowired
	private CurrencyRateRepository repository;
	@Autowired
	private TestRepoCleanerService cleanerService;

	private LocalDate date = LocalDate.of(2023, 2, 13);
	private LocalDate date2 = LocalDate.of(2023, 2, 14);
	private LocalDate date3 = LocalDate.of(2023, 2, 15);

	@BeforeEach
	public void setup() {
		cleanerService.clear();
	}

	@Test
	void testFindAllActive() {
		// arrange
		var rate = new CurrencyRate();
		rate.setDate(date);
		rate = repository.save(rate);

		var rate2 = new CurrencyRate();
		rate2.setDate(date2);
		rate2 = repository.save(rate2);

		var rate3 = new CurrencyRate();
		rate3.setDate(date3);
		rate3.setDeleted(true);
		rate3 = repository.save(rate3);

		// act
		var rates = repository.findAllActive();

		// assert
		assertThat(rates).hasSize(2).doesNotContain(rate3);
	}

	@Test
	void testFindByDate() {
		// arrange
		var rate = new CurrencyRate();
		rate.setDate(date);
		rate = repository.save(rate);

		var rate2 = new CurrencyRate();
		rate2.setDate(date2);
		rate2 = repository.save(rate2);

		var rate3 = new CurrencyRate();
		rate3.setDate(date3);
		rate3.setDeleted(true);
		rate3 = repository.save(rate3);

		// act
		var result = repository.findByDate(date2);

		// assert
		assertThat(result).isEqualTo(rate2);
	}

	@Test
	void testFindRatesByDates() {
		// arrange
		var rate = new CurrencyRate();
		rate.setDate(date);
		rate = repository.save(rate);

		var rate2 = new CurrencyRate();
		rate2.setDate(date2);
		rate2 = repository.save(rate2);

		var rate3 = new CurrencyRate();
		rate3.setDate(date3);
		rate3 = repository.save(rate3);

		// act
		var list = repository.findRatesByDates(List.of(date, date3));

		// assert
		assertThat(list).hasSize(2).contains(rate, rate3);
	}

}
