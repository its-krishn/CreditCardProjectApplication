package com.training.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.training.project.model.CreditCardHistory;
import com.training.project.model.CreditCardModel;
import com.training.project.model.CreditCardRegistrationModel;
import com.training.project.repository.CreditCardHistoRepo;
import com.training.project.repository.CreditCardModelRepo;
import com.training.project.repository.CreditCardRegRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

	@InjectMocks
	CreditCardService creditCardService;

	@Mock
	CreditCardHistoRepo creditCardHistoRepo;

	@Mock
	CreditCardHistory creditCardHistory;

	@Mock
	CreditCardModelRepo creditCardModelRepo;

	@Mock
	CreditCardRegRepository creditCardRegRepository;

	@Test
	public void testCreateCreditCard() {
		CreditCardRegistrationModel registrationModel = new CreditCardRegistrationModel();
		registrationModel.setAccountnumber(4578);
		registrationModel.setUsername("Krishnendu");
		registrationModel.setAge(18);
		registrationModel.setPhonenumber(76256);
		registrationModel.setSalary(30000);

		CreditCardModel creditCard1 = new CreditCardModel();
		creditCard1.setCreditCardNumber(4588);
		creditCard1.setCreditLimit(50000);
		creditCard1.setPassword("krishna");
		creditCard1.setStatus("unblock");
		creditCard1.setUsername("Krishnendu");

		CreditCardModel creditCard2 = new CreditCardModel();
		creditCard2.setCreditCardNumber(4588);
		creditCard2.setCreditLimit(50000);
		creditCard2.setPassword("123");
		creditCard2.setStatus("unblock");
		creditCard2.setUsername("Krishnendu");

		when(creditCardModelRepo.save(creditCard2)).thenReturn(creditCard1);
		assertEquals(creditCard1, creditCardService.createCreditCard(registrationModel));

	}

	@Test
	public void testUnLockCard() {
		CreditCardRegistrationModel registrationModel = new CreditCardRegistrationModel();
		CreditCardModel creditCard = new CreditCardModel();
		creditCard.setStatus("unLock");
		creditCard.setCreditCardNumber(458);

		Optional<CreditCardModel> creditCardModel = Optional.ofNullable(creditCard);

		when(creditCardModelRepo.findById(Mockito.anyInt())).thenReturn(creditCardModel);
		when(creditCardModelRepo.save(Mockito.any())).thenReturn(creditCard);
		assertEquals(creditCard, creditCardService.unLockCard(458));

	}

	@Test
	public void testBlockCard() {
		CreditCardRegistrationModel registrationModel = new CreditCardRegistrationModel();
		CreditCardModel creditCard = new CreditCardModel();
		creditCard.setStatus("block");
		creditCard.setCreditCardNumber(458);

		Optional<CreditCardModel> creditCardModel = Optional.ofNullable(creditCard);

		when(creditCardModelRepo.findById(Mockito.anyInt())).thenReturn(creditCardModel);
		when(creditCardModelRepo.save(Mockito.any())).thenReturn(creditCard);
		assertEquals(creditCard, creditCardService.block(458));

	}
}
