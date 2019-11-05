import {Component, Input, OnInit} from '@angular/core';
import {VerifyBonificoInfo} from "../../../model/verify-bonifico-info.model";
import {Transaction} from "../../../model/transaction.model";
import {ExecuteBonificoInfo} from "../../../model/execute-bonifico-info.model";

@Component({
  selector: 'app-summary-bonifico',
  templateUrl: './summary-bonifico.component.html',
  styleUrls: ['./summary-bonifico.component.less']
})
export class SummaryBonificoComponent implements OnInit {

  constructor() { }

  _data: VerifyBonificoInfo | ExecuteBonificoInfo;
  _transaction: Transaction;
  _status: string;
  _messages: string[];
  _isFinal: boolean;

  ngOnInit() {
  }

  @Input()
  set data(d: VerifyBonificoInfo | ExecuteBonificoInfo) {
    this._data = d;
  }

  @Input()
  set transaction(t: Transaction) {
    this._transaction = t;
  }

  @Input()
  set status(s: string) {
    this._status = s;
  }

  @Input()
  set messages(m: string[]) {
    this._messages = m;
  }

  @Input()
  set isFinal(isFinal: boolean) {
    this._isFinal = isFinal;
  }
}
