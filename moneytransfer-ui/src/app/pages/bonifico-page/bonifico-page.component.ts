import {Component, OnInit} from '@angular/core';
import {NgbDate, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {BonificoService} from '../../services/bonifico.service';
import {PrepareBonificoInfo} from '../../model/prepare-bonifico-info.model';
import {NgbDateCustomParserFormatter} from '../../utils/filter/dateformat';
import {BonificoFormModel} from '../../model/form/bonifico-form.model';
import {Constant} from '../../utils/constant';
import {VerifyBonificoRequest} from '../../model/request/verify-bonifico-request.model';
import {Utils} from '../../utils/utils';
import {Amount} from '../../model/amount.model';
import {VerifyBonificoResponse} from '../../model/response/verify-bonifico-response.model';
import {PrepareBonificoResponse} from '../../model/response/prepare-bonifico-response.model';
import {ExecuteBonificoResponse} from '../../model/response/execute-bonifico-response.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-bonifico-page',
  templateUrl: './bonifico-page.component.html',
  styleUrls: ['./bonifico-page.component.less'],
  providers: [
    {provide: NgbDateParserFormatter, useClass: NgbDateCustomParserFormatter}
  ]
})
export class BonificoPageComponent implements OnInit {

  constructor(
    private bonificoService: BonificoService,
    private router: Router
  ) {}

  submitted = false;
  loading = false;
  isLastOperation = false;
  verifyButtonShowed = true;
  isFinalState = false;

  baseInfo: PrepareBonificoInfo;

  form: BonificoFormModel;

  executionDateMax: NgbDate;
  executionDateMin: NgbDate;

  summaryVerifyBonifico: VerifyBonificoResponse;
  summaryExecuteBonifico: ExecuteBonificoResponse;

  ngOnInit() {
    this.form = new BonificoFormModel();
    this.form.currencyCode = Constant.CURRENCY_CODE;
    this.prepareBonifico();
  }

  submitForm() {
    this.submitted = true;
  }

  back() {
    this.submitted = false;
    this.summaryVerifyBonifico = null;
    this.isLastOperation = false;
    this.verifyButtonShowed = true;
  }

  newBonifico() {
    window.location.reload();
  }

  prepareBonifico() {
    this.loading = true;
    this.bonificoService.getBasicInfoForBonifico().subscribe((res: PrepareBonificoResponse) => {
      this.loading = false;
      if (res.result.outcome === 'SUCCESS') {
        this.baseInfo = res.data;
        this.form.executionDate = Utils.dateStringToNgbDate(this.baseInfo.todayDate);
        this.setAvailableDateForExecutionDate(this.baseInfo.todayDate, this.baseInfo.transferLimitDate);
      }
    });
  }

  verifyBonifico() {
    this.loading = true;
    const request: VerifyBonificoRequest = new VerifyBonificoRequest();
    request.data.executionDate = Utils.ngbDateToString(this.form.executionDate);
    request.data.paymentReason = this.form.paymentReason;
    request.data.beneficiaryIban = this.form.beneficiaryIban;
    request.data.beneficiaryName = this.form.beneficiaryName;
    request.data.amount = new Amount();
    request.data.amount.currency = this.form.amount;
    this.bonificoService.verifyBonifico(request).subscribe((res: VerifyBonificoResponse) => {
      this.loading = false;
      this.summaryVerifyBonifico = res;
      this.verifyButtonShowed = false;
      if (res.result.outcome === 'SUCCESS') {
        this.isLastOperation = true;
      }
    });
  }

  executeBonifico() {
    this.loading = true;
    this.bonificoService.executeBonifico(this.summaryVerifyBonifico.transaction.id).subscribe((res: ExecuteBonificoResponse) => {
      this.loading = false;
      this.isFinalState = true;
      this.summaryExecuteBonifico = res;
    });
  }

  private setAvailableDateForExecutionDate(minDate: string, maxDate: string) {
    this.executionDateMin = Utils.dateStringToNgbDate(minDate);
    this.executionDateMax = Utils.dateStringToNgbDate(maxDate);
  }
}
