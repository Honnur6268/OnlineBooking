package com.onlinebook.model;

public class PaymentFlight {

	// Payment Using Bank
		private String bankName;
		private String beneficiaryName;
		private String accountNumber;
		private String IFSC;

		// Payment Using Card
		private String cardNumber;
		private String cardExpireDate;
		private String CVV;

		// Payment Using Bank
		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBeneficiaryName() {
			return beneficiaryName;
		}

		public void setBeneficiaryName(String beneficiaryName) {
			this.beneficiaryName = beneficiaryName;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getIFSC() {
			return IFSC;
		}

		public void setIFSC(String iFSC) {
			IFSC = iFSC;
		}

		// Payment Using Card
		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getCardExpireDate() {
			return cardExpireDate;
		}

		public void setCardExpireDate(String cardExpireDate) {
			this.cardExpireDate = cardExpireDate;
		}

		public String getCVV() {
			return CVV;
		}

		public void setCVV(String cvv) {
			this.CVV = cvv;
		}

}
