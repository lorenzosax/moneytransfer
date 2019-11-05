import {NgbDate} from "@ng-bootstrap/ng-bootstrap";

export class BonificoFormModel {
  amount: string;
  executionDate: NgbDate;
  beneficiaryName: string;
  beneficiaryIban: string;
  paymentReason: string;
  currencyCode: string;
}
