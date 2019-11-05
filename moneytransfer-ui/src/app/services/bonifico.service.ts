import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Constant} from '../utils/constant';
import {PrepareBonificoResponse} from "../model/response/prepare-bonifico-response.model";
import {Observable} from "rxjs/internal/Observable";
import { map } from 'rxjs/operators';
import {VerifyBonificoRequest} from "../model/request/verify-bonifico-request.model";
import {VerifyBonificoResponse} from "../model/response/verify-bonifico-response.model";
import {Utils} from "../utils/utils";
import {ExecuteBonificoResponse} from "../model/response/execute-bonifico-response.model";

@Injectable({
  providedIn: 'root'
})
export class BonificoService {

  constructor(
    private http: HttpClient
  ) { }

  currentUserLogged: object = {
    customerId: '123',
    accountNumber: '0001234566'
  };

  getBasicInfoForBonifico(): Observable<PrepareBonificoResponse> {
    return this.http.get(Utils.buildEndpoint(Constant.ENDPOINT.PREPARE_BONIFICO, this.currentUserLogged))
      .pipe(map((res: PrepareBonificoResponse) => {
        return res;
      }));
  }

  verifyBonifico(data: VerifyBonificoRequest): Observable<VerifyBonificoResponse> {
    return this.http.post(Utils.buildEndpoint(Constant.ENDPOINT.VERIFY_BONIFICO, this.currentUserLogged), data)
      .pipe(map((res: VerifyBonificoResponse) => {
        return res;
      }));
  }

  executeBonifico(trxId: string): Observable<ExecuteBonificoResponse> {
    return this.http.put(Utils.buildEndpoint(Constant.ENDPOINT.EXECUTE_BONIFICO, {...this.currentUserLogged, trxId}), {})
      .pipe(map((res: ExecuteBonificoResponse) => {
        return res;
      }));
  }
}
