import {Amount} from "./amount.model";

export class VerifyBonificoData {
  amount: Amount = new Amount();
  executionDate: string;
  beneficiaryName: string;
  beneficiaryIban: string;
  paymentReason: string;
}
