import {Component, Input, OnInit} from '@angular/core';
import {VerifyBonificoInfo} from '../../../model/verify-bonifico-info.model';
import {Transaction} from '../../../model/transaction.model';
import {ExecuteBonificoInfo} from '../../../model/execute-bonifico-info.model';

@Component({
  selector: 'app-summary-bonifico',
  templateUrl: './summary-bonifico.component.html',
  styleUrls: ['./summary-bonifico.component.less']
})
export class SummaryBonificoComponent implements OnInit {

  constructor() { }

  smData: VerifyBonificoInfo | ExecuteBonificoInfo;
  smTransaction: Transaction;
  smStatus: string;
  smMessages: string[];
  smIsFinal: boolean;

  ngOnInit() {
  }

  @Input()
  set data(d: VerifyBonificoInfo | ExecuteBonificoInfo) {
    this.smData = d;
  }

  @Input()
  set transaction(t: Transaction) {
    this.smTransaction = t;
  }

  @Input()
  set status(s: string) {
    this.smStatus = s;
  }

  @Input()
  set messages(m: string[]) {
    this.smMessages = m;
  }

  @Input()
  set isFinal(isFinal: boolean) {
    this.smIsFinal = isFinal;
  }
}
